package com.erik.game;

import javax.swing.ImageIcon;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.GameObject;
import com.erik.engine.Vector2;

public class Main {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("res/dog.png");
		Game game = new Game("Game test", 960, 540, icon);
		
		GameObject test0 = new GameObject(new Vector2(100, 100), new Vector2(200, 200));
		test0.colour = Colour.red;
		
		GameObject test1 = new GameObject(new Vector2(250, 200), new Vector2(200, 200));
		test1.colour = Colour.blue;
		
		game.getScene().addGameObjects(test0, test1);
		
		test0.opacity = 0.5;
		test0.setLayer(1);
		test1.setLayer(0);
		
		System.out.println("Number of game ojects: " + game.getScene().getGameObjects().size());
		
		test0.velocity.x = 50;
		test1.velocity.x = -50;
		
		game.start();
	}
}
