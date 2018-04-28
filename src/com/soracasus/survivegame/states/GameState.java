package com.soracasus.survivegame.states;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.utils.SCFile;
import com.soracasus.survivegame.utils.Utils;
import com.soracasus.survivegame.worlds.World;

import java.awt.Graphics;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, new SCFile("worlds/world.json"));
		handler.setWorld(world);
	}

	public void loadFromFile () {
		JSONObject obj = Utils.loadJSONObject(new SCFile("save/save1.json"));

		System.out.println("Loading from file!");

		handler.getWorld().getEntityManager().getPlayer().getInventory().loadFromFile(obj);
		handler.getWorld().getItemManager().loadItems(obj);

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
