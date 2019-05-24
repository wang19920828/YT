package org.fh.general.ecom.common.upload;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;


public class UploadServiceImpl implements UploadService {
	
	private FastDFSConfig config = new FastDFSConfig();
	
	public UploadServiceImpl() {
		init();
	}

	@Override
	public String uploadFile(File file) throws IOException, Exception {
		return uploadFile(file,file.getName());
	}

	@Override
	public String uploadFile(File file, String name) throws IOException, Exception {
		byte[] fileBuff = getFileBuffer(file);
		String fileExtName = getFileExtName(name);
		return send(fileBuff, fileExtName); 
	}

	@Override
	public String uploadFile(byte[] fileBuff, String name) throws IOException, Exception {
		String fileExtName = getFileExtName(name);
		return send(fileBuff, fileExtName); 
	}
	
	private String send(byte[] fileBuff, String fileExtName) throws Exception {
		String upPath = null; 
		StorageClient1 client = null; 
		try{
			client = ConnectionPool.getPoolInstance().checkout(100);
			upPath = client.upload_file1(fileBuff, fileExtName, null);
			ConnectionPool.getPoolInstance().checkin(client); 
		}catch(Exception e){
			ConnectionPool.getPoolInstance().drop(client);
			e.printStackTrace();
			throw e; 
		}
		return upPath;
	}

	private String getFileExtName(String name) {
		String extName = null;
		if (name != null && name.contains(".")) {
			extName = name.substring(name.lastIndexOf(".") + 1);
		}
		return extName;
	}

	@SuppressWarnings("resource")
	private byte[] getFileBuffer(File file) {
		byte[] fileByte = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			fileByte = new byte[fis.available()];
			fis.read(fileByte);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileByte;
	}

	
	private void init() {
		InetSocketAddress[] trackerServers = new InetSocketAddress[1];
		trackerServers[0] = new InetSocketAddress(config.getIp(), config.getPort());
		ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));
		// 连接超时的时限，单位为毫秒
		ClientGlobal.setG_connect_timeout(config.getConnectTimeOut()); // 网络超时的时限，单位为毫秒
		ClientGlobal.setG_network_timeout(config.getNetworkTimeOut());
		ClientGlobal.setG_anti_steal_token(false); // 字符集
		ClientGlobal.setG_charset(config.getCharset());
		ClientGlobal.setG_secret_key(null);
	}
	
	@Override
	public byte[] getFile(String remoteFileName) throws IOException, Exception {
		StorageClient1 client = null; 
		byte[] file = null;
		try{
			client = ConnectionPool.getPoolInstance().checkout(10);
			file = client.download_file1(remoteFileName);
			ConnectionPool.getPoolInstance().checkin(client); 
		}catch(Exception e){
			ConnectionPool.getPoolInstance().drop(client);
			e.printStackTrace();
			throw e; 
		}
		return file;
	}

	@Override
	public void deleteFile(String remoteFileName) throws IOException, Exception {
		StorageClient1 client = null; 
		try{
			client = ConnectionPool.getPoolInstance().checkout(10);
			client.delete_file1(remoteFileName);
			ConnectionPool.getPoolInstance().checkin(client); 
		}catch(Exception e){
			ConnectionPool.getPoolInstance().drop(client);
			e.printStackTrace();
			throw e; 
		}
	}
	
}
