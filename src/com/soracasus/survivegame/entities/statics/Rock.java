package com.soracasus.survivegame.entities.statics;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.Entity;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.items.Item;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.tiles.Tile;

import java.awt.Graphics;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, 1);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

	@Override
	public Entity createNew () {
		return new Rock(handler, 0, 0);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.INSTANCE.getTexture("rock"), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void save (JSONObject obj) {

	}
}
