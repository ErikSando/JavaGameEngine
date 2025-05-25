package com.erik.engine;

public class Camera {
	public Vector2 position = Vector2.zero();
	public double zoom = 1.0;
	
	public Camera() {}
	
	public Camera(int x, int y) {
		position = new Vector2(x, y);
	}
	
	public Camera(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 centre() {
		return position.plus(new Vector2(960, 540).divided(2.0));
	}
}
