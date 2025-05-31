package com.erik.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.erik.engine.enums.InputAxis;
import com.erik.engine.enums.KeyCode;
import com.erik.engine.enums.MouseButton;
import com.erik.engine.events.KeyboardEventSource;
import com.erik.engine.events.MouseEventSource;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private Game game;
	
	private final int numKeys = KeyCode.Last.getKeyCode();
	private boolean[] keys = new boolean[numKeys];
	private boolean[] keysLast = new boolean[numKeys];
	
	private final int numButtons = MouseButton.Last.getButton();
	private boolean[] buttons = new boolean[numButtons];
	private boolean[] buttonsLast = new boolean[numButtons];
	
	private int mouseX, mouseY;
	private int scroll;
	
	public KeyboardEventSource keyDown = new KeyboardEventSource();
	public KeyboardEventSource keyUp = new KeyboardEventSource();
	
	public MouseEventSource mouseDown = new MouseEventSource();
	public MouseEventSource mouseUp = new MouseEventSource();
	
	public InputHandler(Game game) {
		this.game = game;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		
		game.getWindow().getCanvas().addKeyListener(this);
		game.getWindow().getCanvas().addMouseListener(this);
		game.getWindow().getCanvas().addMouseMotionListener(this);
		game.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	public void update() {
		for (int i = 0; i < numKeys; i++) {
			keysLast[i] = keys[i];
		}
		
		for (int i = 0; i < numButtons; i++) {
			buttonsLast[i] = buttons[i];
		}
		
		scroll = 0;
	}
	
	public boolean getKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean getKey(KeyCode keyCode) {
		return keys[keyCode.getKeyCode()];
	}
	
	public boolean getKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public boolean getKeyDown(KeyCode keyCode) {
//		System.out.println("Key " + keyCode.getKeyCode() + " down: " + keys[keyCode.getKeyCode()]);
//		System.out.println("Key " + keyCode.getKeyCode() + " last: " + keysLast[keyCode.getKeyCode()]);
		int kc = keyCode.getKeyCode();
		return keys[kc] && !keysLast[kc];
	}
	
	public boolean getKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public boolean getKeyUp(KeyCode keyCode) {
		return !keys[keyCode.getKeyCode()] && keysLast[keyCode.getKeyCode()];
	}
	
	public boolean getButton(int button) {
		return buttons[button];
	}
	
	public boolean getButton(MouseButton button) {
		return buttons[button.getButton()];
	}
	
	public boolean getButtonDown(int button) {
		return buttons[button] && !buttonsLast[button];
	}
	
	public boolean getButtonDown(MouseButton button) {
		return buttons[button.getButton()] && !buttonsLast[button.getButton()];
	}
	
	public boolean getButtonUp(int button) {
		return !buttons[button] && buttonsLast[button];
	}
	
	public boolean getButtonUp(MouseButton button) {
		return !buttons[button.getButton()] && buttonsLast[button.getButton()];
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / game.getScale());
		mouseY = (int) (e.getY() / game.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / game.getScale());
		mouseY = (int) (e.getY() / game.getScale());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		if (button >= numButtons) return;
		buttons[button] = true;
		mouseDown.invoke(button, getKey(KeyCode.Control), getKey(KeyCode.Shift));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int button = e.getButton();
		if (button >= numButtons) return;
		buttons[button] = false;
		mouseUp.invoke(button, getKey(KeyCode.Control), getKey(KeyCode.Shift));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= numKeys) return;
		keys[keyCode] = true;
		keyDown.invoke(keyCode, e.getKeyChar(), e.getKeyLocation(), getKey(KeyCode.Control), getKey(KeyCode.Shift));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= numKeys) return;
		keys[keyCode] = false;
		keyUp.invoke(keyCode, e.getKeyChar(), e.getKeyLocation(), getKey(KeyCode.Control), getKey(KeyCode.Shift));
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public int getScroll() {
		return scroll;
	}
	
	public int getAxisRaw(InputAxis axis) {
		int value = 0;
		
		switch (axis) {
			case Horizontal:
				if (getKey(KeyCode.A) || getKey(KeyCode.Right)) value--;
				if (getKey(KeyCode.D) || getKey(KeyCode.Left)) value++;
				break;
			
			case Vertical:
				if (getKey(KeyCode.W) || getKey(KeyCode.Up)) value--;
				if (getKey(KeyCode.S) || getKey(KeyCode.Down)) value++;
				break;
		}
		
		return value;
	}
}
