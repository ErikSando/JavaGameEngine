package com.erik.engine.shapes;

import com.erik.engine.vector2.Vector2i;

public class Rectangle {
	public int x, y, w, h;
	
	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectangle(Vector2i position, Vector2i scale) {
		x = (int) position.x;
		y = (int) position.y;
		w = (int) scale.x;
		h = (int) scale.y;
	}
	
	public Vector2i getPosition() {
		return new Vector2i(x, y);
	}
	
	public Vector2i getScale() {
		return new Vector2i(w, h);
	}
	
	public int left() {
		return x;
	}
	
	public int right() {
		return x + w;
	}
	
	public int top() {
		return y;
	}
	
	public int bottom() {
		return y + h;
	}
}
