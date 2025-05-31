package com.erik.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.erik.engine.vector2.Vector2i;

public class Window {
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private String title = "Game";
	private int nativeWidth = 1920;
	private int nativeHeight = 1080;
	private int width = 960;
	private int height = 540;
	private ImageIcon icon;
	private boolean resizable = true;
	
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
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setTitle(title);
		frame.setIconImage(icon.getImage());
		frame.setResizable(resizable);
		frame.setVisible(true);

		canvas.requestFocus();
		canvas.requestFocusInWindow();
		
		canvas.createBufferStrategy(3);
		bufferStrategy = canvas.getBufferStrategy();
	}
	
	public void update() {
		Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		double scaleX = (double) width / nativeWidth;
		double scaleY = (double) height / nativeHeight;
		
		double scale = Math.min(scaleX, scaleY);
		
		double translateX = 0.0;
		double translateY = 0.0;
		
		if (scale == scaleY) { // aspect ratio >= 16:9
			translateX = (width - nativeWidth * scale) / 2;
		}
		else { // aspect ratio < 16:9
			translateY = (height - nativeHeight * scale) / 2;
		}
		
		g2d.translate(translateX, translateY);
		g2d.scale(scale, scale);
		
		g2d.drawImage(image, 0, 0, nativeWidth, nativeHeight, null);
		g2d.dispose();
		
		bufferStrategy.show();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Vector2i getSize() {
		return new Vector2i(width, height);
	}
	
	public void setSize(int width, int height) {
		if (width < 0 || height < 0) return;
		
		this.width = width;
		this.height = height;
		frame.setSize(new Dimension(width, height));
	}
	
	public void setSize(Dimension size) {
		width = size.width;
		height = size.height;
		frame.setSize(size);
	}
	
	public int getNativeWidth() {
		return nativeWidth;
	}
	
	public int getNativeHeight() {
		return nativeHeight;
	}
	
	public void setNativeSize(int width, int height) {
		if (width < 0 || height < 0) return;
		
		nativeWidth = width;
		nativeHeight = height;
	}
	
	public void setNativeSize(Vector2i size) {
		if (size.x < 0 || size.y < 0) return;
		
		nativeWidth = size.x;
		nativeHeight = size.y;
	}
	
	public boolean getResizable() {
		return resizable;
	}
	
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
