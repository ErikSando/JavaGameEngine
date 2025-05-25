package com.erik.game;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.GameObject;
import com.erik.engine.InputAxis;
import com.erik.engine.InputHandler;
import com.erik.engine.PreUpdateGroup;
import com.erik.engine.Vector2;

public class Player extends PreUpdateGroup {
	private InputHandler input;
	private GameObject gameObject;
	
	private int speed = 200;
	
	public Player(Game game, Vector2 position) {
		super(game);
		
		gameObject = new GameObject(game.getScene(), position, new Vector2(50, 100));
		gameObject.colour = Colour.red;
		
		input = game.getInputHandler();
	}

	@Override
	public void start() {

	}

	@Override
	public void update(double delta) {
		int movementH = input.getAxisRaw(InputAxis.Horizontal);
		int movementV = input.getAxisRaw(InputAxis.Vertical);
		
		gameObject.velocity.x = movementH * speed;
		
		game.getCamera().position = gameObject.position.minus(game.getWindow().getSize().divided(2)).plus(gameObject.getRectangle().getScale().divided(2));
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
