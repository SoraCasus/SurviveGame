package com.soracasus.survivegame.items;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.json.JSONArray;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<>();
	}
	
	public void tick(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item i = it.next();
			i.tick();
			if(i.isPickedUp())
				it.remove();
		}
	}
	
	public void render(Graphics g){
		for(Item i : items)
			i.render(g);
	}
	
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}

	public void saveItems (JSONObject file) {

		JSONArray items = new JSONArray();
		for (Item i : this.items) {
			JSONObject item = new JSONObject();
			item.put("x", i.getX());
			item.put("y", i.getY());
			item.put("name", i.getName());
			item.put("id", i.getId());
			item.put("pickedUp", i.isPickedUp());
			items.put(item);
		}

		file.put("items", items);

	}

	public void loadItems (JSONObject file) {

		JSONArray items = file.getJSONArray("items");
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);
			Item it = Item.items[item.getInt("id")];
			it.setX(item.getInt("x"));
			it.setY(item.getInt("y"));
			it.setPickedUp(item.getBoolean("pickedUp"));
			it.setName(item.getString("name"));
			this.items.add(it);
		}

	}

	// Getters and Setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
