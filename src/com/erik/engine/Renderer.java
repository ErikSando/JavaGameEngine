package com.erik.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import com.erik.engine.gfx.Image;
import com.erik.engine.gfx.ImageTile;
import com.erik.engine.scene.GameObject;
import com.erik.engine.scene.Scene;
import com.erik.engine.scene.UIObject;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Renderer {
	private int pixelW, pixelH;
	private int[] pixels;
	private int colourKey;
	private int clearColour = Colour.Black;
	
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
	
	public void setPixelNoChecks(int x, int y, int value, double opacity) {
		if (value == colourKey) return;
		
		int r = (value & 0xff0000) >> 16;
		int g = (value & 0x00ff00) >> 8;
		int b = value & 0x0000ff;
		
		r *= opacity;
		g *= opacity;
		b *= opacity;

		int oldColour = pixels[x + y * pixelW];
		
		int newR = r + (int) Math.round((1 - opacity) * Colour.getR(oldColour));
		int newG = g + (int) Math.round((1 - opacity) * Colour.getG(oldColour));
		int newB = b + (int) Math.round((1 - opacity) * Colour.getB(oldColour));
		
		int newColour = Colour.fromRGB(newR, newG, newB);
		
		pixels[x + y * pixelW] = newColour;
	}
	
	public void setPixel(int x, int y, int value, double opacity) {
		if ((x < 0 || x >= pixelW || y < 0 || y >= pixelH) || value == colourKey) return;
		setPixelNoChecks(x, y, value, opacity);
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
					setPixelNoChecks(offsetX + _x, offsetY + _y, colour, opacity);
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
	
	public void outlineRectangle(int x, int y, int w, int h, int colour, double opacity, int thickness) {
		int leftX = x - thickness;
		int topY = y - thickness;
		int rightX = x + w;
		int bottomY = y + h;
		
		drawRectangle(leftX + thickness, topY, w + thickness, thickness, colour, opacity); // top left to top right
		drawRectangle(rightX, topY + thickness, thickness, h + thickness, colour, opacity); // top right to bottom right
		drawRectangle(leftX, bottomY, w + thickness, thickness, colour, opacity); // bottom left to bottom right
		drawRectangle(leftX, topY, thickness, h + thickness, colour, opacity); // top left to bottom left
	}

	public void outlineRectangle(double x, double y, int w, int h, int colour, double opacity, int thickness) {
		outlineRectangle((int) Math.round(x), (int) Math.round(y), w, h, colour, opacity, thickness);
	}
	
	public void drawGameObject(GameObject gameObject) {
		// if image: render it's image within it's bounds and with it's opacity
		// if no image: render it's rectangle with it's colour
		Vector2d pos = gameObject.position;
		Vector2i scale = gameObject.scale;
		
		if (gameObject.hasImage()) {
			drawImage(gameObject.getImage(), pos.x, pos.y);
			return;
		}
		
		drawRectangle(pos.x, pos.y, scale.x, scale.y, gameObject.colour, gameObject.getOpacity());
	}
	
	public void drawUIObject(UIObject uiObject) {
		Vector2i pos = uiObject.position;
		Vector2i scale = uiObject.scale;
		
		outlineRectangle(pos.x + camera.getPositioni().x, pos.y + camera.getPositioni().y, scale.x, scale.y, uiObject.outlineColour, uiObject.getOutlineOpacity(), uiObject.outlineThickness);
		drawRectangle(pos.x + camera.getPositioni().x, pos.y + camera.getPositioni().y, scale.x, scale.y, uiObject.backgroundColour, uiObject.getBackgroundOpacity());
	}

	public void render(Scene scene) {
		ArrayList<GameObject> gameObjects = scene.getGameObjects();
		ArrayList<UIObject> uiObjects = scene.getUIObjects();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			drawGameObject(gameObjects.get(i));
		}

		for (int i = 0; i < uiObjects.size(); i++) {
			drawUIObject(uiObjects.get(i));
		}
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
