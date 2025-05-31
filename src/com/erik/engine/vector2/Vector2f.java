package com.erik.engine.vector2;

public class Vector2f {
	public float x;
	public float y;
	
	public Vector2f() {
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2f normalised() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return Vector2f.zero();
		}
		
		return new Vector2f((float) (x / magnitude), (float) (y / magnitude));
	}
	
	public void normalise() {
		double magnitude = magnitude();
		
		if (magnitude == 0) {
			return;
		}
		
		x /= magnitude;
		y /= magnitude;
	}
	
	public void clamp(float min, float max) {
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
	
	public Vector2f plus(Vector2f other) {
		return new Vector2f(x + other.x, y + other.y);
	}
	
	public Vector2f minus(Vector2f other) {
		return new Vector2f(x - other.x, y - other.y);
	}
	
	public Vector2f multiplied(float k) {
		return new Vector2f(x * k, y * k);
	}
	
	public Vector2f multiplied(double k) {
		return new Vector2f(x * (float) k, y * (float) k);
	}
	
	public Vector2f multiplied(int k) {
		return new Vector2f(x * k, y * k);
	}
	
	public Vector2f divided(float k) {
		return new Vector2f(x / k, y / k);
	}
	
	public Vector2f inversed() {
		return new Vector2f(-x, -y);
	}
	
	public void add(Vector2f other) {
		x += other.x;
		y += other.y;
	}
	
	public void add(Vector2d other) {
		x += other.x;
		y += other.y;
	}

	public void add(Vector2i other) {
		x += other.x;
		y += other.y;
	}
	
	public void subtract(Vector2f other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void multiply(float k) {
		x *= k;
		y *= k;
	}
	
	public void divide(float k) {
		x /= k;
		y /= k;
	}
	
	public void inverse() {
		x = -x;
		y = -y;
	}
	
	public float dot(Vector2f other) {
		return x * other.x + y * other.y;
	}
	
	public float cross(Vector2f other) {
		return x * other.y - y * other.x;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public static Vector2f zero() {
		return new Vector2f(0.0f, 0.0f);
	}
	
	public static Vector2f one() {
		return new Vector2f(1.0f, 1.0f);
	}
	
	public static Vector2f up() {
		return new Vector2f(0.0f, -1.0f);
	}
	
	public static Vector2f down() {
		return new Vector2f(0.0f, 1.0f);
	}
	
	public static Vector2f left() {
		return new Vector2f(-1.0f, 0.0f);
	}
	
	public static Vector2f right() {
		return new Vector2f(1.0f, 0.0f);
	}
	
	public static Vector2f fromAngle(float angle) {
		return new Vector2f((float) Math.cos(angle), (float) Math.sin(angle));
	}
}
