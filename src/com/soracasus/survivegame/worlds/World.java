package com.soracasus.survivegame.worlds;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.EntityManager;
import com.soracasus.survivegame.entities.creatures.Player;
import com.soracasus.survivegame.entities.statics.Rock;
import com.soracasus.survivegame.entities.statics.Tree;
import com.soracasus.survivegame.items.ItemManager;
import com.soracasus.survivegame.json.JSONArray;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.tiles.Tile;
import com.soracasus.survivegame.utils.SCFile;
import com.soracasus.survivegame.utils.Utils;

import java.awt.Graphics;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	// Item
	private ItemManager itemManager;

	public World (Handler handler, SCFile world) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		// Temporary entity code!
		entityManager.addEntity(new Tree(handler, 3712, 1408));
		entityManager.addEntity(new Rock(handler, 132, 450));
		entityManager.addEntity(new Rock(handler, 350, 300));
		entityManager.addEntity(new Rock(handler, 400, 345));
		entityManager.addEntity(new Tree(handler, 625, 325));

		loadWorld(world);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.tiles[0];

		Tile t = Tile.tiles[tiles[x][y] - 1];
		if(t == null)
			return Tile.tiles[0];
		return t;
	}

	private void loadWorld (SCFile world) {

		JSONObject obj = Utils.loadJSONObject(world);
		JSONObject properties = obj.getJSONObject("properties");
		JSONArray layers = obj.getJSONArray("layers");
		JSONArray data = layers.getJSONObject(0).getJSONArray("data");

		width = obj.getInt("width");
		height = obj.getInt("height");
		spawnX = properties.getInt("spawnX") * Tile.TILEWIDTH;
		spawnY = properties.getInt("spawnY") * Tile.TILEHEIGHT;

		tiles = new int[width][height];

		int index = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = data.getInt(index++);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
}








