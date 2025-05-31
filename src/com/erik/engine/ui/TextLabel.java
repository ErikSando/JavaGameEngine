package com.erik.engine.ui;

import com.erik.engine.scene.Scene;
import com.erik.engine.scene.UIObject;
import com.erik.engine.vector2.Vector2i;

public class TextLabel extends UIObject {
	private void init(String text, Vector2i position, Vector2i scale) {
		this.text = text;
		this.position = position;
		this.scale = scale;
	}
	
	public TextLabel(Scene scene, String text, Vector2i position, Vector2i scale) {
		init(text, position, scale);
		addToScene(scene);
	}
	

	public TextLabel(Scene scene, String text, int x, int y, int w, int h) {
		init(text, new Vector2i(x, y), new Vector2i(w, h));
		addToScene(scene);
	}
	
	public TextLabel(String text, Vector2i position, Vector2i scale) {
		init(text, position, scale);
	}
	

	public TextLabel(String text, int x, int y, int w, int h) {
		init(text, new Vector2i(x, y), new Vector2i(w, h));
	}
}
