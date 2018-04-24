package com.soracasus.survivegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE

	public static Tile[] tiles;

	public static void initTiles (int size) {
		tiles = new Tile[size];
	}
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
