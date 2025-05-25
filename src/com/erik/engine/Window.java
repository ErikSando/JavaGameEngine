package com.erik.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window {
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private String title = "Game";
	private int width = 960;
	private int height = 540;
	private ImageIcon icon;
	private boolean resizable = false;
	
	public Window() {
		init();
	}
	
	public Window(String title) {
		this.title = title;
		init();
	}
	
	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	
	public Window(String title, int width, int height, ImageIcon icon) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.icon = icon;
		init();
	}
	
	private void init() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		
		Dimension size = new Dimension(width, height);
		
		canvas.setPreferredSize(size);
		canvas.setMaximumSize(size);
		canvas.setMaximumSize(size);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setTitle(title);
		frame.setIconImage(icon.getImage());
		frame.setResizable(resizable);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(3);
		bufferStrategy = canvas.getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
	}
	
	public void update() {
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getWidth();
	}
	
	public void setSize(int width, int height) {
		frame.setSize(new Dimension(width, height));
	}
	
	public void setSize(Dimension size) {
		frame.setSize(size);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
