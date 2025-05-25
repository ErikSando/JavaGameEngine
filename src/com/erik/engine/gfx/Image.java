package com.erik.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private int width, height;
	private int[] pixels;
	
	public double opacity = 1.0;
	public int layer = 0;
	
	public Image(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new FileInputStream("res/" + path));	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		image.flush();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
}
