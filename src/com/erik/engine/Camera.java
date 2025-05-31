package com.erik.engine;

import com.erik.engine.vector2.Vector2i;

public class Camera {
	public Vector2i position = Vector2i.zero();
	public double zoom = 1.0;
	
	public Camera() {}
	
	public Camera(int x, int y) {
		position = new Vector2i(x, y);
	}
	
	public Camera(Vector2i position) {
		this.position = position;
	}
	
	public Vector2i centre() {
		return position.plus(new Vector2i(960, 540).divided(2.0));
	}
}
