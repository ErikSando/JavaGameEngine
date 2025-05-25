package com.erik.engine;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2 normalised() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return Vector2.zero();
		}
		
		return new Vector2(x / magnitude, y / magnitude);
	}
	
	public void normalise() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return;
		}
		
		x /= magnitude;
		y /= magnitude;
	}
	
	public void clamp(double min, double max) {
		double magnitude = magnitude();
		
		if (magnitude < min) {
			normalise();
			multiply(min);
		}
		
		if (magnitude > max) {
			normalise();
			multiply(max);
		}
	}
	
	public Vector2 plus(Vector2 other) {
		return new Vector2(x + other.x, y + other.y);
	}
	
	public Vector2 minus(Vector2 other) {
		return new Vector2(x - other.x, y - other.y);
	}
	
	public Vector2 multiplied(double k) {
		return new Vector2(x * k, y * k);
	}
	
	public Vector2 divided(double k) {
		return new Vector2(x / k, y / k);
	}
	
	public Vector2 inversed() {
		return new Vector2(-x, -y);
	}
	
	public void add(Vector2 other) {
		x += other.x;
		y += other.y;
	}
	
	public void subtract(Vector2 other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void multiply(double k) {
		x *= k;
		y *= k;
	}
	
	public void divide(double k) {
		x /= k;
		y /= k;
	}
	
	public void inverse() {
		x = -x;
		y = -y;
	}
	
	public double dot(Vector2 other) {
		return x * other.x + y * other.y;
	}
	
	public double cross(Vector2 other) {
		return x * other.y - y * other.x;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public static Vector2 zero() {
		return new Vector2(0.0, 0.0);
	}
	
	public static Vector2 one() {
		return new Vector2(1.0, 1.0);
	}
	
	public static Vector2 up() {
		return new Vector2(0.0, -1.0);
	}
	
	public static Vector2 down() {
		return new Vector2(0.0, 1.0);
	}
	
	public static Vector2 left() {
		return new Vector2(-1.0, 0.0);
	}
	
	public static Vector2 right() {
		return new Vector2(1.0, 0.0);
	}
	
	public static double dot(Vector2 v1, Vector2 v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	public static Vector2 clamp(Vector2 v, double min, double max) {
		double magnitude = v.magnitude();
		
		
		if (magnitude < min) {
			return v.normalised().multiplied(min);
		}
		
		if (magnitude > max) {
			return v.normalised().multiplied(max);
		}
		
		return v;
	}
	
	public static Vector2 lerp(Vector2 v1, Vector2 v2, double t) {
		return v1.plus(v2.minus(v1).multiplied(t));
	}
	
	public static double angle(Vector2 v1, Vector2 v2) {
		return Math.acos(Vector2.dot(v1, v2) / (v1.magnitude() * v2.magnitude()));
	}
	
	public static double distance(Vector2 v1, Vector2 v2) {
		return Math.sqrt((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y));
	}
	
	public static Vector2 fromAngle(double angle) {
		return new Vector2(Math.cos(angle), Math.sin(angle));
	}
}
