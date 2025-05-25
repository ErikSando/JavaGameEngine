package com.erik.game;

import javax.swing.ImageIcon;

import com.erik.engine.Game;
import com.erik.engine.GameObject;
import com.erik.engine.Vector2;
import com.erik.engine.gfx.Image;

public class Main {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("res/dog.png");
		Game game = new Game("Game test", 960, 540, icon);
		
		World world = new World();
		world.loadData("res/levels/level1.txt");
		
		Player player = new Player(game, new Vector2(400, 50));
		
		GameObject object = new GameObject(game.getScene(), new Vector2(600, 250), new Vector2(300, 200));
		object.setImage(new Image("dog.png"));
		
		game.start();
	}
}
