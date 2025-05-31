package com.erik.game;

import javax.swing.ImageIcon;

import com.erik.engine.Colour;
import com.erik.engine.Game;
import com.erik.engine.gfx.Image;
import com.erik.engine.scene.GameObject;
import com.erik.engine.scene.UIObject;
import com.erik.engine.vector2.Vector2d;
import com.erik.engine.vector2.Vector2i;

public class Main {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("res/dog.png");
		Game game = new Game("Game test", 960, 540, icon);
		
//		game.getRenderer().setClearColour(Colour.fromRGB(50, 200, 250));
		
		World world = new World();
		game.setScene(world.loadData("res/levels/level1.txt"));
		
		Player player = new Player(game, new Vector2d(80.0, 0.0));
//		player.getGameObject().setLayer(2);
		
		UIObject ui1 = new UIObject(game.getScene(), new Vector2i(100, 100), new Vector2i(100, 50));
		ui1.outlineColour = Colour.White;
		ui1.backgroundColour = Colour.Red;
		ui1.outlineThickness = 5;
		ui1.setOutlineOpacity(0.8);
		ui1.setBackgroundOpacity(0.8);
		
		UIObject ui2 = new UIObject(game.getScene(), new Vector2i(150, 125), new Vector2i(100, 50));
		ui2.outlineColour = Colour.White;
		ui2.backgroundColour = Colour.Blue;
		ui2.outlineThickness = 5;
		
		ui1.setLayer(1);
		
		GameObject object = new GameObject(game.getScene(), new Vector2d(330.0, 150.0), new Vector2i(300, 200));
		object.setImage(new Image("dog.png"));
		
		game.start();
	}
}
