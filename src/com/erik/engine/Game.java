package com.erik.engine;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.erik.engine.groups.PostDrawMember;
import com.erik.engine.groups.PostUpdateMember;
import com.erik.engine.groups.PreDrawMember;
import com.erik.engine.groups.PreUpdateMember;

public class Game implements Runnable {
	private Window window;
	private InputHandler input;
	private Renderer renderer;
	private Scene scene;
	private Camera camera;
	private Thread thread;

	private boolean running = false;
	private double maxFPS = 60.0f;
	
	float scale = 1.0f;
	
	private ArrayList<PreUpdateMember> preUpdateGroup = new ArrayList<>();
	private ArrayList<PostUpdateMember> postUpdateGroup = new ArrayList<>();
	private ArrayList<PreDrawMember> preDrawGroup = new ArrayList<>();
	private ArrayList<PostDrawMember> postDrawGroup = new ArrayList<>();
	
	public Game(String title, int width, int height) {
		window = new Window(title, width, height);
		input = new InputHandler(this);
		camera = new Camera();
		renderer = new Renderer(window, 0xff00ff);
		renderer.setCamera(camera);
		scene = new Scene();
	}
	
	public Game(String title, int width, int height, ImageIcon icon) {
		window = new Window(title, width, height, icon);
		input = new InputHandler(this);
		camera = new Camera();
		renderer = new Renderer(window, 0xff00ff);
		renderer.setCamera(camera);
		scene = new Scene();
	}
	
	public void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        
        int refreshRate = dm.getRefreshRate();
        
        if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
            System.out.println("Refresh rate is unknown, using " + (int) maxFPS + " as maximum FPS");
        }
        else {
            maxFPS = (double) refreshRate;
            System.out.println("Refresh rate: " + refreshRate + " Hz");
        }
        
        for (PreUpdateMember member : preUpdateGroup) {
        	member.start();
        }

        for (PostUpdateMember member : postUpdateGroup) {
        	member.start();
        }
        
		thread = new Thread(this);
		thread.run();
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		
		boolean render = false;
		double firstTime = 0.0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double deltaTime = 0.0;
		double unprocessedTime = 0.0;
		//double maxDeltaTime = 1; // not sure what to make this, or if i even need it
		
		double frameTime = 0.0;
		int fps = 0;
		int frames = 0;
		
		int updateCount = 0;
		int maxUpdates = 5;
		
		while (running) {
			render = false;
			updateCount = 0;
			
			firstTime = System.nanoTime() / 1000000000.0;
			deltaTime = firstTime - lastTime;
			lastTime = firstTime;
			
//			if (deltaTime > maxDeltaTime) {
//				deltaTime = maxDeltaTime;
//				System.out.println("Delta time too high, clamping");
//			}
			
			unprocessedTime += deltaTime;
			frameTime += deltaTime;
			
			double updateRate = 1.0 / maxFPS;
			
			while (unprocessedTime >= updateRate && updateCount < maxUpdates) {
				unprocessedTime -= updateRate;

//				input.update();
				update(updateRate);

				updateCount++;
				render = true;
			}
			
			if (frameTime >= 1.0) {
				//fps = (int) Math.round(frames / frameTime);
				fps = frames;
				frameTime = 0.0;
				frames = 0;
				System.out.println("FPS: " + fps);
			}
			
			if (!render) {
				Thread.yield();
				continue;
			}
			
			draw(unprocessedTime / updateRate);
			
			frames++;
		}
		
		dispose();
	}
	
	private void dispose() {
		
	}
	
	private void update(double delta) {
        for (PreUpdateMember member : preUpdateGroup) {
        	if (!member.active) continue;
        	member.update(delta);
        }
        
		ArrayList<GameObject> gameObjects = scene.getGameObjects();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update(delta, gameObjects);
		}

        for (PostUpdateMember member : postUpdateGroup) {
        	if (!member.active) continue;
        	member.update(delta);
        }
        
		input.update();
	}
	
	private void draw(double interpolation) {
		renderer.clear();
		
        for (PreDrawMember member : preDrawGroup) {
        	if (!member.active) continue;
        	member.draw(renderer);
        }
		
		//renderer.draw();
		
		ArrayList<GameObject> gameObjects = scene.getGameObjects();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).draw(renderer);
			//renderer.drawGameObject(gameObjects.get(i));
		}
		
        for (PostDrawMember member : postDrawGroup) {
        	if (!member.active) continue;
        	member.draw(renderer);
        }
		
		window.update();
	}

	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public void setWindow(Window window) {
		this.window = window;
	}
	
	public InputHandler getInputHandler() {
		return input;
	}
	
	public void setInputHandler(InputHandler input) {
		this.input = input;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void addToPreUpdateGroup(PreUpdateMember member) {
		preUpdateGroup.add(member);
	}
	
	public void removeFromPreUpdateGroup(PreUpdateMember member) {
		preUpdateGroup.remove(member);
	}
	
	public void addToPostUpdateGroup(PostUpdateMember member) {
		postUpdateGroup.add(member);
	}
	
	public void removeFromPostUpdateGroup(PostUpdateMember member) {
		postUpdateGroup.remove(member);
	}
	
	public void addToPreDrawGroup(PreDrawMember member) {
		preDrawGroup.add(member);
	}
	
	public void removeFromPreDrawGroup(PreDrawMember member) {
		preDrawGroup.remove(member);
	}
	
	public void addToPostDrawGroup(PostDrawMember member) {
		postDrawGroup.add(member);
	}
	
	public void removeFromPostDrawGroup(PostDrawMember member) {
		postDrawGroup.remove(member);
	}
}
