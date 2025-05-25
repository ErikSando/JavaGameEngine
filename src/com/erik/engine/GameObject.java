package com.erik.engine;

import java.util.ArrayList;

import com.erik.engine.events.CollisionEventSource;
import com.erik.engine.gfx.Image;
import com.erik.engine.shapes.Rectangle;
import com.erik.engine.utils.Utils;

public class GameObject {
	public Vector2 position;
	public Vector2 velocity = Vector2.zero();
	public Vector2 acceleration = Vector2.zero();
	public Vector2 scale;
	public int colour = 0xffffff;
	
	private double opacity = 1.0;
	int layer = 0; // package private
	
	protected Image image;
	protected boolean hasImage = false;
	
	protected Scene scene;
	//private int index;
	
	protected double terminalVelocity;
	protected double mass;
	
	public boolean update = true;
	public boolean anchored = false;
	public boolean canCollide = true;
	public boolean canTouch = true;
	
	public String tag = "none";
	
	public CollisionEventSource collisionEnter = new CollisionEventSource();
	public CollisionEventSource collisionExit = new CollisionEventSource();
	
	public CollisionEventSource touchEnter = new CollisionEventSource();
	public CollisionEventSource touchExit = new CollisionEventSource();
	
	public GameObject(Scene scene, float x, float y, int w, int h) {
		position = new Vector2(x, y);
		scale = new Vector2(w, h);
		scene.addGameObject(this);
		setMass(1.0);
	}
	
	public GameObject(Scene scene, Vector2 position, Vector2 scale) {
		this.position = position;
		this.scale = scale;
		scene.addGameObject(this);
		setMass(1.0);
	}
	
	public GameObject(float x, float y, int w, int h) {
		position = new Vector2(x, y);
		scale = new Vector2(w, h);
		setMass(1.0);
	}
	
	public GameObject(Vector2 position, Vector2 scale) {
		this.position = position;
		this.scale = scale;
		setMass(1.0);
	}
	
	public void update(double delta, ArrayList<GameObject> gameObjects) {
		if (!update) return;
		
		if (!anchored) {
			acceleration.y = scene.gravity;
		
			velocity.add(acceleration.multiplied(delta));
			
			if (velocity.y > terminalVelocity) {
				velocity.y = terminalVelocity;
			}
		}
		
		Rectangle hitbox = getRectangle();
		Rectangle nextFrameX = new Rectangle(position.plus(new Vector2(velocity.x * delta, 0.0)), scale);
		Rectangle nextFrameY = new Rectangle(position.plus(new Vector2(0.0, velocity.y * delta)), scale);
		
		for (GameObject gameObject : gameObjects) {
			if (gameObject == this) continue;
			
			boolean willCollide = canCollide && gameObject.canCollide;
			
			boolean colliding = false;
			boolean touching = false;
			
			Rectangle otherRect = gameObject.getRectangle();
			
			if (Utils.RectangleIntersection(nextFrameX, otherRect)) {
				if (canTouch) touching = true;
				
				if (willCollide) {
					colliding = true;
					
					if (velocity.x > 0) {
						velocity.x = (otherRect.left() - hitbox.right()) / delta;
					}
					else if (velocity.x < 0) {
						velocity.x = (otherRect.right() - hitbox.left()) / delta;
					}
				}
			}
			
			if (Utils.RectangleIntersection(nextFrameY, otherRect)) {
				if (canTouch) touching = true;
				
				if (willCollide) {
					colliding = true;
					
					if (velocity.y >= 0) {
						velocity.y = (otherRect.top() - hitbox.bottom()) / delta;
					}
					else if (velocity.y < 0) {
						velocity.y = (otherRect.bottom() - hitbox.top()) / delta;
					}
				}
			}
		}
		
		position.add(velocity.multiplied(delta));
	}
	
	public void draw(Renderer renderer) {
		//graphics.setColor(Color.white);
		//graphics.fillRect((int) position.x, (int) position.y, (int) scale.x, (int) scale.y);
		
		if (hasImage) {
			renderer.drawImage(image, (int) position.x, (int) position.y);
			return;
		}
		
		renderer.drawRectangle((int) position.x, (int) position.y, (int) scale.x, (int) scale.y, colour, opacity);
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
		
		terminalVelocity = 2000 * Math.sqrt(scene.gravity * mass / scale.x);
	}

	public Rectangle getRectangle() {
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
