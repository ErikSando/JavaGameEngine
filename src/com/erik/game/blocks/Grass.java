package com.erik.game.blocks;

import com.erik.engine.gfx.Image;
import com.erik.engine.scene.GameObject;
import com.erik.engine.scene.Scene;

public class Grass extends GameObject {
	public Grass(Scene scene, int x, int y, int w) {
		super(scene, x, y, w, w);

		setImage(new Image("blocks/grass.png"));
		update = false;
	}
}
