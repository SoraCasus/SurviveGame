package com.soracasus.survivegame.items;

import com.soracasus.survivegame.entities.creatures.Player;
import com.soracasus.survivegame.gfx.Assets;

import java.awt.event.KeyEvent;

public class WoodItem extends Item {

	private static final int DISTANCE = 50;

	public WoodItem () {
		super(Assets.INSTANCE.getTexture("wood"), "Wood", 0);
		this.hasAction = true;
	}

	@Override
	public void onAction () {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
			// Place item in world
			Player player = handler.getWorld().getEntityManager().getPlayer();
			int dir = player.getCurrentDirection();
			int dx = 0;
			int dy = 0;

			if (dir == Player.UP)
				dy = -DISTANCE;
			if (dir == Player.DOWN)
				dy = DISTANCE;
			if (dir == Player.LEFT)
				dx = -DISTANCE;
			if (dir == Player.RIGHT)
				dx = DISTANCE;

			handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (player.getCenterX() + dx), (int) (player.getCenterY() + dy)));

			// Remove decrement item count
			int count = player.getInventory().getSelectedSlot().getCount();
			player.getInventory().getSelectedSlot().setCount(count - 1);

			if (player.getInventory().getSelectedSlot().getCount() <= 0)
				player.getInventory().getSelectedSlot().setItem(null);
		}
	}

	@Override
	public Item createNew (int count) {
		WoodItem i = new WoodItem();
		i.setCount(count);
		return i;
	}

	@Override
	public Item createNew (int x, int y) {
		WoodItem i = new WoodItem();
		i.setPosition(x, y);
		return i;
	}

	@Override
	public Item createNew (int x, int y, int count) {
		WoodItem i = new WoodItem();
		i.setCount(count);
		i.setPosition(x, y);
		return i;
	}


}
