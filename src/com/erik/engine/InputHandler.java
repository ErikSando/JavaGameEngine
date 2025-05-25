package com.erik.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private Game game;
	
	private final int numKeys = 256;
	private boolean[] keys = new boolean[numKeys];
	private boolean[] keysLast = new boolean[numKeys];
	
	private final int numButtons = 5;
	private boolean[] buttons = new boolean[numButtons];
	private boolean[] buttonsLast = new boolean[numButtons];
	
	private int mouseX, mouseY;
	private int scroll;
	
	public InputHandler(Game game) {
		this.game = game;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		
		game.getWindow().getFrame().addKeyListener(this);
		game.getWindow().getFrame().addMouseListener(this);
		game.getWindow().getFrame().addMouseMotionListener(this);
		game.getWindow().getFrame().addMouseWheelListener(this);
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
	
	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public boolean isButton(int button) {
		return buttons[button];
	}
	
	public boolean isButtonDown(int button) {
		return buttons[button] && !buttonsLast[button];
	}
	
	public boolean isButtonUp(int button) {
		return !buttons[button] && buttonsLast[button];
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
		if (e.getButton() >= numButtons) return;
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() >= numButtons) return;
		buttons[e.getButton()] = false;
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
		if (e.getKeyCode() >= numKeys) return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= numKeys) return;
		keys[e.getKeyCode()] = false;
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
}
