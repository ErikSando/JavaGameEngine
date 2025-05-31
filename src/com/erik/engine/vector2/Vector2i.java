package com.erik.engine.vector2;

public class Vector2i {
	public int x;
	public int y;
	
	public Vector2i() {
		x = 0;
		y = 0;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2i normalised() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return Vector2i.zero();
		}
		
		return new Vector2i((int) (x / magnitude), (int) (y / magnitude));
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
	
	public Vector2i plus(Vector2i other) {
		return new Vector2i(x + other.x, y + other.y);
	}
	
	public Vector2i plus(Vector2d other) {
		return new Vector2i(x + (int) other.x, y + (int) other.y);
	}
	
	public Vector2i minus(Vector2i other) {
		return new Vector2i(x - other.x, y - other.y);
	}
	
	public Vector2i minus(Vector2d other) {
		return new Vector2i(x - (int) other.x, y - (int) other.y);
	}
	
	public Vector2i multiplied(double k) {
		return new Vector2i((int) (x * k), (int) (y * k));
	}
	
	public Vector2i divided(double k) {
		return new Vector2i((int) (x / k), (int) (y / k));
	}
	
	public Vector2i inversed() {
		return new Vector2i(-x, -y);
	}
	
	public void add(Vector2i other) {
		x += other.x;
		y += other.y;
	}
	
	public void add(Vector2d other) {
		x += other.x;
		y += other.y;
	}
	
	public void subtract(Vector2i other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void subtract(Vector2d other) {
		x -= (int) other.x;
		y -= (int) other.y;
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
	
	public double dot(Vector2i other) {
		return x * other.x + y * other.y;
	}
	
	public double cross(Vector2i other) {
		return x * other.y - y * other.x;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public static Vector2i zero() {
		return new Vector2i(0, 0);
	}
	
	public static Vector2i one() {
		return new Vector2i(1, 1);
	}
	
	public static Vector2i up() {
		return new Vector2i(0, -1);
	}
	
	public static Vector2i down() {
		return new Vector2i(0, 1);
	}
	
	public static Vector2i left() {
		return new Vector2i(-1, 0);
	}
	
	public static Vector2i right() {
		return new Vector2i(1, 0);
	}
}
