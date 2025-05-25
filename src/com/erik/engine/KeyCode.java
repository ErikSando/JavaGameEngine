package com.erik.engine;

import java.awt.event.KeyEvent;

public enum KeyCode {
	Undefined,
	Cancel(KeyEvent.VK_CANCEL),
	Backspace(KeyEvent.VK_BACK_SPACE),
	Tab,
	Enter,
	Clear(KeyEvent.VK_CLEAR),
	Shift(KeyEvent.VK_SHIFT),
	Control,
	Alt,
	Pause,
	CapsLock,
	Escape(KeyEvent.VK_ESCAPE),
	Space(KeyEvent.VK_SPACE),
	PageUp,
	PageDown,
	End,
	Home,
	Left,
	Up,
	Right,
	Down,
	Comma(KeyEvent.VK_COMMA),
	Minus,
	Period,
	Slash,
	Digit0, Digit1, Digit2, Digit3, Digit4, Digit5, Digit6, Digit7, Digit8, Digit9,
	SemiColon(KeyEvent.VK_SEMICOLON),
	Equals(KeyEvent.VK_EQUALS),
	A(KeyEvent.VK_A), B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
	OpenBracket,
	BackSlash,
	CloseBracket,
	Numpad0(KeyEvent.VK_NUMPAD0), Numpad1, Numpad2, Numpad3, Numpad4, Numpad5, Numpad6, Numpad7, Numpad8, Numpad9,
	Multiply,
	Add,
	Seperator,
	Subtract,
	Decimal,
	Divide,
	F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,
	Delete(KeyEvent.VK_DELETE),
	DeadGrave,
	DeadAcute,
	DeadCircumflex,
	DeadTilde,
	DeadMacron,
	DeadBreve,
	DeadAboveDot,
	DeadDiaeresis,
	DeadAboveRing,
	DeadCaron,
	DeadCedilla,
	DeadOgonek,
	DeadIota,
	DeadVoicedSound,
	DeadSemiVoicedSound,
	NumLock,
	ScrollLock,
	PrintScreen(KeyEvent.VK_PRINTSCREEN),
	Insert,
	Help,
	Meta,
	Backquote(KeyEvent.VK_BACK_QUOTE),
	Quote(KeyEvent.VK_QUOTE),
	KeypadUp(KeyEvent.VK_KP_UP),
	KeypadDown,
	KeypadLeft,
	KeypadRight,
	Ampersand(KeyEvent.VK_AMPERSAND),
	Asterisk,
	DoubleQuote,
	Less,
	Greater(KeyEvent.VK_GREATER),
	BraceLeft,
	BraceRight,
	Last;
	
	private final int keyCode;
	private static int currentKeyCode = 0;
	
	private static int next() {
		return currentKeyCode++;
	}
	
	private static void setNext(int current) {
		currentKeyCode = current + 1;
	}
	
	KeyCode() {
		this.keyCode = next();
	}
	
	KeyCode(int keyCode) {
		this.keyCode = keyCode;
		setNext(keyCode);
	}
	
	public int getKeyCode() {
		return keyCode;
	}
}
