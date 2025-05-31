package com.erik.engine.shapes;

import com.erik.engine.vector2.Vector2d;

public class Rectd {
	public double x, y, w, h;
	
	public Rectd(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectd(Vector2d position, Vector2d scale) {
		x = position.x;
		y = position.y;
		w = scale.x;
		h = scale.y;
	}
	
	public Vector2d getPosition() {
		return new Vector2d(x, y);
	}
	
	public Vector2d getScale() {
		return new Vector2d(w, h);
	}
	
	public double left() {
		return x;
	}
	
	public double right() {
		return x + w;
	}
	
	public double top() {
		return y;
	}
	
	public double bottom() {
		return y + h;
	}
}
