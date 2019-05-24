package org.fh.general.ecom.common.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class DESUtil {
    private static final String Algorithm = "DESede";
    private static final String Transformation = "DESede/CBC/PKCS5Padding";
    private static final String Iv = "";

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str =new DESUtil().encryptWithHex("123","456");
        System.out.println(str);
    }

    public String encryptWithHex(String key, String src)
            throws UnsupportedEncodingException
    {
        byte[] encodeByte = encryptMode(getKeyByte(key), src.getBytes());
        return byte2hex(encodeByte);
    }

    public String encryptWithBase64(String key, String src)
            throws UnsupportedEncodingException
    {
        byte[] destinate = encryptMode(getKeyByte(key), src.getBytes());
        return new Base64().encodeAsString(destinate);
    }

    public String decrypt4Hex(String key, String encryptData)
    {
        byte[] keybyte = null;
        try {
            keybyte = getKeyByte(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] src = hex2byte(encryptData.getBytes());
        return decryptMode(keybyte, src);
    }

    public String decrypt4Base64(String key, String encryptData)
    {

        String clearText = null;
        try {
            clearText = decryptMode(getKeyByte(key), new Base64().decode(encryptData));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return clearText;
    }

    private byte[] encryptMode(byte[] keybyte, byte[] src)
    {
        try
        {
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");

            IvParameterSpec iv = new IvParameterSpec("".getBytes());
            Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c1.init(1, deskey, iv);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    private String decryptMode(byte[] keybyte, byte[] src)
    {
        try
        {
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");

            IvParameterSpec iv = new IvParameterSpec("".getBytes());
            Cipher c1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c1.init(2, deskey, iv);
            byte[] data = c1.doFinal(src);
            return new String(data);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    private String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; ++n)
        {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }

    private byte[] getKeyByte(String key) throws UnsupportedEncodingException
    {
        byte[] data = key.getBytes();
        int len = data.length;
        byte[] newdata = new byte[24];
        System.arraycopy(data, 0, newdata, 0, (len > 24) ? 24 : len);
        return newdata;
    }

    private byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);

            b2[(n / 2)] = (byte)Integer.parseInt(item, 16);
        }
        return b2;
    }
}
