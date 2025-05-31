package com.erik.engine;

public class Colour {
	public static int White = 0xffffff;
	public static int Black = 0x000000;
	public static int Red = 0xff0000;
	public static int Green = 0x00ff00;
	public static int Blue = 0x0000ff;
	public static int Yellow = 0xffff00;
	public static int Pink = 0xff00ff;
	public static int Cyan = 0x00ffff;
	
	public static int fromRGB(int r, int g, int b) {
		return b | g << 8 | r << 16;
	}
	
	public static int getR(int colour) {
		return colour >> 16;
	}
	
	public static int getG(int colour) {
		return (colour & Green) >> 8;
	}
	
	public static int getB(int colour) {
		return colour & Blue;
	}
	
	private Colour() {
		throw new UnsupportedOperationException("Cannot instantiate Colour class");
	}
}
