package org.fh.general.ecom.common.utils;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;




/**
 * 
 * <p>Title:BarCodeUtil </p>
 * <p>Description:条形码生成工具类 </p>
 * @Copyright:Copyright (c) 2015
 * @Author:PJJ
 * @Version:1.0
 * @date 2016年6月29日 下午2:43:06
 * @since    JDK 1.7
 */
public class BarCodeUtil {
	
//    private static final long serialVersionUID = 1L;
//    
//    private static final String KEY = "keycode";
//    
//    private static final String WIDTH = "mwidth";
//    
//    private static final String HEIGHT = "mheight";
//    
//    private static final String IMAGETYPE = "JPEG";


    public static BufferedImage doGet(String keycode) throws Exception {
        if (keycode != null && !"".equals(keycode)) {
            try {
                Code128Writer writer = new Code128Writer();
                int width = 180;
                int height = 60;
                String mwidth = "45";
                if (mwidth != null && !"".equals(mwidth.trim())) {
                    try {
                        width = Integer.valueOf(mwidth);
                    } catch (NumberFormatException e) {
                        //TODO output to log
                    }
                }
                String mheight = "20";
                if (mheight != null && !"".equals(mheight.trim())) {
                    try {
                        height = Integer.valueOf(mheight);
                    } catch (NumberFormatException e) {
                        //TODO output to log
                    }
                }
                BitMatrix m = writer.encode(keycode, BarcodeFormat.CODE_128, width, height);
                return createImage(m);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
		return null;
    }

    public static BufferedImage createImage(BitMatrix bitMatrix) throws Exception {
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000  
                        : 0xFFFFFFFF);  
            }  
        }  
        return image;
    }
}