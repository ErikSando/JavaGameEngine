package com.erik.game;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.InputHandler;
import com.erik.engine.enums.InputAxis;
import com.erik.engine.enums.KeyCode;
import com.erik.engine.groups.PostUpdateMember;
import com.erik.engine.scene.GameObject;
import com.erik.engine.utils.Utils;
import com.erik.engine.vector2.Vector2;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Player extends PostUpdateMember {
	private InputHandler input;
	private GameObject gameObject;
	
	private int speed = 200;
	private int jumpPower = 530;
	
	private double camFollowSpeed = 7.5;
	
	public Player(Game game, Vector2d position) {
		super(game);
		
		gameObject = new GameObject(game.getScene(), position, new Vector2i(50, 100));
		gameObject.colour = Colour.Red;
		
		input = game.getInputHandler();
	}

	@Override
	public void start() {
		// doesn't matter if this goes in the constructor, but theres nothing else here so i put it here
//		game.getCamera().position = Vector2.toi(gameObject.position).minus(game.getWindow().getSize().divided(2)).plus(gameObject.scale.divided(2));
		game.getCamera().position = gameObject.position.minus(game.getWindow().getSize().divided(2)).plus(gameObject.scale.divided(2));
	}

	@Override
	public void update(double delta) {
		//System.out.println(gameObject.velocity);
		
		int movementH = input.getAxisRaw(InputAxis.Horizontal);
		int movementV = input.getAxisRaw(InputAxis.Vertical);
		
		gameObject.velocity.x = movementH * speed;
		
		if ((input.getKey(KeyCode.Space) || input.getKey(KeyCode.W) || input.getKey(KeyCode.Up)) && gameObject.collisionBelow()) {
			gameObject.velocity.y = -jumpPower;
		}
		
		// cam pos = gameobjpos - windowsize/2 + gameobjsize/2
//		Vector2i currPos = game.getCamera().position;
//		Vector2i newPos = Vector2.toi(gameObject.position).minus(game.getWindow().getSize().divided(2)).plus(gameObject.scale.divided(2));
//		double lerpFactor = Utils.Clamp(camFollowSpeed * delta, 0.0, 1.0);
//		game.getCamera().position = Vector2.preciseLerpi(currPos, newPos, lerpFactor);
		Vector2d currPos = game.getCamera().position;
		Vector2d newPos = gameObject.position.minus(game.getWindow().getSize().divided(2)).plus(gameObject.scale.divided(2));
		double lerpFactor = Utils.Clamp(camFollowSpeed * delta, 0.0, 1.0);
		game.getCamera().position = Vector2.lerp(currPos, newPos, lerpFactor);
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getJumpPower() {
		return jumpPower;
	}
	
	public void setJumpPower(int jumpPower) {
		this.jumpPower = jumpPower;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
}
