package com.erik.engine.shapes;

import com.erik.engine.vector2.Vector2i;

public class Recti {
	public int x, y, w, h;
	
	public Recti(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Recti(Vector2i position, Vector2i scale) {
		x = position.x;
		y = position.y;
		w = scale.x;
		h = scale.y;
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
