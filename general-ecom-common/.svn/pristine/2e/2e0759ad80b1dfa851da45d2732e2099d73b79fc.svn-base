package org.fh.general.ecom.common.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

//import org.recruit.base.image.JPEGCodec;
//import org.recruit.base.image.JPEGImageEncoder;

public class ImageUtil {

	private static int getRandom(int count) {
		return (int) Math.round(Math.random() * (count));
	}

	@SuppressWarnings("unused")
	private static String getRandomString() {
		String string = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < 30; i++) {
			sb.append(string.charAt(getRandom(len - 1)));
		}
		return sb.toString();
	}

	// 图片变小了,小图片四周是黑色的，可以使用
	public static byte[] reduceImg2(
			BufferedImage image) {/*
									 * byte b[]=null; File file =null; try {
									 * String webRoot =
									 * System.getProperty("springmvc.root");
									 * String ends = ""; if
									 * (!webRoot.endsWith("/") &&
									 * !webRoot.endsWith("\\")) { ends = "/"; }
									 * webRoot+=ends+getRandomString()+".png";
									 * int widthdist = image.getWidth(); int
									 * heightdist = image.getHeight();
									 * 
									 * BufferedImage tag= new
									 * BufferedImage((int) widthdist, (int)
									 * heightdist, BufferedImage.SCALE_DEFAULT);
									 * 
									 * tag.getGraphics().drawImage(image.
									 * getScaledInstance(widthdist, heightdist,
									 * Image.SCALE_DEFAULT), 0, 0, null);
									 * FileOutputStream out = new
									 * FileOutputStream(webRoot);
									 * JPEGImageEncoder encoder =
									 * JPEGCodec.createJPEGEncoder(out);
									 * encoder.encode(tag); out.close(); file
									 * =new File(webRoot); FileInputStream in =
									 * null; in = new FileInputStream(file); b =
									 * new byte[in.available()]; in.read(b);
									 * in.close(); file.delete(); } catch
									 * (IOException ex) { ex.printStackTrace();
									 * }
									 * 
									 * return b;
									 */
		return null;
	}

	// 图片变小了，可以使用比reduceImg2好一点
	public static byte[] reduceImg(BufferedImage image) {
		byte[] b = null;
		try {
			// ByteArrayInputStream in = new ByteArrayInputStream(inputByte);
			// //将b作为输入流；
			// BufferedImage image = ImageIO.read(in);
			Image img = image;
			int widthdist = image.getWidth();
			int heightdist = image.getHeight();

			BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(img.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(tag, "jpg", out);// png>gif>jpg=jpeg
			b = out.toByteArray();
			out.flush();
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return b;
	}

	@SuppressWarnings("unused")
	private static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

}
