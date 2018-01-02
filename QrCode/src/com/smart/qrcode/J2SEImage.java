package com.smart.qrcode;


import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class J2SEImage implements QRCodeImage{
	BufferedImage image;
	J2SEImage(BufferedImage image){
		this.image=image;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return image.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		
		return image.getRGB(x, y);
	}

}
