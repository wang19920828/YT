package org.fh.general.ecom.common.redis;

import org.fh.general.ecom.common.utils.PropertiesUtils;

import java.io.InputStream;
import java.util.Properties;


public class RedisConfig {
	
	/**
	 * redis配置文件路径
	 */
	private static final String  REDIS_FILE_NAME = "/props/config.properties";
	
	/**
	 * jedis的最大连接数参数名称
	 */
	private static final String REDIS_MAXTOTAL_NAME = "redis.pool.maxTotal";
	
	/**
	 *jedis最大保存idel状态对象数参数名称
	 */
	private static final String REDIS_MAXIDLE_NAME = "redis.pool.maxIdle";
	
	/**
	 *jedis池没有对象返回时，最大等待时间参数名称
	 */
	private static final String  REDIS_MAXWAIT_NAME = "redis.pool.maxWait";
	
	/**
	 *jedis调用borrowObject方法时，是否进行有效检查参数名称
	 */
	private static final String  REDIS_TESTONBORROW_NAME = "redis.pool.testOnBorrow";
	
	/**
	 *jedis调用returnObject方法时，是否进行有效检查参数名称
	 */
	private static final String  REDIS_TESTONRETURN_NAME = "redis.pool.testOnReturn";
	
	/**
	 *redis服务器密码参数名称
	 */
	private static final String  REDIS_AUTH_NAME = "redis.auth";
	
	/**
	 *redis服务器ip参数名称
	 */
	private static final String  REDIS_IP_NAME = "redis.ip";
	
	/**
	 *redis服务器端口号参数名称
	 */
	private static final String  REDIS_PORT_NAME = "redis.port";

	private Integer maxTotal;
	
	private Integer maxIdle;
	
	private Long maxWait;
	
	private Boolean testOnBorrow;
	
	private Boolean testOnReturn;
	
	private String auth;
	
	private String redisIp;

	private Integer redisPort;
	
	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Long maxWait) {
		this.maxWait = maxWait;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getRedisIp() {
		return redisIp;
	}

	public void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}

	public Integer getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(Integer redisPort) {
		this.redisPort = redisPort;
	}

	public RedisConfig(){
		this.init();
	}
	public void init(){
		try{
			InputStream is = this.getClass().getResourceAsStream(REDIS_FILE_NAME); 
			Properties propertie = PropertiesUtils.readProperties(is);
			String strMaxTotal = PropertiesUtils.getPropertieByKey(propertie, REDIS_MAXTOTAL_NAME);
			this.maxTotal = strMaxTotal == null?1000:Integer.valueOf(strMaxTotal);
			String strMaxIdle = PropertiesUtils.getPropertieByKey(propertie, REDIS_MAXIDLE_NAME);
			this.maxIdle = strMaxIdle == null?1000:Integer.valueOf(strMaxIdle);
			String strMaxWait =PropertiesUtils.getPropertieByKey(propertie, REDIS_MAXWAIT_NAME);
			this.maxWait = strMaxWait == null?10000l:Long.valueOf(strMaxWait);
			String strTestOnBorrow = PropertiesUtils.getPropertieByKey(propertie, REDIS_TESTONBORROW_NAME);
			this.testOnBorrow = strTestOnBorrow == null?true:Boolean.valueOf(REDIS_PORT_NAME);
			String strTestOnReturn = PropertiesUtils.getPropertieByKey(propertie, REDIS_TESTONRETURN_NAME);
			this.testOnReturn = strTestOnReturn == null?true:Boolean.valueOf(strTestOnReturn);
			this.auth = PropertiesUtils.getPropertieByKey(propertie, REDIS_AUTH_NAME);
			this.redisIp = PropertiesUtils.getPropertieByKey(propertie, REDIS_IP_NAME);
			String strRedisPort = PropertiesUtils.getPropertieByKey(propertie, REDIS_PORT_NAME);
			this.redisPort = strRedisPort == null?6379:Integer.valueOf(strRedisPort);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
