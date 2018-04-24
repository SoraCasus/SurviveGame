package com.soracasus.survivegame.states;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.utils.SCFile;
import com.soracasus.survivegame.worlds.World;

import java.awt.Graphics;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, new SCFile("worlds/world.json"));
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
