package com.erik.engine.vector2;

public class Vector2d {
	public double x;
	public double y;
	
	public Vector2d() {
		x = 0.0;
		y = 0.0;
	}
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2d normalised() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return Vector2d.zero();
		}
		
		return new Vector2d(x / magnitude, y / magnitude);
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
	
	public Vector2d plus(Vector2d other) {
		return new Vector2d(x + other.x, y + other.y);
	}
	
	public Vector2d plus(Vector2f other) {
		return new Vector2d(x + other.x, y + other.y);
	}
	
	public Vector2d plus(Vector2i other) {
		return new Vector2d(x + other.x, y + other.y);
	}
	
	public Vector2d minus(Vector2d other) {
		return new Vector2d(x - other.x, y - other.y);
	}
	
	public Vector2d minus(Vector2f other) {
		return new Vector2d(x - other.x, y - other.y);
	}

	public Vector2d minus(Vector2i other) {
		return new Vector2d(x - other.x, y - other.y);
	}
	
	public Vector2d multiplied(double k) {
		return new Vector2d(x * k, y * k);
	}
	
	public Vector2d multiplied(float k) {
		return new Vector2d(x * k, y * k);
	}
	
	public Vector2d multiplied(int k) {
		return new Vector2d(x * k, y * k);
	}
	
	public Vector2d divided(double k) {
		return new Vector2d(x / k, y / k);
	}
	
	public Vector2d divided(float k) {
		return new Vector2d(x / k, y / k);
	}
	
	public Vector2d divided(int k) {
		return new Vector2d(x / k, y / k);
	}
	
	public Vector2d inversed() {
		return new Vector2d(-x, -y);
	}
	
	public void add(Vector2d other) {
		x += other.x;
		y += other.y;
	}
	
	public void add(Vector2f other) {
		x += other.x;
		y += other.y;
	}
	
	public void add(Vector2i other) {
		x += other.x;
		y += other.y;
	}
	
	public void subtract(Vector2d other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void subtract(Vector2f other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void subtract(Vector2i other) {
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
	
	public double dot(Vector2d other) {
		return x * other.x + y * other.y;
	}
	
	public double dot(Vector2f other) {
		return x * other.x + y * other.y;
	}
	
	public double dot(Vector2i other) {
		return x * other.x + y * other.y;
	}
	
	public double cross(Vector2d other) {
		return x * other.y - y * other.x;
	}
	
	public double cross(Vector2f other) {
		return x * other.y - y * other.x;
	}
	
	public double cross(Vector2i other) {
		return x * other.y - y * other.x;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public static Vector2d zero() {
		return new Vector2d(0.0, 0.0);
	}
	
	public static Vector2d one() {
		return new Vector2d(1.0, 1.0);
	}
	
	public static Vector2d up() {
		return new Vector2d(0.0, -1.0);
	}
	
	public static Vector2d down() {
		return new Vector2d(0.0, 1.0);
	}
	
	public static Vector2d left() {
		return new Vector2d(-1.0, 0.0);
	}
	
	public static Vector2d right() {
		return new Vector2d(1.0, 0.0);
	}
	
	public static Vector2d fromAngle(double angle) {
		return new Vector2d(Math.cos(angle), Math.sin(angle));
	}
}
