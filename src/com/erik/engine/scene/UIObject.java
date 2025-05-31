package com.erik.engine.scene;

import com.erik.engine.Colour;
import com.erik.engine.Renderer;
import com.erik.engine.enums.TextAlignmentH;
import com.erik.engine.enums.TextAlignmentV;
import com.erik.engine.vector2.Vector2i;

public class UIObject {
	protected Scene scene;
	public Vector2i position = Vector2i.zero();
	public Vector2i scale = new Vector2i(100, 50);
	protected double backgroundOpacity = 1.0;
	protected double outlineOpacity = 1.0;
	protected double textOpacity = 1.0;
	protected double opacity = 1.0;
	public int backgroundColour = Colour.fromRGB(180, 180, 180);
	public int outlineColour = Colour.fromRGB(120, 120, 120);
	public int textColour = Colour.White;
	public int outlineThickness = 1;
	// protected font
	protected String text;
	protected TextAlignmentH textAlignmentH = TextAlignmentH.Centre;
	protected TextAlignmentV textAlignmentV = TextAlignmentV.Centre;
	
	int layer = 0; // package private
	
	protected void init(Vector2i position, Vector2i scale) {
		this.position = position;
		this.scale = scale;
	}
	
	protected void addToScene(Scene scene) {
		this.scene = scene;
		scene.addUIObject(this);
	}
	
	public UIObject() {}
	
	public UIObject(Scene scene) {
		addToScene(scene);
	}
	
	public UIObject(Scene scene, Vector2i position, Vector2i scale) {
		init(position, scale);
		addToScene(scene);
	}
	
	public UIObject(Scene scene, int x, int y, int w, int h) {
		init(new Vector2i(x, y), new Vector2i(w, h));
		addToScene(scene);
	}
	
	public UIObject(Vector2i position, Vector2i scale) {
		init(position, scale);
	}
	
	public UIObject(int x, int y, int w, int h) {
		init(new Vector2i(x, y), new Vector2i(w, h));
	}
	
	public void draw(Renderer renderer) {
		renderer.outlineRectangle(position.x, position.y, scale.x, scale.y, outlineColour, outlineOpacity, outlineThickness);
		renderer.drawRectangle(position.x, position.y, scale.x, scale.y, backgroundColour, backgroundOpacity);
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		scene.changeUIObjectLayer(this, layer);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public double getBackgroundOpacity() {
		return backgroundOpacity;
	}
	
	public void setBackgroundOpacity(double opacity) {
		if (opacity < 0.0 || opacity > 1.0) return;
		backgroundOpacity = opacity;
	}
	
	public double getOutlineOpacity() {
		return outlineOpacity;
	}
	
	public void setOutlineOpacity(double opacity) {
		if (opacity < 0.0 || opacity > 1.0) return;
		outlineOpacity = opacity;
	}
	
	public double getTextOpacity() {
		return textOpacity;
	}
	
	public void setTextOpacity(double opacity) {
		if (opacity < 0.0 || opacity > 1.0) return;
		textOpacity = opacity;
	}
}
