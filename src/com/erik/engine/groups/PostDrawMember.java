package com.erik.engine.groups;

import com.erik.engine.Game;
import com.erik.engine.Renderer;

public abstract class PostDrawMember extends GroupMember {
	public PostDrawMember() {}
	
	public PostDrawMember(boolean active) {
		super(active);
	}
	
	public PostDrawMember(Game game) {
		super(game);
	}
	
	public PostDrawMember(Game game, boolean active) {
		super(game, active);
	}

	@Override
	public void addToGame() {
		game.addToPostDrawGroup(this);
	}

	@Override
	public void removeFromGame() {
		game.removeFromPostDrawGroup(this);
	}

	public abstract void start();
	public abstract void draw(Renderer renderer);
}
