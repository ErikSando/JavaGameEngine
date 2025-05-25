package com.erik.engine;

import java.awt.image.DataBufferInt;

import com.erik.engine.gfx.Image;
import com.erik.engine.gfx.ImageTile;

public class Renderer {
	private int pixelW, pixelH;
	private int[] pixels;
	private int colourKey;
	private int clearColour = Colour.black;
	
	public Renderer(Window window, int colourKey) {
		pixelW = window.getWidth();
		pixelH = window.getHeight();
		pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
		this.colourKey = colourKey;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = clearColour;
		}
	}
	
	private void setPixelNoChecks(int x, int y, int value) {
		if (value == colourKey) return;
		pixels[x + y * pixelW] = value;
	}
	
	public void setPixel(int x, int y, int value) {
		if (x < 0 || x >= pixelW || y < 0 || y >= pixelH || value == colourKey) return;
		pixels[x + y * pixelW] = value;
	}
	
	public void addToPixelNoChecks(int x, int y, int value, double opacity) {
		if (value == colourKey) return;
		
		int r = (value & 0xff0000) >> 16;
		int g = (value & 0x00ff00) >> 8;
		int b = value & 0x0000ff;
		
		r *= opacity;
		g *= opacity;
		b *= opacity;
		
		int newColour = Colour.getColour(r, g, b);
		
		pixels[x + y * pixelW] += newColour;
	}
	
	public void addToPixel(int x, int y, int value, double opacity) {
		if ((x < 0 || x >= pixelW || y < 0 || y >= pixelH) || value == colourKey) return;
		addToPixelNoChecks(x, y, value, opacity);
	}
	
	public void renderImage(Image image, int x, int y) {
		if (x < -image.getWidth()) return;
		if (y < -image.getHeight()) return;
		if (x >= pixelW) return;
		if (y >= pixelH) return;
		
		int startX = 0;
		int startY = 0;
		int newW = image.getWidth();
		int newH = image.getHeight();
		
		if (x < 0) startX -= x;
		if (y < 0) startY -= y;
		if (newW + x > pixelW) newW -= (newW + x - pixelW);
		if (newH + y > pixelH) newH -= (newH + y - pixelH);

		int imgWidth = image.getWidth();
		int[] pixels = image.getPixels();
		
		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				if (pixels[_x + _y * imgWidth] < 0) {
					//System.out.println(pixels[_x + _y * imgWidth] + " below zero");
				}
				if (colourKey == pixels[_x + _y * imgWidth]) {
					//System.out.println(pixels[_x + _y * imgWidth] + " equals colour key");
				}
				setPixelNoChecks(x + _x, y + _y, pixels[_x + _y * imgWidth]);
			}
		}
	}
	
	public void renderImageTile(ImageTile image, int x, int y, int tileX, int tileY) {
		if (x < -image.getWidth() || y < -image.getHeight()) return;
		if (x >= pixelW || y >= pixelH) return;

		int startX = 0;
		int startY = 0;
		int newW = image.getWidth();
		int newH = image.getHeight();
		
		if (x < 0) startX -= x;
		if (y < 0) startY -= y;
		if (newW + x > pixelW) newW -= (newW + x - pixelW);
		if (newH + y > pixelH) newH -= (newH + y - pixelH);

		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int[] pixels = image.getPixels();

		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				setPixelNoChecks(_x + x, _y + y, pixels[_x + tileX * imgWidth + _y + tileY * imgHeight * imgWidth]);
			}
		}
	}
	
	public void renderRectangle(int x, int y, int w, int h, int colour, double opacity) {
		if (x < -w || y < -h) return;
		if (x >= pixelW || y >= pixelH) return;
		if (opacity == 0.0) return;
		
		int startX = 0;
		int startY = 0;
		int newW = w;
		int newH = h;
		
		if (x < 0) startX -= x;
		if (y < 0) startY -= y;
		if (newW + x > pixelW) newW -= (newW + x - pixelW);
		if (newH + y > pixelH) newH -= (newH + y - pixelH);
		
		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				if (opacity < 1.0) {
					addToPixelNoChecks(x + _x, y + _y, colour, opacity);
				}
				else {
					setPixelNoChecks(x + _x, y + _y, colour);
				}
			}
		}
	}
	
	public void renderGameObject(GameObject gameObject) {
		// if image: render it's image within it's bounds and with it's opacity
		// if no image: render it's rectangle with it's colour
	}

	public int getColourKey() {
		return colourKey;
	}

	public void setColourKey(int colourKey) {
		this.colourKey = colourKey;
	}
	
	public int getClearColour() {
		return clearColour;
	}
	
	public void setClearColour(int colour) {
		clearColour = colour;
	}
}
