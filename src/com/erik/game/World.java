package com.erik.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.erik.engine.GameObject;
import com.erik.engine.Scene;
import com.erik.game.blocks.Dirt;
import com.erik.game.blocks.Grass;

public class World {
	private int tileSize = 64;
	
	public World() {}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public void setTileSize(int tileSize) {
		if (tileSize < 1) return;
		this.tileSize = tileSize;
	}
	
	public Scene loadData(String dataPath) {
		Scene scene = new Scene();
		
		try {
			String contents = Files.readString(Path.of(dataPath));
			scene = loadScene(contents);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return scene;
	}
	
	private Scene loadScene(String data) {
		Scene scene = new Scene();
		
		char[] chars = data.toCharArray();
		
		int row = 0;
		int column = 0;
		
		String val = "";
		
		for (char c : chars) {
			if (c == '\n') {
				row++;
				column = 0;
				continue;
			}
			
			if (Character.isDigit(c)) {
				val += c;
				continue;
			}
			
			switch (val) {
				case "1":
					scene.addGameObject(new Dirt(scene, column * tileSize, row * tileSize, tileSize));
					break;
					
				case "2":
					scene.addGameObject(new Grass(scene, column * tileSize, row * tileSize, tileSize));
					break;
			}
			
			val = "";
			column++;
		}

		return scene;
	}
}
