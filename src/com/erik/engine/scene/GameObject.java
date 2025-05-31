package com.erik.engine.scene;

import java.util.ArrayList;

import com.erik.engine.Colour;
import com.erik.engine.Renderer;
import com.erik.engine.events.CollisionEventSource;
import com.erik.engine.gfx.Image;
import com.erik.engine.shapes.Rectd;
import com.erik.engine.utils.Utils;
import com.erik.engine.vector2.Vector2;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class GameObject {
	public Vector2d position;
	public Vector2d velocity = Vector2d.zero();
	public Vector2d acceleration = Vector2d.zero();
	public Vector2i scale;
	public int colour = Colour.White;
	
	protected double opacity = 1.0;
	int layer = 0; // package private
	
	protected Image image;
	protected boolean hasImage = false;
	protected boolean hasAnimation = false;
	
	protected Scene scene;
	//private int index;
	
	protected double terminalVelocity;
	protected double mass;
	
	public boolean update = true;
	public boolean useGravity = true;
	public boolean canCollide = true;
	public boolean canTouch = true;
	
	private boolean collisionLeft = false;
	private boolean collisionRight = false;
	private boolean collisionAbove = false;
	private boolean collisionBelow = false;
	
	private boolean collisionLeftNext = false;
	private boolean collisionRightNext = false;
	private boolean collisionAboveNext = false;
	private boolean collisionBelowNext = false;
	
	public String tag = "none";
	
	public CollisionEventSource collisionEnter = new CollisionEventSource();
	public CollisionEventSource collisionExit = new CollisionEventSource();
	
	public CollisionEventSource touchEnter = new CollisionEventSource();
	public CollisionEventSource touchExit = new CollisionEventSource();
	
	public GameObject(Scene scene, double x, double y, int w, int h) {
		position = new Vector2d(x, y);
		scale = new Vector2i(w, h);
		scene.addGameObject(this);
		setMass(1.0);
	}
	
	public GameObject(Scene scene, Vector2d position, Vector2i scale) {
		this.position = position;
		this.scale = scale;
		scene.addGameObject(this);
		setMass(1.0);
	}
	
	public GameObject(double x, double y, int w, int h) {
		position = new Vector2d(x, y);
		scale = new Vector2i(w, h);
		setMass(1.0);
	}
	
	public GameObject(Vector2d position, Vector2i scale) {
		this.position = position;
		this.scale = scale;
		setMass(1.0);
	}
	
	public void update(double delta, ArrayList<GameObject> gameObjects) {
		if (!update) return;
		
		if (useGravity) acceleration.y = scene.gravity;
	
		velocity.add(acceleration.multiplied(delta));
		
		if (velocity.y > terminalVelocity) {
			velocity.y = terminalVelocity; // change this to use drag, like a real life object falling faster than its terminal velocity
		}
		
		collisionLeft = collisionLeftNext;
		collisionRight = collisionRightNext;
		collisionAbove = collisionAboveNext;
		collisionBelow = collisionBelowNext;
		
		collisionLeftNext = false;
		collisionRightNext = false;
		collisionAboveNext = false;
		collisionBelowNext = false;
		
		// need to use Rectd so small velocities dont get rounded off
		Rectd hitbox = getRectd();
		Rectd nextFrameX = new Rectd(position.plus(new Vector2d(velocity.x * delta, 0.0)), Vector2.tod(scale));
		Rectd nextFrameY = new Rectd(position.plus(new Vector2d(0.0, velocity.y * delta)), Vector2.tod(scale));
		
		//double increase = nextFrameY.getPosition().y - position.y;
		
		for (GameObject gameObject : gameObjects) {
			if (gameObject == this) continue;
			
			boolean willCollide = canCollide && gameObject.canCollide;
			
			boolean colliding = false;
			boolean touching = false;
			
			Rectd otherRect = gameObject.getRectd();
			
			// later, make it so that the closest collision in each direction is tracked, and then when collision enter and exit events
			// are added, only fire for the closest collisions
			
			if (Utils.RectangleIntersection(nextFrameX, otherRect)) {
				if (canTouch) touching = true;
				
				if (willCollide) {
					colliding = true;
					
					if (velocity.x > 0) {
						collisionRightNext = true;
						velocity.x = (otherRect.left() - hitbox.right()) / delta;
					}
					else if (velocity.x < 0) {
						collisionLeftNext = true;
						velocity.x = (otherRect.right() - hitbox.left()) / delta;
					}
				}
			}
			
			if (Utils.RectangleIntersection(nextFrameY, otherRect)) {
				if (canTouch) touching = true;
				
				if (willCollide) {
					colliding = true;
					
					if (velocity.y >= 0) {
						collisionBelowNext = true;
						velocity.y = (otherRect.top() - hitbox.bottom()) / delta;
					}
					else if (velocity.y < 0) {
						collisionAboveNext = true;
						velocity.y = (otherRect.bottom() - hitbox.top()) / delta;
						nextFrameY.y = position.y + (int) (velocity.y * delta);
					}
				}
			}
		}
		
		position.add(velocity.multiplied(delta));
	}
	
	public void draw(Renderer renderer) {
		if (hasImage) {
			renderer.drawImage(image, position.x, position.y);
			return;
		}
		
		renderer.drawRectangle(position.x, position.y, scale.x, scale.y, colour, opacity);
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
		
		this.image = image.getScaled(scale.x, scale.y);
		hasImage = true;
	}
	
	public boolean hasImage() {
		return hasImage;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
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
		
		terminalVelocity = 200 * Math.sqrt(scene.gravity * mass / scale.x);
	}

	public Rectd getRectd() {
		return new Rectd(position, Vector2.tod(scale));
	}
	
//	public Recti getRecti() {
//		return new Recti(Vector2.toi(position), scale);
//	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public boolean collisionLeft() {
		return collisionLeft;
	}

	public boolean collisionRight() {
		return collisionRight;
	}

	public boolean collisionAbove() {
		return collisionAbove;
	}

	public boolean collisionBelow() {
		return collisionBelow;
	}
	
//	public int getIndex() {
//		return index;
//	}
//	
//	public void setIndex(int index) {
//		this.index = index;
//	}
}
