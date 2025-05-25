package com.erik.engine.groups;

import com.erik.engine.Game;

public abstract class PreUpdateGroup extends GroupMember {
	public PreUpdateGroup() {}
	
	public PreUpdateGroup(boolean active) {
		super(active);
	}
	
	public PreUpdateGroup(Game game) {
		super(game);
	}
	
	public PreUpdateGroup(Game game, boolean active) {
		super(game, active);
	}
	
	@Override
	public void addToGame() {
		game.addToPreUpdateGroup(this);
	}

	@Override
	public void removeFromGame() {
		game.removeFromPreUpdateGroup(this);
	}
	
	public abstract void start();
	public abstract void update(double delta);
}
