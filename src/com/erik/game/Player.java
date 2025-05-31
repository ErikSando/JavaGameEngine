package com.erik.game;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.GameObject;
import com.erik.engine.InputHandler;
import com.erik.engine.enums.InputAxis;
import com.erik.engine.groups.PostUpdateMember;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Player extends PostUpdateMember {
	private InputHandler input;
	private GameObject gameObject;
	
	private int speed = 200;
	
	public Player(Game game, Vector2i position) {
		super(game);
		
		gameObject = new GameObject(game.getScene(), position, new Vector2i(50, 100));
		gameObject.colour = Colour.red;
//		gameObject.useGravity = false;
		
		input = game.getInputHandler();
		
//		input.keyDown.addListener(event -> {
//			System.out.println("Key down: " + event.keyCode + ", " + (char) event.keyChar);
//		});
//		
//		input.keyUp.addListener(event -> {
//			System.out.println("Key up: " + event.keyChar);
//		});
//		
//		input.mouseDown.addListener(event -> {
//			System.out.println("Mouse down: " + event.button);
//		});
//		
//		input.mouseUp.addListener(event -> {
//			System.out.println("Mouse up: " + event.button);
//		});
	}

	@Override
	public void start() {

	}

	@Override
	public void update(double delta) {
		//System.out.println(gameObject.velocity);
		
		int movementH = input.getAxisRaw(InputAxis.Horizontal);
		int movementV = input.getAxisRaw(InputAxis.Vertical);
		
//		System.out.println(movementH + ", " + movementV);
		
		gameObject.velocity = new Vector2d(movementH, movementV).normalised().multiplied(speed);
		
//		gameObject.velocity.x = movementH;
//		gameObject.velocity.y = movementV;
//		
//		gameObject.velocity.normalise();
//		gameObject.velocity.multiply(speed);
		
//		game.getCamera().position = gameObject.position.minus(game.getWindow().getSize().divided(2)).plus(gameObject.getRectangle().getScale().divided(2));
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
}
