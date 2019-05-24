package org.fh.general.ecom.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.fh.general.ecom.common.comm.ConstantsMall;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Slf4j
public class RedisHashServiceImpl implements RedisHashService {


	private JedisPool jedisPool;
	
	RedisConfig redisConfig = new RedisConfig();
	 
	public RedisHashServiceImpl() {
		this.init();
	}

	/**
    * 设置HashMap
    *
    * @param key
    *            Key
    * @param hash
    *            Map
    * @return 状态码：成功-OK
    */
    public  String hmset(String key, Map<String, String> hash) {
         Jedis jedis = getJedis();
         String data = jedis.hmset(key, hash);
         jedisPool.returnResource(jedis);
         return data;
    }

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
    public  long hset(String key, String field, String value) {
   	 Jedis jedis = getJedis();
         Long data = jedis.hset(key, field, value);
         jedisPool.returnResource(jedis);
         return data;
    }

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
    public  long hsetnx(String key, String field, String value) {
         Jedis jedis = getJedis();
         Long data = jedis.hsetnx(key, field, value);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 查询该key对应的所有hash
    *
    * @param key
    * @return Map<String, String>
    */
    public  Map<String, String> hgetall(String key) {
         Jedis jedis = getJedis();
         Map<String, String> data = jedis.hgetAll(key);
         jedisPool.returnResource(jedis);
         return data;
    }
    
    /**
     * 通过key和fields查询值
     * @param key
     * 	key
     * @param fields
     * 	hash中的key
     * @return
     * 	List<String>	
     */
    public  List<String> hmget(String key,String...fields){
   	 Jedis jedis = getJedis();
        List<String> data = jedis.hmget(key, fields);
        jedisPool.returnResource(jedis);
        return data;
    }

    /**
    * 获取hash中的值
    *
    * @param key
    *            key
    * @param field
    *            hash中的key
    * @return String
    */
    public  String hget(String key, String field) {
         Jedis jedis = getJedis();
         String data = jedis.hget(key, field);
         jedisPool.returnResource(jedis);
         return data;
    }

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
    public  Boolean hexists(String key, String field) {
         Jedis jedis = getJedis();
         Boolean data = jedis.hexists(key, field);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 删除一个或多个哈希字段
    *
    * @param key
    *            key
    * @param fields
    *            hash中的key
    * @return 影响
    */
    public  long hdel(String key, String... fields) {
         Jedis jedis = getJedis();
         long data = jedis.hdel(key, fields);
         jedisPool.returnResource(jedis);
         return data;
    }

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
    public  long hincrby(String key, String field, long increment) {
         Jedis jedis = getJedis();
         long data = jedis.hincrBy(key, field, increment);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 由给定的递增量哈希字段的浮点值
    *
    * @param key
    *            key
    *  param fields
    *            hash中的key
    * @param increment
    *            增加量
    * @return 影响
    */
    public  double hincrByFloat(String key, String field, double increment) {
         Jedis jedis = getJedis();
         Double data = jedis.hincrByFloat(key, field, increment);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 获取所有在哈希字段(hash--->key)
    *
    * @param key
    *            key
    * @return Set<String>
    */
    public Set<String> hkeys(String key) {
         Jedis jedis = getJedis();
         Set<String> data = jedis.hkeys(key);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 获取哈希字段数
    *
    * @param key
    *            key
    * @return
    */
    public  long hlen(String key) {
         Jedis jedis = getJedis();
         Long data = jedis.hlen(key);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
    * 获取在哈希中的所有值
    *
    * @param key
    * @return List<String>
    */
    public  List<String> hvals(String key) {
         Jedis jedis = getJedis();
         List<String> data = jedis.hvals(key);
         jedisPool.returnResource(jedis);
         return data;
    }
    
    /**
    * 增量迭代哈希字段及相关值
    *
    * @param key
    * @param cursor
    * @return
    */
    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
         Jedis jedis = getJedis();
         ScanResult<Entry<String, String>> data = jedis.hscan(key, cursor);
         jedisPool.returnResource(jedis);
         return data;
    }

    /**
     * 获取连接
     * @return
     */
    @Override
    public Jedis getJedis(){
    	Jedis jedis=null;
    	try {
    		jedis = jedisPool.getResource();
    		if(StringUtils.isNotEmpty(redisConfig.getAuth())){
    			jedis.auth(redisConfig.getAuth());
    		}
    		return jedis;
    	} catch (Exception e) {
    		e.printStackTrace();
    		jedisPool.returnResource(jedis);//释放redis连接
    		log.info("RedisHashServiceImpl.getJedis出错了！");
    	}
    	return jedis;
    }
    /**
     * 关闭连接
     */
    @Override
    public void closeJedisConnection(Jedis jedis) {
    	//Jedis jedis=null;
		try {
			//jedis = getJedis();
			jedisPool.returnResource(jedis);
		} catch (Exception e) {
			if (jedis !=null && jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}
    
    public void init(){
    	 JedisPoolConfig config = new JedisPoolConfig();
    	 config.setMaxTotal(redisConfig.getMaxTotal()); 
         config.setMaxIdle(redisConfig.getMaxIdle());    
         config.setMaxWaitMillis(redisConfig.getMaxWait());    
         config.setTestOnBorrow(redisConfig.getTestOnBorrow());    
         config.setTestOnReturn(redisConfig.getTestOnReturn());    
         this.jedisPool = new JedisPool(config,redisConfig.getRedisIp(),redisConfig.getRedisPort());
    }
    
    /**
	 * Producer调用redis的lpush往特定key里塞入消息
	 */
    @Override
    public void pushMsg(String key,String msg){
    	Jedis jedis=this.getJedis();
    	jedis.lpush(key, msg);
    	jedisPool.returnResource(jedis);//释放redis连接
    }
	
    /**
	 * Producer/Consumer Mode
	 * 描述    当有多个consumers的时候，它会按照brpop调用的顺序分派消息，并非随机
	 * 获取key
	 * @param key
	 */
    @Override
	public String consum(String key){
    	Jedis jedis=this.getJedis();
		while (true) {
			List<String> msgs = jedis.brpop(ConstantsMall.BLOCK_TIMEOUT, key);
			if (msgs == null) continue;
			String jobMsg = msgs.get(1);
			jedisPool.returnResource(jedis);//释放redis连接
			return jobMsg;
		}
	}

	@Override
	public void sethValue(String key, String value, int seconds) {
    	Jedis jedis=this.getJedis();
    	jedis.hset(key, key, value);
    	jedis.expire(key, seconds);
    	jedisPool.returnResource(jedis);//释放redis连接
	}

	@Override
	public String gethValue(String key) {
	   	Jedis jedis=this.getJedis();
	   	String data=jedis.hget(key, key);	
	    jedisPool.returnResource(jedis);
	    return data;
	}
	
	@Override
	public void set(String key, String value, int seconds) {
    	Jedis jedis=this.getJedis();
    	if(seconds==0){
    		jedis.set(key, value);
    	}else{
    		jedis.setex(key, seconds, value);
    	}
    	
    	jedisPool.returnResource(jedis);//释放redis连接
	}
	
	@Override
	public  String get(String key) {
		
		String value = null;
		try {
			Jedis jedis=this.getJedis();
			value = jedis.get(key);
			jedisPool.returnResource(jedis);
		} catch (Throwable t) {
			log.error(t.getMessage(), t);
		}
		return value;
	}

	@Override
	public void delete(String key) {
		Jedis jedis=this.getJedis();
		jedis.del(key);
		jedisPool.returnResource(jedis);
	}
	
	
	

}
