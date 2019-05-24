package org.fh.general.ecom.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public interface RedisHashService {
	
	/**
     * 设置HashMap
     *
     * @param key
     *            Key
     * @param hash
     *            Map
     * @return 状态码：成功-OK
     */
     public String hmset(String key, Map<String, String> hash);

     /**
     * 设置哈希字段的字符串值,存在则不设置
     *
     * @param key
     *            key
     * @param field
     *            hashmap ---> key
     * @param value
     *            hashmap ---> value
     * @return
     */
     public long hset(String key, String field, String value);

     /**
     * 设置哈希字段的字符串值,存在则不设置
     *
     * @param key
     *            key
     * @param field
     *            hashmap ---> key
     * @param value
     *            hashmap ---> value
     * @return
     */
     public long hsetnx(String key, String field, String value);

     /**
     * 查询该key对应的所有hash
     *
     * @param key
     * @return Map<String, String>
     */
     public Map<String, String> hgetall(String key);
     
     /**
      * 通过key和fields查询值
      * @param key
      * 	key
      * @param fields
      * 	hash中的key
      * @return
      * 	List<String>	
      */
     public List<String> hmget(String key, String... fields);

     /**
     * 获取hash中的值
     *
     * @param key
     *            key
     * @param field
     *            hash中的key
     * @return String
     */
     public String hget(String key, String field);

     /**
     *
     * 判断一个哈希字段存在与否
     *
     * @param key
     *            key
     * @param field
     *            hash中的key
     * @return Boolean
     */
     public Boolean hexists(String key, String field);

     /**
     * 删除一个或多个哈希字段
     *
     * @param key
     *            key
     * @param fields
     *            hash中的key
     * @return 影响
     */
     public long hdel(String key, String... fields);
     /**
     * 由给定数量增加的哈希字段的整数值
     *
     * @param key
     *            key
     * @param field
     *            hash中的key
     * @param increment
     *            增加量
     * @return 影响
     */
     public long hincrby(String key, String field, long increment);

     /**
     * 由给定的递增量哈希字段的浮点值
     *
     * @param key
     *            key
     * @param field
     *            hash中的key
     * @param increment
     *            增加量
     * @return 影响
     */
     public double hincrByFloat(String key, String field, double increment);

     /**
     * 获取所有在哈希字段(hash--->key)
     *
     * @param key
     *            key
     * @return Set<String>
     */
     public Set<String> hkeys(String key);

     /**
     * 获取哈希字段数
     *
     * @param key
     *            key
     * @return
     */
     public long hlen(String key);

     /**
     * 获取在哈希中的所有值
     *
     * @param key
     * @return List<String>
     */
     public List<String> hvals(String key);

     /**
     * 增量迭代哈希字段及相关值
     *
     * @param key
     * @param cursor
     * @return
     */
     public ScanResult<Entry<String, String>> hscan(String key, String cursor);

	  
	/**     
	 * 描述   
	 * @return     
	 */
	Jedis getJedis();

	  
	/**     
	 * 描述   
	 * @param key
	 * @param msg     
	 */
	void pushMsg(String key, String msg);

	  
	/**     
	 * 描述   
	 * @param key
	 * @return     
	 */
	String consum(String key);
	
	void sethValue(String key, String value, int seconds);
	String gethValue(String key); /**


	/**     
	 * 描述   
	 * @param jedis     
	 */
	void closeJedisConnection(Jedis jedis);

	void set(String key, String value, int seconds);

	String get(String key);

	void delete(String key);



}
