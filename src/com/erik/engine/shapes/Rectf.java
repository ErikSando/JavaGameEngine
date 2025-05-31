package com.erik.engine.shapes;

import com.erik.engine.vector2.Vector2f;

public class Rectf {
	public float x, y, w, h;
	
	public Rectf(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectf(Vector2f position, Vector2f scale) {
		x = position.x;
		y = position.y;
		w = scale.x;
		h = scale.y;
	}
	
	public Vector2f getPosition() {
		return new Vector2f(x, y);
	}
	
	public Vector2f getScale() {
		return new Vector2f(w, h);
	}
	
	public float left() {
		return x;
	}
	
	public float right() {
		return x + w;
	}
	
	public float top() {
		return y;
	}
	
	public float bottom() {
		return y + h;
	}
}
