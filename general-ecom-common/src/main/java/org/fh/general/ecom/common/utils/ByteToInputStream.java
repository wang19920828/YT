package org.fh.general.ecom.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteToInputStream {

	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	public static final byte[] input2byte(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
	public static final byte[] input3byte(InputStream input) throws IOException{
		 byte[] byt = new byte[input.available()];
		 input.read(byt);
		 return byt;
		
	}
	
	public static byte[] getBytes(InputStream is) throws IOException {
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    int len;
	    byte[] data = new byte[100];
	    while ((len = is.read(data, 0, 100)) != -1) {
	    buffer.write(data, 0, len);
	    }

	    buffer.flush();
	    return buffer.toByteArray();
	}

}
