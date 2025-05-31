package com.erik.game.blocks;

import com.erik.engine.gfx.Image;
import com.erik.engine.scene.GameObject;
import com.erik.engine.scene.Scene;

public class Dirt extends GameObject {
	public Dirt(Scene scene, int x, int y, int w) {
		super(scene, x, y, w, w);
		
		setImage(new Image("blocks/dirt.png"));
		update = false;
	}
}
