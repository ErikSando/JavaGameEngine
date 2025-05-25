package com.erik.engine;

import java.awt.Rectangle;

public class GameObject {
	public Vector2 position;
	public Vector2 velocity = Vector2.zero();
	public Vector2 scale;
	public int colour = 0xffffff;
	public double opacity = 1.0;
	public int layer = 0;
	
	private Scene scene;
	//private int index;
	
	public GameObject(Scene scene, float x, float y, int w, int h) {
		//this.scene = scene;
		position = new Vector2(x, y);
		scale = new Vector2(w, h);
		this.scene.addGameObject(this);
	}
	
	public GameObject(Scene scene, Vector2 position, Vector2 scale) {
		//this.scene = scene;
		this.position = position;
		this.scale = scale;
		this.scene.addGameObject(this);
	}
	
	public GameObject(float x, float y, int w, int h) {
		position = new Vector2(x, y);
		scale = new Vector2(w, h);
	}
	
	public GameObject(Vector2 position, Vector2 scale) {
		this.position = position;
		this.scale = scale;
	}
	
	public void update(double delta) {
		
		position.add(velocity.multiplied(delta));
	}
	
	public void render(Renderer renderer) {
		//graphics.setColor(Color.white);
		//graphics.fillRect((int) position.x, (int) position.y, (int) scale.x, (int) scale.y);
		
		renderer.renderRectangle((int) position.x, (int) position.y, (int) scale.x, (int) scale.y, colour, opacity);
	}
	
	public void destroy() {
		scene.removeGameObject(this);
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		if (layer < 0) return;
		scene.changeGameObjectLayer(this, layer);
	}

	public Rectangle getRect() {
		return new Rectangle((int) position.x, (int) position.y, (int) scale.x, (int) scale.y);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
//	public int getIndex() {
//		return index;
//	}
//	
//	public void setIndex(int index) {
//		this.index = index;
//	}
}
