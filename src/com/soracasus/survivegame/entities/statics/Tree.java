package com.soracasus.survivegame.entities.statics;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.items.Item;
import com.soracasus.survivegame.tiles.Tile;

import java.awt.Graphics;
import java.util.Random;

public class Tree extends StaticEntity {

	private Random random;

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

		random = new Random();

		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y, random.nextInt(5) + 1));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.INSTANCE.getTexture("tree"), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
