package com.soracasus.survivegame.inventory;

import com.soracasus.survivegame.items.Item;

public class InventorySlot {

	private Item item;
	private int count;

	public InventorySlot (Item item, int count) {
		this.item = null;
		this.count = 0;
	}

	public Item getItem () {
		return item;
	}

	public void setItem (Item item) {
		this.item = item;
	}

	public int getCount () {
		return count;
	}

	public void setCount (int count) {
		this.count = count;
	}

	@Override
	public String toString () {
		return "InventorySlot{" +
				"item:" + item +
				", count:" + count +
				'}';
	}
}
