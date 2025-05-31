package com.erik.engine;

import java.awt.image.DataBufferInt;

import com.erik.engine.gfx.Image;
import com.erik.engine.gfx.ImageTile;
import com.erik.engine.vector2.Vector2i;

public class Renderer {
	private int pixelW, pixelH;
	private int[] pixels;
	private int colourKey;
	private int clearColour = Colour.black;
	
	private Camera camera = new Camera();
	
	public Renderer(Window window, int colourKey) {
		pixelW = window.getWidth();
		pixelH = window.getHeight();
		pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
		this.colourKey = colourKey;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
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
	
	public void drawImage(Image image, int x, int y) {
		Vector2i camPos = camera.getPositioni();
		int offsetX = x - camPos.x;
		int offsetY = y - camPos.y;
		
		if (offsetX < -image.getWidth() || offsetY < -image.getHeight()) return;
		if (offsetX >= pixelW || offsetY >= pixelH) return;
		
		int startX = 0;
		int startY = 0;
		int newW = image.getWidth();
		int newH = image.getHeight();
		
		if (offsetX < 0) startX -= offsetX;
		if (offsetY < 0) startY -= offsetY;
		if (newW + offsetX > pixelW) newW -= (newW + offsetX - pixelW);
		if (newH + offsetY > pixelH) newH -= (newH + offsetY - pixelH);

		int imgWidth = image.getWidth();
		int[] pixels = image.getPixels();
		
		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				setPixelNoChecks(offsetX + _x, offsetY + _y, pixels[_x + _y * imgWidth]);
			}
		}
	}
	
	public void drawImage(Image image, double x, double y) {
		drawImage(image, (int) Math.round(x), (int) Math.round(y));
	}
	
	public void drawImageTile(ImageTile image, int x, int y, int tileX, int tileY) {
		Vector2i camPos = camera.getPositioni();
		int offsetX = x - camPos.x;
		int offsetY = y - camPos.y;
		
		if (offsetX < -image.getWidth() || offsetY < -image.getHeight()) return;
		if (offsetX >= pixelW || offsetY >= pixelH) return;

		int startX = 0;
		int startY = 0;
		int newW = image.getWidth();
		int newH = image.getHeight();
		
		if (offsetX < 0) startX -= offsetX;
		if (offsetY < 0) startY -= offsetY;
		if (newW + offsetX > pixelW) newW -= (newW + offsetX - pixelW);
		if (newH + offsetY > pixelH) newH -= (newH + offsetY - pixelH);

		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int[] pixels = image.getPixels();

		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				setPixelNoChecks(_x + offsetX, _y + offsetY, pixels[_x + tileX * imgWidth + _y + tileY * imgHeight * imgWidth]);
			}
		}
	}
	
	public void drawImageTile(ImageTile image, double x, double y) {
		drawImage(image, (int) Math.round(x), (int) Math.round(y));
	}
	
	public void drawRectangle(int x, int y, int w, int h, int colour, double opacity) {
		Vector2i camPos = camera.getPositioni();
		int offsetX = x - camPos.x;
		int offsetY = y - camPos.y;
		
		if (offsetX < -w || offsetY < -h) return;
		if (offsetX >= pixelW || offsetY >= pixelH) return;
		if (opacity == 0.0) return;
		
		int startX = 0;
		int startY = 0;
		int newW = w;
		int newH = h;
		
		if (offsetX < 0) startX -= offsetX;
		if (offsetY < 0) startY -= offsetY;
		if (newW + offsetX > pixelW) newW -= (newW + offsetX - pixelW);
		if (newH + offsetY > pixelH) newH -= (newH + offsetY - pixelH);
		
		for (int _x = startX; _x < newW; _x++) {
			for (int _y = startY; _y < newH; _y++) {
				if (opacity < 1.0) {
					addToPixelNoChecks(offsetX + _x, offsetY + _y, colour, opacity);
				}
				else {
					setPixelNoChecks(offsetX + _x, offsetY + _y, colour);
				}
			}
		}
	}
	
	public void drawRectangle(double x, double y, int w, int h, int colour, double opacity) {
		drawRectangle((int) Math.round(x), (int) Math.round(y), w, h, colour, opacity);
	}
	
	public void drawGameObject(GameObject gameObject) {
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
