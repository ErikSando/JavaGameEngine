package com.erik.engine.groups;

import com.erik.engine.Game;

public abstract class PostUpdateGroup extends GroupMember {
	public PostUpdateGroup() {}
	
	public PostUpdateGroup(boolean active) {
		super(active);
	}
	
	public PostUpdateGroup(Game game) {
		super(game);
	}
	
	public PostUpdateGroup(Game game, boolean active) {
		super(game, active);
	}

	@Override
	public void addToGame() {
		game.addToPostUpdateGroup(this);
	}

	@Override
	public void removeFromGame() {
		game.removeFromPostUpdateGroup(this);
	}

	public abstract void start();
	public abstract void update(double delta);
}
