package com.erik.engine.enums;

import java.awt.event.MouseEvent;

public enum MouseButton {
	button1(MouseEvent.BUTTON1),
	button2(MouseEvent.BUTTON2),
	button3(MouseEvent.BUTTON3),
	button4(4),
	button5(5);
	
	private final int button;
	
	MouseButton(int button) {
		this.button = button;
	}
	
	public int getButton() {
		return button;
	}
}
