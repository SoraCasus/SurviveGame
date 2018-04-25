package com.soracasus.survivegame.items;

import com.soracasus.survivegame.gfx.Assets;

public class RockItem extends Item {

	public RockItem () {
		super(Assets.INSTANCE.getTexture("rock"), "Rock", 1);
		this.hasAction = false;
	}

	@Override
	public void onAction () {

	}


	@Override
	public Item createNew (int count) {
		RockItem i = new RockItem();
		i.setPosition(x, y);
		return i;
	}

	@Override
	public Item createNew (int x, int y) {
		RockItem i = new RockItem();
		i.setPosition(x, y);
		return i;
	}

	@Override
	public Item createNew (int x, int y, int count) {
		RockItem i = new RockItem();
		i.setCount(count);
		i.setPosition(x, y);
		return i;
	}
}
