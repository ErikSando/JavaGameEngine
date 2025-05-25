package com.erik.engine;

public abstract class PreDrawGroup extends GroupMember {
	public PreDrawGroup() {}
	
	public PreDrawGroup(boolean active) {
		super(active);
	}
	
	public PreDrawGroup(Game game) {
		super(game);
	}
	
	public PreDrawGroup(Game game, boolean active) {
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
