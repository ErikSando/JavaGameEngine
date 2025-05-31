package com.erik.engine.enums;

import java.awt.event.MouseEvent;

public enum MouseButton {
	NoButton(MouseEvent.NOBUTTON),
	Button1,
	Button2,
	Button3,
	Button4,
	Button5,
	Last;
	
	private final int button;
	private static int currentButton = 0;
	
	private static int next() {
		return currentButton++;
	}
	
	private static void setNext(int next) {
		currentButton = next;
	}
	
	MouseButton() {
		this.button = next();
	}
	
	MouseButton(int button) {
		this.button = button;
		setNext(button + 1);
	}
	
	public int getButton() {
		return button;
	}
}
