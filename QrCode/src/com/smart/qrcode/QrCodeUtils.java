package com.smart.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;


public class QrCodeUtils {

	public static void main(String[] args) {
		String content="老谈咋不去开会";
		//String content="qr码";
		int version=7;
		String logopath="D:\\logo1.png";//logo的文件路径
		String outpath="D:\\test.jpg";//文件输出路径
		qrCode(content,version,logopath,outpath);
		jiexi(outpath);
	}
	static void qrCode(String content,int version,String logopath,String outpath){
		Qrcode qrcode=new Qrcode();
		//容错率 L(7%)、M(15%)、Q(25%)、H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		//Numeric数字 Alphanumeric 英文字母 Binary 二进制 Kanji 汉字
		qrcode.setQrcodeEncodeMode('B');
		//1-40 1:21x21 40:177x177  4增长
		qrcode.setQrcodeVersion(version);
		int size=12*(version-1)+67;
		BufferedImage image=new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d=image.createGraphics();
		graphics2d.setBackground(Color.WHITE);
		graphics2d.clearRect(0, 0, size, size);
		graphics2d.setColor(Color.black);
		
		byte[] contentBytes = null;
		try {
			contentBytes = content.getBytes("gbk");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		boolean[][]codeOut=qrcode.calQrcode(contentBytes);
		for(int i=0;i<codeOut.length;i++){
			for(int j=0;j<codeOut.length;j++){
				if(codeOut[j][i]){
					graphics2d.fillRect(j*3+2, i*3+2, 3, 3);
				}
			}
		}
		
		try {
			Image logo=ImageIO.read(new File(logopath));
			int logowidth=logo.getWidth(null);
			int logoheight=logo.getHeight(null);
			int x=(size-logowidth)/2;
			int y=(size-logoheight)/2;
			graphics2d.drawImage(logo, x, y, logowidth, logoheight, null);
			image.flush();
			graphics2d.dispose();
			ImageIO.write(image, "JPEG", new File(outpath));
			System.out.println("生成二维码成功！");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("生成二维码失败！");
		}
	}
	
	static void jiexi(String outpath){
		File file=new File(outpath);
		BufferedImage bImage=null;
		String decodeData=null;
		try {
			bImage=ImageIO.read(file);
			QRCodeDecoder decoder=new QRCodeDecoder();
			decodeData=new String(decoder.decode(new J2SEImage(bImage)),"gbk");
			System.out.println(decodeData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
