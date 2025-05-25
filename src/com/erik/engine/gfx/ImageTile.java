package com.erik.engine.gfx;

public class ImageTile extends Image {
	private int width, height;
	
	public ImageTile(String path, int width, int height) {
		super(path);
		
		this.width = width;
		this.height = height;
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
}
