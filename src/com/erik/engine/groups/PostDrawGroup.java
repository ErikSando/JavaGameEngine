package com.erik.engine.groups;

import com.erik.engine.Game;
import com.erik.engine.Renderer;

public abstract class PostDrawGroup extends GroupMember {
	public PostDrawGroup() {}
	
	public PostDrawGroup(boolean active) {
		super(active);
	}
	
	public PostDrawGroup(Game game) {
		super(game);
	}
	
	public PostDrawGroup(Game game, boolean active) {
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
