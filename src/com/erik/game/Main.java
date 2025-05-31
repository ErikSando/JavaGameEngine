package com.erik.game;

import javax.swing.ImageIcon;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.GameObject;
import com.erik.engine.gfx.Image;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Main {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("res/dog.png");
		Game game = new Game("Game test", 960, 540, icon);
		
//		game.getRenderer().setClearColour(Colour.blue);
		
		World world = new World();
		game.setScene(world.loadData("res/levels/level1.txt"));
		
		Player player = new Player(game, new Vector2d(80.0, 0.0));
//		player.getGameObject().setLayer(2);
		
		GameObject object = new GameObject(game.getScene(), new Vector2d(330.0, 150.0), new Vector2i(300, 200));
		object.setImage(new Image("dog.png"));
		
		game.start();
	}
}
