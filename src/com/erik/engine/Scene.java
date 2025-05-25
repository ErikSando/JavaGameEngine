package com.erik.engine;

import java.util.ArrayList;

public class Scene {
	public double gravity = 9.8;
	
	private ArrayList<ArrayList<GameObject>> layers = new ArrayList<ArrayList<GameObject>>();
	
	public ArrayList<GameObject> getGameObjects() {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		
		for (int i = 0; i < layers.size(); i++) {
			ArrayList<GameObject> layer = layers.get(i);
			
			for (int j = 0; j < layer.size(); j++) {
				gameObjects.add(layer.get(j));
			}
		}
		
		return gameObjects;
	}
	
	// right now, game object layers cant be changed without breaking removeGameObject
	
	public void addGameObject(GameObject gameObject) {
		int layer = gameObject.layer;
		
		while (layer >= layers.size()) {
			layers.add(new ArrayList<GameObject>());
		}
		
		//gameObject.setIndex(layers.get(layer).size());
		layers.get(layer).add(gameObject);
		gameObject.setScene(this);
	}
	
	public void addGameObjects(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			addGameObject(gameObject);
		}
	}
	
	public void removeGameObject(GameObject gameObject) {
		//layers.get(gameObject.layer).remove(gameObject.getIndex());
		layers.get(gameObject.layer).remove(gameObject);
	}
	
	public void removeGameObjects(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			removeGameObject(gameObject);
		}
	}
	
	public void changeGameObjectLayer(GameObject gameObject, int layer) {
		removeGameObject(gameObject);
		gameObject.layer = layer;
		addGameObject(gameObject);
	}
}
