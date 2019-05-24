package org.fh.general.ecom.common.upload;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

public class ConnectionPool {
	private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
	
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;

	private FastDFSConfig config = new FastDFSConfig();
	

	private Object obj = new Object();

	private ConnectionPool() {
		busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
		idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(config.getSize());
		init(config.getSize());
	};

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getPoolInstance() {
		return instance;
	}

	private void init(int size) {
		initClientGlobal();
		TrackerServer trackerServer = null;
		try{
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			for(int i = 0; i < size; i++) {
				StorageServer storageServer = null;
				StorageClient1 client = new  StorageClient1(trackerServer,storageServer);
				idleConnectionPool.add(client);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			if(trackerServer != null) {
				try{
					trackerServer.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	


	public StorageClient1 checkout(int waitTimes)throws InterruptedException {  
		StorageClient1 client1 = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);  
		busyConnectionPool.put(client1, obj); 
		return client1;  
	}  

	
	public void checkin(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1) != null) {
			idleConnectionPool.add(client1);
		}
	}

	public void drop(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1) != null) {
			TrackerServer trackerServer = null;
			try {
				TrackerClient trackerClient = new TrackerClient();
				// TODO此处有内存泄露，因为trackerServer没有关闭连接
				trackerServer = trackerClient.getConnection();
				StorageServer storageServer = null;
				StorageClient1 newClient1 = new StorageClient1(trackerServer, storageServer);
				idleConnectionPool.add(newClient1);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (trackerServer != null) {
					try {
						trackerServer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 初始化fastdfs连接
	 */
	private void initClientGlobal() {
		InetSocketAddress[] trackerServers = new InetSocketAddress[1];
		trackerServers[0] = new InetSocketAddress(config.getIp(),config.getPort());
		ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));
		// 连接超时的时限，单位为毫秒
		ClientGlobal.setG_connect_timeout(config.getConnectTimeOut()); // 网络超时的时限，单位为毫秒
		ClientGlobal.setG_network_timeout(config.getNetworkTimeOut());
		ClientGlobal.setG_anti_steal_token(false); // 字符集
		ClientGlobal.setG_charset(config.getCharset());
		ClientGlobal.setG_secret_key(null);
	}

}

