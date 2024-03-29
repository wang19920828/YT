package org.fh.general.ecom.common.mongo;//package org.fh.general.ecom.common.utils;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Collation;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bson.*;
import org.fh.general.ecom.common.utils.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
@Slf4j
public class MongoDbUtil {

	public static MongoDatabase mongoDB=null;
	private static MongoConfig config = new MongoConfig();
	private static String keyValueTable="whp_key_value";

	@SuppressWarnings("resource")
	public static MongoDatabase getMongoDataBase(){
		if(mongoDB!=null){
			return mongoDB;
		}
		 MongoClient mongoClient = new MongoClient(config.getHost() , config.getPort() );
		 mongoDB = mongoClient.getDatabase("invest_mongo");
		  return mongoDB;
	}

	public static void main(String[] args) {
		System.out.println(MongoDbUtil.getMongoDataBase());

	}
	public static void setValue(String key,String value){
		Map<String,Object> map=new ConcurrentHashMap<String, Object>();
		map.put("key", key);
		map.put("value", value);
		try {
			deleteValue(key);
			insertOne(keyValueTable,map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static String getValue(String key){
		Map<String,Object> result=new ConcurrentHashMap<String, Object>();
		Map<String,Object> map=new ConcurrentHashMap<String, Object>();
		map.put("key", key);
		try {
			result=findOne(keyValueTable, map);
			if(result==null){
				return null;
			}
			return result.get("value").toString();
		} catch (Exception e) {
			result=null;
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public static Object getObjectValue(String key,Class c,Map<String,Class> map){
		String value=getValue(key);
		JSONObject json = JSONObject.fromObject(value);
		Object obj = JSONObject.toBean(json, c,map);
		return obj;
	}
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static List getArrayValue(String key,Class c,Map<String,Class> map){
		String value=getValue(key);
		JSONArray json = JSONArray.fromObject(value);
		List list = JSONArray.toList(json, c, map);
		return list;
	}
	public static void deleteValue(String key){
		Map<String, Object> map=new ConcurrentHashMap<String, Object>();
		map.put("key", key);
		try {
			deleteMany(keyValueTable, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 如果表不存在则自动创建
	 * map
	 * key 代表字段
	 * value 代表值
	 * */
	public static void insertOne(String tableName,Map<String, Object> map) throws Exception{
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(map);
		mc.insertOne(doc);
	}
	/**
	 * 批量添加
	 * 无返回值
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void insertMany(String tableName,List<Map> list) throws Exception{
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		List<Document> listd=new ArrayList<Document>();
		for(Map m:list){
			listd.add(getDocument(m));
		}
		if(!listd.isEmpty()){
			mc.insertMany(listd);
		}


	}

	public static Long deleteMany(String tableName,Map<String, Object> map) throws Exception{
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(map);
		DeleteResult result=mc.deleteMany(doc);
		return result.getDeletedCount();
	}

	public static Long updateOne(String tableName,Map<String,Object> condition,Map<String, Object> content) throws Exception{
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=new Document("$set",getDocument(content));
		Document con=getDocument(condition);
		UpdateResult result=mc.updateOne(con, doc);
		System.out.println(result);
		return result.getModifiedCount();
	}
	public static Long updateMany(String tableName,Map<String,Object> condition,Map<String, Object> content) throws Exception{
		/*MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=new Document("$set",getDocument(content));
		Document con=getDocument(condition);
		UpdateResult result=mc.updateMany(con, doc);
		System.out.println(result);
		return result.getModifiedCount();*/
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		list = findList(tableName, condition);
		//先删除
		Long num = deleteMany(tableName, condition);
		//后新增
		updateMongoDB(tableName, list, content);
		return num;
	}
	/**
	 * 查询返回list
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String,String>> findList(String tableName,Map<String,Object> param) throws Exception{
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(param);
		Document sort=getSortDocument(param);
		FindIterable<Document> ft=mc.find(doc);
		if(sort!=null){
			ft=ft.sort(sort);
		}
		for(Document d:ft){
			Map json=JSONObject.fromObject(d.toJson());
			list.add(json);
		}
		return list;
	}
	/**
	 * 查询返回list
	 * */
	public static List<String> findListPageSort(String tableName,Map<String,Object> param,Map<String,Object> sortMap) throws Exception{

		log.info("findListPageSort.param:"+param);
		List<String> list=new ArrayList<String>();
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(param);
		Document sort=getDocument(sortMap);
		int page=0;
		int pageSize=0;
		if(param.get("page")!=null && param.get("pageSize")!=null){
			 page=Integer.parseInt(param.get("page").toString());
			 pageSize=Integer.parseInt(param.get("pageSize").toString());
		}else if(param.get("currentPage")!=null && param.get("pageSize")!=null){
			page=Integer.parseInt(param.get("currentPage").toString());
			pageSize=Integer.parseInt(param.get("pageSize").toString());
		}else if(param.get("currentPageNum")!=null && param.get("pageSize")!=null){
			page=Integer.parseInt(param.get("currentPageNum").toString());
			pageSize=Integer.parseInt(param.get("pageSize").toString());
		}

		doc.remove("page");
		doc.remove("pageSize");
		doc.remove("currentPageNum");
		//2017-08-22 begin
		int skip=(page-1)*pageSize;
		if(skip<0){
			return list;
		}else if(skip==0){
			Collation collation  = Collation.builder().locale("zh").numericOrdering(true).build();
			FindIterable<Document> ft=mc.find(doc).collation(collation).sort(sort).limit(pageSize);
			//FindIterable<Document> ft=mc.find(doc).sort(sort).limit(pageSize);
			for(Document d:ft){
				list.add(d.toJson());
			}
			return list;
		}
		//2017-08-22 end
		Collation collation  = Collation.builder().locale("zh").numericOrdering(true).build();
		FindIterable<Document> ft=mc.find(doc).collation(collation).skip(skip).sort(sort).limit(pageSize);
		for(Document d:ft){
			list.add(d.toJson());
		}
		return list;
	}

	public static Long findCount(String tableName,Map<String,Object> param) throws Exception{
		param.remove("page");
		param.remove("pageSize");
		param.remove("currentPageNum");
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(param);
		Long  totalCount=mc.count(doc);
		return totalCount;
	}
	/**
	 * 查询返回一个对象
	 * */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> findOne(String tableName,Map<String,Object> param) throws Exception{
		Map<String,Object> result=new ConcurrentHashMap<String, Object>();
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		Document doc=getDocument(param);
		FindIterable<Document> ft=mc.find(doc);
		Document first=ft.first();
		if(first==null){
			return null;
		}
		result=JSONObject.fromObject(first);
		return result;
	}
	/**
	 * 创建表
	 * */
	@SuppressWarnings("unused")
	private static void createTable(String tableName) throws Exception{
		getMongoDataBase().createCollection(tableName);
	}
	/**
	 * 删除表
	 * */
	public static void dropTable(String tableName) throws Exception{
		MongoCollection<Document> mc=getMongoDataBase().getCollection(tableName);
		mc.drop();
	}

	private static Document getSortDocument(Map<String,Object> param){
		Document doc=new Document();
		if(param==null){
			return null;
		}
		boolean flag=false;
		Iterator<String> it=param.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			if(key.contains(".SORT")){
				Object value=param.get(key);
				key=key.replace(".SORT", "");
				doc.put(key, Integer.valueOf(value.toString()));
				flag=true;
			}

		}
		if(!flag){
			return null;
		}


		return doc;
	}

	/**
	 * 将map转化为document对象
	 * */
	private static Document getDocument(Map<String,Object> param) throws Exception{
		Document doc=new Document();
		if(param==null){
			return doc;
		}
		Iterator<String> it=param.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			Object value=param.get(key);
			if(key.contains(".SORT")){
				key = key.replace(".SORT", "");
				doc.append(key,value);
				continue;
			}

			if(value==null){
				value="";
			}
			if(value instanceof BigDecimal){
				value=value+"";
			}

			if(key.contains(".GT") || key.contains(".LT")  || key.contains(".NE")){//大于
				 getBasicDBObject(doc, param, key);
			}else if(key.contains("like.")){//全查
				if(!value.equals("like.projectName")){
					Pattern pattern=Pattern.compile("^.*"+value+".*$");
					key=key.replace("like.", "");
					doc.append(key, pattern);
				}
			}else if(key.contains("likebefore.")){//前查
				Pattern pattern=Pattern.compile("^"+value+".*$");
				key=key.replace("likebefore.", "");
				doc.append(key, pattern);
			}else if(key.contains("likeafter.")){//后查
				Pattern pattern=Pattern.compile("^.*"+value+"$");
				key=key.replace("likeafter.", "");
				doc.append(key, pattern);
			}else if (key.contains("IN.")){
				key=key.replace("IN.", "");
				doc.append(key,new BasicDBObject("$in",value));
			}else{//普通
				doc.append(key, value);
			}
		}
		return doc;
	}

	private static void getBasicDBObject(Document doc,Map<String,Object> param,String key){
		BasicDBObject query = new BasicDBObject();
		key=key.replace(".GTE", "")
				.replace(".GT", "")
				.replace(".LTE", "")
				.replace(".LT", "")
				.replace(".NE", "");
		if(param.containsKey(key+".GT")){//大于
			query.append(QueryOperators.GT, param.get(key+".GT"));
		}
		if(param.containsKey(key+".GTE")){//大于等于
			query.append(QueryOperators.GTE, param.get(key+".GTE"));
		}
		if(param.containsKey(key+".LT")){//小于
			query.append(QueryOperators.LT, param.get(key+".LT"));
		}
		if(param.containsKey(key+".LTE")){//小于等于
			query.append(QueryOperators.LTE, param.get(key+".LTE"));
		}
		//"$ne"
		if(param.containsKey(key+".NE")){//不等于
			query.append(QueryOperators.NE, param.get(key+".NE"));
		}
		doc.append(key, query);
	}
	@SuppressWarnings("unchecked")
	private static BsonArray getBsonArray(Object obj){
        JSONArray userData = JSONArray.fromObject(obj);
        BsonArray bsonArray = new BsonArray();
        JSONObject jo;
        for (int i = 0; i < userData.size(); i++) {
            jo = userData.getJSONObject(i);
            BsonDocument document = new BsonDocument();
            if (!jo.isEmpty()) {
                Set<String> set = jo.keySet();
                for (String key : set) {
                    document.put(key, objectToBsonValue(jo.get(key)));
                }
            }
            bsonArray.add(document);
        }
        return bsonArray;
	}
	 /**
     * Java对象转BsonValue对象
     * @param obj
     * @return
     */
    private static BsonValue objectToBsonValue(Object obj){
        if (obj instanceof Integer){
            return new BsonInt32((Integer) obj);
        }

        if (obj instanceof String){
            return new BsonString((String) obj);
        }

        if (obj instanceof Long){
            return new BsonInt64((Long) obj);
        }

        if (obj instanceof Date){
            return new BsonDateTime(((Date) obj).getTime());
        }
        return new BsonNull();
    }

    /**
     * 修改MongoDB(先删除，后新增)
     * @param tableName
     * @param list
     * @param content
     */
    @SuppressWarnings("unchecked")
	public static void updateMongoDB(String tableName,List<Map<String, String>> list,Map<String, Object> content){
		try {
			if(list != null && list.size() > 0){
	    		for (Map<String, String> map : list) {
	    			Map<String,Object> objMap = ObjectUtils.ConvertMapStringToMapObj(map);
					for (String str : content.keySet()) {
						objMap.put(str, content.get(str));
					}
					for (String str : objMap.keySet()) {
						if(objMap.get(str).toString().indexOf("$numberLong") > 0){
							Map<String,Object> m=JSONObject.fromObject(objMap.get(str).toString());
							objMap.put(str, Long.valueOf(m.get("$numberLong").toString()));
						}
					}
					objMap.remove("_id");

					insertOne(tableName, objMap);
	    		}
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

/*	public static void main(String[] args) {
		Map<String,Object> sortMap = new ConcurrentHashMap<String,Object>();
		sortMap.put("sort",1);
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("like.productName", "测试");
		param.put("module", "search");
		param.put("module", "search");
		param.put("page", "0");
		param.put("pageSize", "10");
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		try {
			List<String> liststr=MongoDbUtil.findListPageSort("shopProduct",param, sortMap);
			System.out.println(liststr.size());
			for(String str:liststr){
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
