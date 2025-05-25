package com.erik.engine.events;

import java.util.ArrayList;
import java.util.List;

public class KeyboardEventSource {
	private final List<KeyboardEventListener> listeners = new ArrayList<>();
	
	public void addListener(KeyboardEventListener listener) {
		listeners.add(listener);
	}
	public void removeListener(KeyboardEventListener listener) {
		listeners.remove(listener);
	}
	
	public void invoke(int keyCode, int keyChar, int keyLocation, boolean ctrlKey, boolean shiftKey) {
		KeyboardEvent event = new KeyboardEvent(this, keyCode, keyChar, keyLocation, ctrlKey, shiftKey);
		
		for (KeyboardEventListener listener : listeners) {
			listener.invoke(event);
		}
	}
}
