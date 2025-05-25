package com.erik.engine;

import java.awt.Rectangle;

import com.erik.engine.gfx.Image;

public class GameObject {
	public Vector2 position;
	public Vector2 velocity = Vector2.zero();
	public Vector2 scale;
	public int colour = 0xffffff;
	
	private double opacity = 1.0;
	int layer = 0; // package private
	
	private Image image;
	private boolean hasImage = false;
	
	private Scene scene;
	//private int index;
	
	private double mass;
	
	public GameObject(Scene scene, float x, float y, int w, int h) {
		position = new Vector2(x, y);
		scale = new Vector2(w, h);
		scene.addGameObject(this);
	}
	
	public GameObject(Scene scene, Vector2 position, Vector2 scale) {
		this.position = position;
		this.scale = scale;
		scene.addGameObject(this);
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
		
		if (hasImage) {
			renderer.renderImage(image, (int) position.x, (int) position.y);
			return;
		}
		
		renderer.renderRectangle((int) position.x, (int) position.y, (int) scale.x, (int) scale.y, colour, opacity);
	}
	
	public void destroy() {
		scene.removeGameObject(this);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		if (image.getWidth() == scale.x && image.getHeight() == scale.y) {
			this.image = image;
			hasImage = true;
			return;
		}
		
		this.image = image.getScaled((int) scale.x, (int) scale.y);
		hasImage = true;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		if (layer < 0) return;
		scene.changeGameObjectLayer(this, layer);
	}
	
	public double getOpacity() {
		return opacity;
	}
	
	public void setOpacity(double opacity) {
		// not sure if i should clamp or ignore if opacity < 0 || opacity > 1
		this.opacity = Math.min(Math.max(opacity, 0.0), 1.0);
	}
	
	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		// not sure if i should clamp or ignore if mass < 0
		if (mass < 0.0) return;
		this.mass = mass;
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
