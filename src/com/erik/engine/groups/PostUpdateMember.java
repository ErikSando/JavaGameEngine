package com.erik.engine.groups;

import com.erik.engine.Game;

public abstract class PostUpdateMember extends GroupMember {
	public PostUpdateMember() {}
	
	public PostUpdateMember(boolean active) {
		super(active);
	}
	
	public PostUpdateMember(Game game) {
		super(game);
	}
	
	public PostUpdateMember(Game game, boolean active) {
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
