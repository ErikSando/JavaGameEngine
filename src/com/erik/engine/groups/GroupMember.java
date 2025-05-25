package com.erik.engine.groups;

import com.erik.engine.Game;

public abstract class GroupMember {
	public boolean active = true;

	protected Game game;
	
	public GroupMember() {}
	
	public GroupMember(boolean active) {
		this.active = active;
	}
	
	public GroupMember(Game game) {
		this.game = game;
		
		addToGame();
	}
	
	public GroupMember(Game game, boolean active) {
		this.game = game;
		this.active = active;
		
		if (this.active) {
			addToGame();
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		removeFromGame();
		this.game = game;
		addToGame();
	}
	
	public void destroy() {
		removeFromGame();
	}
	
	public abstract void addToGame();
	public abstract void removeFromGame();
}
