package com.erik.engine.scene;

import java.util.ArrayList;

import com.erik.engine.gfx.Image;

public class Scene {
	public static final int MAX_LAYER = 20;
	public double gravity = 1000;

	private boolean sorted = false;
	private boolean uiSorted = false;
	
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private ArrayList<ArrayList<GameObject>> layers = new ArrayList<ArrayList<GameObject>>();
	
	private ArrayList<UIObject> uiObjects = new ArrayList<>();
	private ArrayList<ArrayList<UIObject>> uiLayers = new ArrayList<ArrayList<UIObject>>();
//	private ArrayList<ArrayList<StaticGameObject>> staticLayers = new ArrayList<ArrayList<StaticGameObject>>();
	
//	private Camera camera = new Camera();
	
	private Image backgroundImage;
	
	public Scene() {}
	
//	public Camera getCamera() {
//		return camera;
//	}
//	
//	public void setCamera(Camera camera) {
//		this.camera = camera;
//	}
	
	private void sortGameObjects() {
		gameObjects.clear();
		
		for (int i = 0; i < layers.size(); i++) {
			ArrayList<GameObject> layer = layers.get(i);
			
			for (int j = 0; j < layer.size(); j++) {
				gameObjects.add(layer.get(j));
			}
		}

		sorted = true;
	}
	
	public ArrayList<GameObject> getGameObjects() {
		if (!sorted) sortGameObjects();
		
		return gameObjects;
	}
	
	private void sortUIObjects() {
		uiObjects.clear();
		
		for (int i = 0; i < uiLayers.size(); i++) {
			ArrayList<UIObject> layer = uiLayers.get(i);
			
			for (int j = 0; j < layer.size(); j++) {
				uiObjects.add(layer.get(j));
			}
		}

		uiSorted = true;
	}
	
	public ArrayList<UIObject> getUIObjects() {
		if (!uiSorted) sortUIObjects();
		
		return uiObjects;
	}
	
//	public ArrayList<StaticGameObject> getStaticGameObjects() {
//		ArrayList<StaticGameObject> staticGameObjects = new ArrayList<>();
//		
//		for (int i = 0; i < layers.size(); i++) {
//			ArrayList<StaticGameObject> layer = staticLayers.get(i);
//			
//			for (int j = 0; j < layer.size(); j++) {
//				staticGameObjects.add(layer.get(j));
//			}
//		}
//		
//		return staticGameObjects;
//	}
	
	// there might be a better way to handle both updating and static game ojects (using inheritance for example),
	// but I want them to be in seperate lists so I'm using seperate classes for now
	
	public void addGameObject(GameObject gameObject) {
		int layer = gameObject.getLayer();
		
		while (layer >= layers.size()) {
			layers.add(new ArrayList<GameObject>());
		}
		
		//gameObject.setIndex(layers.get(layer).size());
		layers.get(layer).add(gameObject);
		sorted = false;
		gameObject.setScene(this);
	}
	
//	public void addStaticGameObject(StaticGameObject gameObject) {
//		int layer = gameObject.getLayer();
//		
//		while (layer >= layers.size()) {
//			staticLayers.add(new ArrayList<StaticGameObject>());
//		}
//		
//		//gameObject.setIndex(layers.get(layer).size());
//		staticLayers.get(layer).add(gameObject);
//		gameObject.setScene(this);
//	}
	
	public void addGameObjects(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			addGameObject(gameObject);
		}
	}
	
	public void removeGameObject(GameObject gameObject) {
//		layers.get(gameObject.layer).remove(gameObject.getIndex());
		layers.get(gameObject.getLayer()).remove(gameObject);
		sorted = false;
	}
	
	public void removeGameObjects(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			removeGameObject(gameObject);
		}
	}
	
	public void changeGameObjectLayer(GameObject gameObject, int layer) {
		if (layer < 0 || layer > MAX_LAYER) return;
		
		removeGameObject(gameObject);
		gameObject.layer = layer;
		addGameObject(gameObject);
	}
	
	public void addUIObject(UIObject uiObject) {
		int layer = uiObject.getLayer();
		
		while (layer >= uiLayers.size()) {
			uiLayers.add(new ArrayList<UIObject>());
		}
		
		uiLayers.get(layer).add(uiObject);
		uiSorted = false;
		uiObject.setScene(this);
	}
	
	public void addUIObjects(UIObject... uiObjects) {
		for (UIObject uiObject : uiObjects) {
			addUIObject(uiObject);
		}
	}
	
	public void removeUIObject(UIObject uiObject) {
		uiLayers.get(uiObject.getLayer()).remove(uiObject);
		uiSorted = false;
	}
	
	public void removeUIObjects(UIObject... uiObjects) {
		for (UIObject uiObject : uiObjects) {
			removeUIObject(uiObject);
		}
	}
	
	public void changeUIObjectLayer(UIObject uiObject, int layer) {
		if (layer < 0 || layer > MAX_LAYER) return;
		
		removeUIObject(uiObject);
		uiObject.layer = layer;
		addUIObject(uiObject);
	}
}
