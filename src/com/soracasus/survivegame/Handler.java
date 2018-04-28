package com.soracasus.survivegame;

import com.soracasus.survivegame.gfx.GameCamera;
import com.soracasus.survivegame.input.KeyManager;
import com.soracasus.survivegame.input.MouseManager;
import com.soracasus.survivegame.worlds.World;

public class Handler {

	public static Handler instance;

	public static void initialize (Game game) {
		instance = new Handler(game);
	}


	private Game game;
	private World world;

	public Handler(Game game){
		this.game = game;
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
