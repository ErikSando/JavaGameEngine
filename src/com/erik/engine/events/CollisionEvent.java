package com.erik.engine.events;

import com.erik.engine.GameObject;

public class CollisionEvent {
	private final CollisionEventSource source;
	
	public GameObject other;
	
	public CollisionEvent(CollisionEventSource source, GameObject other) {
		this.source = source;
		this.other = other;
	}
	
	public CollisionEventSource getSource() {
		return source;
	}
}
