package com.erik.engine.groups;

import com.erik.engine.Game;
import com.erik.engine.Renderer;

public abstract class PreDrawMember extends GroupMember {
	public PreDrawMember() {}
	
	public PreDrawMember(boolean active) {
		super(active);
	}
	
	public PreDrawMember(Game game) {
		super(game);
	}
	
	public PreDrawMember(Game game, boolean active) {
		super(game, active);
	}

	@Override
	public void addToGame() {
		game.addToPreDrawGroup(this);
	}

	@Override
	public void removeFromGame() {
		game.removeFromPreDrawGroup(this);
	}

	public abstract void start();
	public abstract void draw(Renderer renderer);
}
