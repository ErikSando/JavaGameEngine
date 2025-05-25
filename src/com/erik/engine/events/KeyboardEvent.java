package com.erik.engine.events;

public class KeyboardEvent {
	private final KeyboardEventSource source;
	
	public final int keyCode;
	public final int keyChar;
	public final int keyLocation;
	public final boolean ctrlKey;
	public final boolean shiftKey;
	
	public KeyboardEvent(KeyboardEventSource source, int keyCode, int keyChar, int keyLocation, boolean ctrlKey, boolean shiftKey) {
		this.source = source;
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.keyLocation = keyLocation;
		this.ctrlKey = ctrlKey;
		this.shiftKey = shiftKey;
	}
	
	public KeyboardEventSource getSource() {
		return source;
	}
}
