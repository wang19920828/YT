package org.fh.general.ecom.common.upload;



import org.fh.general.ecom.common.utils.PropertiesUtils;

import java.util.Properties;
import java.io.InputStream;




public class FastDFSConfig {
	/**
	 * fastdfs配置文件路径
	 */
	private static final String  FASTDFS_FILE_NAME = "/props/config.properties";
	
	/**
	 * 连接数个数参数名称
	 */
	private static final String POOL_SIZE_NAME = "pool_size";
	
	/**
	 *  fastdfs连接IP参数名称
	 */
	private static final String  FASTDFS_IP_NAME = "fastdfs_ip";
	
	/**
	 *  fastdfs连接端口参数名称
	 */
	private static final String  FASTDFS_PORT_NAME = "fastdfs_port";
	
	/**
	 * fastdfs字符集编码参数名称
	 */
	private static final String  FASTDFS_CHARSET_NAME = "fastdfs_charset";
	/**
	 * fastdfsl连接超时参数名称
	 */
	private static final String  CONNECT_TIMEOUT_NAME = "connect_timeout";
	/**
	 * fastdfs处理超时参数名称
	 */
	private static final String  NETWORK_TIMEOUT_NAME = "network_timeout";
	private static final String  FASTDFS_URL = "fastdfs_url";
	
	private int size;
	
	private String ip;

	private int port;
	
	private String charset;
	
	private int connectTimeOut;
	
	private int networkTimeOut;
	
	private String fastdfsUrl;
	
	public String getFastdfsUrl() {
		return fastdfsUrl;
	}

	public void setFastdfsUrl(String fastdfsUrl) {
		this.fastdfsUrl = fastdfsUrl;
	}

	public FastDFSConfig(){
		init();
	}
	
	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public String getCharset() {
		return charset;
	}



	public void setCharset(String charset) {
		this.charset = charset;
	}



	public int getConnectTimeOut() {
		return connectTimeOut;
	}



	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}



	public int getNetworkTimeOut() {
		return networkTimeOut;
	}



	public void setNetworkTimeOut(int networkTimeOut) {
		this.networkTimeOut = networkTimeOut;
	}



	/**
	 * 赋值连接池相关参数
	 */
	private void init(){
		try{
			InputStream is = this.getClass().getResourceAsStream(FASTDFS_FILE_NAME); 
			Properties propertie = PropertiesUtils.readProperties(is);
			String strSize = PropertiesUtils.getPropertieByKey(propertie, POOL_SIZE_NAME);
			this.size = strSize==null?20:Integer.valueOf(strSize);
			this.ip = PropertiesUtils.getPropertieByKey(propertie, FASTDFS_IP_NAME);
			String strPort = PropertiesUtils.getPropertieByKey(propertie, FASTDFS_PORT_NAME);
			this.port = strPort == null?80:Integer.valueOf(strPort);
			this.charset = PropertiesUtils.getPropertieByKey(propertie, FASTDFS_CHARSET_NAME);
			String strConnectOut = PropertiesUtils.getPropertieByKey(propertie, CONNECT_TIMEOUT_NAME);
			this.connectTimeOut = strConnectOut == null? 20000:Integer.valueOf(strConnectOut);
			String strNetworkOut = PropertiesUtils.getPropertieByKey(propertie, NETWORK_TIMEOUT_NAME);
			this.fastdfsUrl = PropertiesUtils.getPropertieByKey(propertie, FASTDFS_URL);
			this.networkTimeOut = strNetworkOut == null? 50000:Integer.valueOf(strNetworkOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
