package com.erik.engine;

import com.erik.engine.vector2.Vector2;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Camera {
	public Vector2d position = Vector2d.zero();
	public double zoom = 1.0;
	
	public Camera() {}
	
	public Camera(double x, double y) {
		position = new Vector2d(x, y);
	}
	
	public Camera(Vector2d position) {
		this.position = position;
	}
	
	public Vector2i getPositioni() {
		return Vector2.toi(position);
	}
	
	public Vector2i centre() {
		return Vector2.toi(position.plus(new Vector2d(960.0, 540.0).divided(2.0)));
	}
}
