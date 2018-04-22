package com.soracasus.survivegame.states;

import java.awt.Graphics;

import com.soracasus.survivegame.worlds.World;
import com.soracasus.survivegame.Handler;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
