package com.erik.engine.events;

import java.util.ArrayList;
import java.util.List;

public class MouseEventSource {
	private final List<MouseEventListener> listeners = new ArrayList<>();
	
	public void addListener(MouseEventListener listener) {
		listeners.add(listener);
	}
	public void removeListener(MouseEventListener listener) {
		listeners.remove(listener);
	}
	
	public void invoke(int button, boolean ctrlKey, boolean shiftKey) {
		MouseEvent event = new MouseEvent(this, button, ctrlKey, shiftKey);
		
		for (MouseEventListener listener : listeners) {
			listener.invoke(event);
		}
	}
}
