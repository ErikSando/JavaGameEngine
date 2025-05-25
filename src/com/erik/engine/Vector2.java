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
	
	static Vector2 zero() {
		return new Vector2(0.0, 0.0);
	}
	
	static Vector2 one() {
		return new Vector2(1.0, 1.0);
	}
	
	static Vector2 up() {
		return new Vector2(0.0, -1.0);
	}
	
	static Vector2 down() {
		return new Vector2(0.0, 1.0);
	}
	
	static Vector2 left() {
		return new Vector2(-1.0, 0.0);
	}
	
	static Vector2 right() {
		return new Vector2(1.0, 0.0);
	}
}
