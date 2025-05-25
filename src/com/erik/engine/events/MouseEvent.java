package com.erik.engine.events;

public class MouseEvent {
	private final MouseEventSource source;
	
	public final int button;
	public final boolean ctrlKey;
	public final boolean shiftKey;
	
	public MouseEvent(MouseEventSource source, int button, boolean ctrlKey, boolean shiftKey) {
		this.source = source;
		this.button = button;
		this.ctrlKey = ctrlKey;
		this.shiftKey = shiftKey;
	}
	
	public MouseEventSource getSource() {
		return source;
	}
}
