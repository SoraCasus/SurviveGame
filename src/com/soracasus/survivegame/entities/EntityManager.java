package com.soracasus.survivegame.entities;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.creatures.Player;
import com.soracasus.survivegame.json.JSONArray;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare (Entity a, Entity b) {
			if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};

	public EntityManager (Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}

	public void tick () {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			e.tick();
			if (!e.isActive())
				it.remove();
		}
		entities.sort(renderSorter);
	}

	public void render (Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
		player.postRender(g);
	}

	public void saveEntities (JSONObject obj) {
		JSONArray arr = new JSONArray();
		for (Entity e : entities) {
			JSONObject ent = new JSONObject();

			ent.put("x", e.getCenterX());
			ent.put("y", e.getCenterY());
			ent.put("width", e.getWidth());
			ent.put("height", e.getHeight());
			ent.put("health", e.getHealth());
			ent.put("active", e.isActive());
			JSONObject bounds = new JSONObject();
			bounds.put("x", e.getBounds().x);
			bounds.put("y", e.getBounds().y);
			bounds.put("width", e.getBounds().width);
			bounds.put("height", e.getBounds().height);
			ent.put("bounds", bounds);
		}
	}

	public void addEntity (Entity e) {
		entities.add(e);
	}

	//GETTERS SETTERS

	public Handler getHandler () {
		return handler;
	}

	public void setHandler (Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer () {
		return player;
	}

	public void setPlayer (Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities () {
		return entities;
	}

	public void setEntities (ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
