package com.erik.engine.events;

import java.util.ArrayList;
import java.util.List;

import com.erik.engine.GameObject;

public class CollisionEventSource {
	private final List<CollisionEventListener> listeners = new ArrayList<>();
	
	public void addListener(CollisionEventListener listener) {
		listeners.add(listener);
	}
	public void removeListener(CollisionEventListener listener) {
		listeners.remove(listener);
	}
	
	public void invoke(GameObject other) {
		CollisionEvent event = new CollisionEvent(this, other);
		
		for (CollisionEventListener listener : listeners) {
			listener.invoke(event);
		}
	}
}
