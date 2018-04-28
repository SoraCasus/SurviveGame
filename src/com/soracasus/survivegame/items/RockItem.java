package com.soracasus.survivegame.items;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;

public class RockItem extends Item {

	public RockItem (Handler handler) {
		super(handler, Assets.INSTANCE.getTexture("rock"), "Rock", 1);
		this.hasAction = false;
	}

	@Override
	public void onAction () {

	}

	@Override
	public Item createNew (int x, int y) {
		RockItem i = new RockItem(handler);
		i.setPosition(x, y);
		return i;
	}
}
