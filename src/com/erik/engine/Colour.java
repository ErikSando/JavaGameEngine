package com.erik.engine;

public class Colour {
	public static int white = 0xffffff;
	public static int black = 0x000000;
	public static int red = 0xff0000;
	public static int green = 0x00ff00;
	public static int blue = 0x0000ff;
	public static int yellow = 0xffff00;
	public static int pink = 0xff00ff;
	public static int cyan = 0x00ffff;
	
	public static int getColour(int r, int g, int b) {
		return b | g << 8 | r << 16;
	}
	
	private Colour() {
		throw new UnsupportedOperationException("Cannot instantiate Colour class");
	}
}
