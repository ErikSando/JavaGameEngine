package com.erik.engine.utils;

import com.erik.engine.shapes.Rectd;
import com.erik.engine.shapes.Rectf;
import com.erik.engine.shapes.Recti;

public class Utils {
	public static boolean RectangleIntersection(Rectd r1, Rectd r2) {
		if (r1.x + r1.w <= r2.x ||
			r1.y + r1.h <= r2.y ||
			r2.x + r2.w <= r1.x ||
			r2.y + r2.h <= r1.y) return false;
		
		return true;
	}
	
	public static boolean RectangleIntersection(Rectf r1, Rectf r2) {
		if (r1.x + r1.w <= r2.x ||
			r1.y + r1.h <= r2.y ||
			r2.x + r2.w <= r1.x ||
			r2.y + r2.h <= r1.y) return false;
		
		return true;
	}
	
	public static boolean RectangleIntersection(Recti r1, Recti r2) {
		if (r1.x + r1.w <= r2.x ||
			r1.y + r1.h <= r2.y ||
			r2.x + r2.w <= r1.x ||
			r2.y + r2.h <= r1.y) return false;
		
		return true;
	}
	
	public static double Clamp(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}
	
	public static float Clamp(float value, float min, float max) {
		return Math.min(Math.max(value, min), max);
	}
	
	public static int Clamp(int value, int min, int max) {
		return Math.min(Math.max(value, min), max);
	}
}
