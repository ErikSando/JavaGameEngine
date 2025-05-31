package com.erik.engine.groups;

import com.erik.engine.Game;

public abstract class PreUpdateMember extends GroupMember {
	public PreUpdateMember() {}
	
	public PreUpdateMember(boolean active) {
		super(active);
	}
	
	public PreUpdateMember(Game game) {
		super(game);
	}
	
	public PreUpdateMember(Game game, boolean active) {
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
