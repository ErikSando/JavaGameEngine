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
			process(image);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image(BufferedImage image) {
		process(image);
	}
	
	private void process(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		// remove alpha channel
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] &= 0x00ffffff;
		}
		
		image.flush();
	}
	
	public Image getScaled(int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		int w = getWidth();
		int h = getHeight();
		
		for (int x = 0; x < width; x++) {
			int newX = x * w / width;
			
			for (int y = 0; y < height; y++) {
				int newY = y * h / height;
				int colour = pixels[newX + newY * w];
				newImage.setRGB(x, y, colour);
			}
		}
		
		return new Image(newImage);
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
