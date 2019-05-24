package org.fh.general.ecom.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class FileUtils {

	private static String filenameTemp;

	/**
	 * 判断多级路径是否存在，不存在就创建
	 * 
	 * @param filePath
	 */
	public static void isExist(String filePath) {
		String paths[] = filePath.split("/");
		String dir = paths[0];
		for (int i = 0; i < paths.length - 2; i++) {
			try {
				dir = dir + "/" + paths[i + 1];
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdir();
				}
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}

	/**
	 * 根据文件获取文件后缀
	 * 
	 * @param fileName
	 */
	public static String getFileType(File file) {
		return getFileType(file.getName());
	}

	/**
	 * 根据文件名称获取文件后缀
	 * 
	 * @param fileName
	 */
	public static String getFileType(String fileName) {
		return StringUtils.substringAfterLast(fileName, ".");
	}

	/**
	 * 根据文件名随机生成uuid文件名称
	 */
	public static String uuidFileName(String fileName) {
		return UUID.randomUUID() + "." + getFileType(fileName);
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static void deleteFile(File file) {
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * 根据文件路径获取输出流
	 * 
	 * @param dir
	 * @return
	 */
	public static OutputStream getOutputStream(String dir) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(dir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return outputStream;
	}

	/**
	 * 将字节数组写到路径文件中
	 * 
	 * @param fileByte
	 * @param dir
	 */
	public static boolean writeFile(byte[] fileByte, String dir) {
		boolean writeFlag = false;
		OutputStream outputStream = getOutputStream(dir);
		if (null != outputStream) {
			try {
				outputStream.write(fileByte);
				writeFlag = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writeFlag;
	}

	/**
	 * 创建文件
	 * 全路径加上文件名称    D:/home/corpus/caffe.txt
	 * @throws IOException
	 */
	public static boolean creatTxtFile(String pathName) throws IOException {
		boolean flag = false;
//		filenameTemp = path + name + ".txt";
		filenameTemp = pathName;
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		return flag;
	}

	/**
	 * 写文件
	 * 
	 * @param newStr
	 *            新内容
	 * @throws IOException
	 */
	public static boolean writeTxtFile(String newStr) throws IOException {
		// 先读取原有文件内容，然后进行写入操作
		boolean flag = false;
		String filein = newStr + "\r\n";
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// 文件路径
			File file = new File(filenameTemp);
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该文件原有的内容
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
				// System.getProperty("line.separator")
				// 行与行之间的分隔符 相当于“\n”
				buf = buf.append(System.getProperty("line.separator"));
			}
			buf.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return flag;
	}

}
