package com.soracasus.survivegame.entities;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.creatures.Player;
import com.soracasus.survivegame.json.JSONArray;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.Graphics;
import java.util.*;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private List<Entity> entityQueue;
	private Comparator<Entity> renderSorter = (a, b) -> Float.compare(a.getY() + a.getHeight(), b.getY() + b.getHeight());

	public EntityManager (Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		this.entities = new ArrayList<>();
		this.entityQueue = new ArrayList<>();
		entities.add(player);
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
        entities.addAll(entityQueue);
        entityQueue.clear();
	}

	public void render (Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
		player.postRender(g);
	}

	public void loadEntities (JSONObject save) {
		JSONArray arr = save.getJSONArray("entities");
		entities.clear();
		entities.add(player);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			if (obj.getInt("id") == player.id) {
				player.setX(obj.getInt("x"));
				player.setY(obj.getInt("y"));
				continue;
			}
			Entity e = Entity.entities[obj.getInt("id")].createNew();
			e.setX(obj.getInt("x"));
			e.setY(obj.getInt("y"));
			this.addEntity(e);
		}
	}

	public void saveEntities (JSONObject obj) {
		JSONArray arr = new JSONArray();
		for (Entity e : entities) {
			JSONObject ent = new JSONObject();
			e.save(ent);
			arr.put(ent);
		}
		obj.put("entities", arr);
	}

	public void addEntity (Entity e) {
		if(e instanceof Player) return;
	    entityQueue.add(e);
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
