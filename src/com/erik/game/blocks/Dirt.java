package com.erik.game.blocks;

import com.erik.engine.GameObject;
import com.erik.engine.Scene;
import com.erik.engine.gfx.Image;

public class Dirt extends GameObject {
	public Dirt(Scene scene, int x, int y, int w) {
		super(scene, x, y, w, w);
		
		setImage(new Image("blocks/dirt.png"));
		update = false;
	}
}
