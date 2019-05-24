package org.fh.general.ecom.common.mongo;

import org.fh.general.ecom.common.utils.PropertiesUtils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author huliping
 * @DATE 2018/9/25
 **/
public class MongoConfig {
    /**
     * mongo配置文件路径
     */
    private static final String  MONGO_NAME = "/props/config.properties";

    /**
     * mongo连接IP参数名称
     */
    private static final String MONGO_HOST_NAME = "mongo.host";

    /**
     *  mongo连端口参数名称
     */
    private static final String MONGO_PORT_NAME = "mongo.port";

    /**
     * mongo连接数参数名称
     */
    private static final String  MONGO_POOLSIZE_NAME = "mongo.poolsize";

    /**
     *  mongo等待队列长度参数名称
     */
    private static final String  MONGO_BLOCKSIZE_NAME = "mongo.blocksize";

    private String host;

    private int port;

    private int poolSize;

    private int blockSize;
    public MongoConfig(){
        this.init();
    }

    public String getHost() {
        return host;
    }




    public void setHost(String host) {
        this.host = host;
    }




    public int getPort() {
        return port;
    }




    public void setPort(int port) {
        this.port = port;
    }




    public int getPoolSize() {
        return poolSize;
    }




    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }




    public int getBlockSize() {
        return blockSize;
    }




    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }




    /**
     * 赋值MEMCACHE相关参数
     */
    private void init(){
        try{
            InputStream is = this.getClass().getResourceAsStream(MONGO_NAME);
            Properties propertie = PropertiesUtils.readProperties(is);
            this.host = PropertiesUtils.getPropertieByKey(propertie, MONGO_HOST_NAME);
            String strPort = PropertiesUtils.getPropertieByKey(propertie, MONGO_PORT_NAME);
            this.port = strPort == null?27017:Integer.valueOf(strPort);
            String strPoolSize = PropertiesUtils.getPropertieByKey(propertie, MONGO_POOLSIZE_NAME);
            this.poolSize = strPoolSize == null?100:Integer.valueOf(strPoolSize);
            String strBlockSize = PropertiesUtils.getPropertieByKey(propertie, MONGO_BLOCKSIZE_NAME);
            this.blockSize = strBlockSize == null? 100:Integer.valueOf(strBlockSize);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
