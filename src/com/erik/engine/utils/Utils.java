package com.erik.engine.utils;

import com.erik.engine.shapes.Rectangle;

public class Utils {
	public static boolean RectangleIntersection(Rectangle r1, Rectangle r2) {
		if (r1.x + r1.w <= r2.x ||
			r1.y + r1.h <= r2.y ||
			r1.x >= r2.x + r2.w ||
			r1.y >= r2.y + r2.h) return false;
		
		return true;
	}
}
