package com.soracasus.survivegame.tiles;

import com.soracasus.survivegame.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.INSTANCE.getTexture("stone"), id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
