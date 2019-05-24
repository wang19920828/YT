package org.fh.general.ecom.common.upload;

import java.io.File;
import java.io.IOException;

public interface UploadService {
	
	/**
	 * 上传文件
	 * @param file 文件 
	 * @return	文件存储路径
	 * @throws IOException
	 * @throws Exception
	 */
	public String uploadFile(File file) throws IOException, Exception;
	
	/**
	 * 上传文件
	 * @param file 文件
	 * @param name	 文件名称
	 * @return	文件存储路径
	 * @throws IOException
	 * @throws Exception
	 */
	public String uploadFile(File file, String name) throws IOException, Exception;
	
	/**
	 * 上传文件
	 * @param fileBuff	二进制数组
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileBuff, String name) throws IOException, Exception;
	
	
	/**
	 * 获取文件
	 * @param remoteFileName 文件路径+名称
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public byte[] getFile(String remoteFileName) throws IOException, Exception;
	
	/**
	 * 删除文件
	 * @param remoteFileName 文件路径+名称
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public void deleteFile(String remoteFileName)throws IOException, Exception;
}
