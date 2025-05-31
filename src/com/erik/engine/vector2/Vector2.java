package com.erik.engine.vector2;

public class Vector2 {
	public Vector2() {}
	
	public static double dot(Vector2d v1, Vector2d v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	public static double dot(Vector2f v1, Vector2f v2) {
		return (double) (v1.x * v2.x + v1.y * v2.y);
	}
	
	public static double dot(Vector2i v1, Vector2i v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	public static Vector2d clamp(Vector2d v, double min, double max) {
		double magnitude = v.magnitude();
		
		
		if (magnitude < min) {
			return v.normalised().multiplied(min);
		}
		
		if (magnitude > max) {
			return v.normalised().multiplied(max);
		}
		
		return v;
	}
	
	public static Vector2f clamp(Vector2f v, float min, float max) {
		double magnitude = v.magnitude();
		
		
		if (magnitude < min) {
			return v.normalised().multiplied(min);
		}
		
		if (magnitude > max) {
			return v.normalised().multiplied(max);
		}
		
		return v;
	}
	
	public static Vector2i clamp(Vector2i v, double min, double max) {
		double magnitude = v.magnitude();
		
		
		if (magnitude < min) {
			return v.normalised().multiplied(min);
		}
		
		if (magnitude > max) {
			return v.normalised().multiplied(max);
		}
		
		return v;
	}
	
	public static Vector2d lerp(Vector2d v1, Vector2d v2, double t) {
		return v1.plus(v2.minus(v1).multiplied(t));
	}
	
	public static Vector2f lerp(Vector2f v1, Vector2f v2, float t) {
		return v1.plus(v2.minus(v1).multiplied(t));
	}
	
	public static Vector2i lerp(Vector2i v1, Vector2i v2, double t) {
		return v1.plus(v2.minus(v1).multiplied(t));
	}
	
	public static double angle(Vector2d v1, Vector2d v2) {
		return Math.acos(Vector2.dot(v1, v2) / (v1.magnitude() * v2.magnitude()));
	}
	
	public static float angle(Vector2f v1, Vector2f v2) {
		return (float) Math.acos(Vector2.dot(v1, v2) / (v1.magnitude() * v2.magnitude()));
	}
	
	public static double angle(Vector2i v1, Vector2i v2) {
		return Math.acos(Vector2.dot(v1, v2) / (v1.magnitude() * v2.magnitude()));
	}
	
	public static double distance(Vector2d v1, Vector2d v2) {
		return Math.sqrt((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y));
	}
	
	public static double distance(Vector2f v1, Vector2f v2) {
		return Math.sqrt((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y));
	}
	
	public static double distance(Vector2i v1, Vector2i v2) {
		return Math.sqrt((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y));
	}
}
