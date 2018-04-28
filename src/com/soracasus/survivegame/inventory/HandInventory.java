package com.soracasus.survivegame.inventory;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.gfx.Text;
import com.soracasus.survivegame.items.Item;
import com.soracasus.survivegame.json.JSONArray;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.menu.Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class HandInventory extends Menu {

	private static final int
			INV_X = 64,
			INV_Y = 48,
			INV_W = 512,
			INV_H = 384,
			INV_LIST_CENTER_X = INV_X + 171,
			INV_Y_OFFSET = 95,
			INV_LIST_SPACE = 30;

	private static final int
			INV_IMG_X = 452,
			INV_IMG_Y = 65,
			INV_IMG_W = 64,
			INV_IMG_H = 64;

	private static final int
			INV_COUNT_X = 485,
			INV_COUNT_Y = 156;

	private static final int
			HAND_LEFT = 0,
			HAND_RIGHT = 1;

	private static final int
			RH_BOX_X = 500,
			RH_BOX_Y = 410,
			RH_BOX_W = 90 / 2,
			RH_BOX_H = 123 / 2;

	private static final int
			LH_BOX_X = 575,
			LH_BOX_Y = 410,
			LH_BOX_W = 90 / 2,
			LH_BOX_H = 123 / 2;


	private Handler handler;
	private InventorySlot[] slots;

	private InventorySlot leftHand;
	private InventorySlot rightHand;
	private int selectedHand;

	private int selectedItem;

	public HandInventory (Handler handler) {
		System.out.println("Initialize inventory");
		this.slots = new InventorySlot[11];
		for (int i = 0; i < slots.length; i++)
			slots[i] = new InventorySlot(null, 0);
		this.handler = handler;
		this.selectedItem = 0;
		this.leftHand = new InventorySlot(null, 0);
		this.rightHand = new InventorySlot(null, 0);
		this.selectedHand = HAND_LEFT;
	}

	public InventorySlot getSelectedSlot () {
		switch (selectedHand) {
			case HAND_LEFT:
				return leftHand;
			case HAND_RIGHT:
				return rightHand;
		}
		return null;
	}

	@Override
	public void tick () {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
			// Switch active hand
			if (selectedHand == HAND_LEFT) {
				System.out.println("Swap to right hand");
				System.out.println(rightHand.toString());
				selectedHand = HAND_RIGHT;
			} else {
				System.out.println("Swap to left hand");
				System.out.println(leftHand.toString());
				selectedHand = HAND_LEFT;
			}
		}

		if (active)
			tickActive();
		else
			tickInactive();
	}

	private void tickActive () {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;

		if (selectedItem < 0)
			selectedItem = slots.length - 1;
		else if (selectedItem >= slots.length)
			selectedItem = 0;

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
			// selected slot is occupied

			if (getSelectedSlot().getItem() == null)
				handIsEmpty();
			else
				handIsFull();
		}
	}

	private void handIsEmpty () {
		if (slots[selectedItem].getItem() != null) {
			// Pick up the item
			getSelectedSlot().setItem(slots[selectedItem].getItem());
			getSelectedSlot().setCount(slots[selectedItem].getCount());
			slots[selectedItem].setItem(null);
			slots[selectedItem].setCount(0);
		}
		// Otherwise nothing happens
	}

	private void handIsFull () {
		if (slots[selectedItem].getItem() != null) {
			// Check if items are equal
			if (slots[selectedItem].getItem().getId() == getSelectedSlot().getItem().getId()) {
				System.out.println("Items are equal!");
				// Merge items in inventory
				slots[selectedItem].setCount(slots[selectedItem].getCount() + getSelectedSlot().getCount());
				getSelectedSlot().setItem(null);
				getSelectedSlot().setCount(0);
			} else {
				// Swap items
				Item tmpItem = getSelectedSlot().getItem();
				int tmpCount = getSelectedSlot().getCount();

				getSelectedSlot().setItem(slots[selectedItem].getItem());
				getSelectedSlot().setCount(slots[selectedItem].getCount());

				slots[selectedItem].setItem(tmpItem);
				slots[selectedItem].setCount(tmpCount);
			}
		} else {
			// Place the item  in inventory
			slots[selectedItem].setItem(getSelectedSlot().getItem());
			slots[selectedItem].setCount(getSelectedSlot().getCount());
			getSelectedSlot().setItem(null);
			getSelectedSlot().setCount(0);
		}
	}

	private void tickInactive () {

	}

	@Override
	public void render (Graphics g) {
		// Draw inventory screen if active
		if (active) {
			g.drawImage(Assets.INSTANCE.getTexture("inventoryScreen"), INV_X, INV_Y, INV_W, INV_H, null);

			for (int i = 0; i < slots.length; i++) {
				if (slots[i].getItem() == null) {
					if (i == selectedItem) {
						Text.drawString(g, "-----", INV_LIST_CENTER_X, i * INV_LIST_SPACE + INV_Y_OFFSET,
								true, Color.WHITE, Assets.INSTANCE.getFont("slkscr"));
						continue;
					}
					continue;
				}

				// If the item is highlighted
				if (selectedItem == i) {
					String name = slots[i].getItem().getName();
					Text.drawString(g, "> " + name + " <", INV_LIST_CENTER_X,
							i * INV_LIST_SPACE + INV_Y_OFFSET, true, Color.YELLOW, Assets.INSTANCE.getFont("slkscr"));
				} else {
					String name = slots[i].getItem().getName();
					Text.drawString(g, name, INV_LIST_CENTER_X, i * INV_LIST_SPACE + INV_Y_OFFSET,
							true, Color.WHITE, Assets.INSTANCE.getFont("slkscr"));
				}
			}

			// Draw the preview icon
			if (slots[selectedItem].getItem() != null) {
				Item item = slots[selectedItem].getItem();
				g.drawImage(item.getTexture(), INV_IMG_X, INV_IMG_Y, INV_IMG_W, INV_IMG_H, null);
				Text.drawString(g, Integer.toString(slots[selectedItem].getCount()), INV_COUNT_X, INV_COUNT_Y, true,
						Color.WHITE, Assets.INSTANCE.getFont("slkscr"));
			}

			if (rightHand.getItem() != null) {
				Item item = rightHand.getItem();
				g.drawImage(item.getTexture(), INV_IMG_X, INV_IMG_Y + 123, INV_IMG_W, INV_IMG_H, null);
				Text.drawString(g, Integer.toString(rightHand.getCount()), INV_COUNT_X, INV_COUNT_Y + 123, true,
						Color.WHITE, Assets.INSTANCE.getFont("slkscr"));
			}

			if (leftHand.getItem() != null) {
				Item item = leftHand.getItem();
				g.drawImage(item.getTexture(), INV_IMG_X, INV_IMG_Y + 123 + 123, INV_IMG_W, INV_IMG_H, null);
				Text.drawString(g, Integer.toString(leftHand.getCount()), INV_COUNT_X + 3, INV_COUNT_Y + 123 + 123, true,
						Color.WHITE, Assets.INSTANCE.getFont("slkscr"));
			}
		} else {
			if (selectedHand == HAND_RIGHT)
				g.drawImage(Assets.INSTANCE.getTexture("rightHandBoxSel"), RH_BOX_X, RH_BOX_Y, RH_BOX_W, RH_BOX_H, null);
			else
				g.drawImage(Assets.INSTANCE.getTexture("rightHandBox"), RH_BOX_X, RH_BOX_Y, RH_BOX_W, RH_BOX_H, null);

			if (rightHand.getItem() != null) {
				g.drawImage(rightHand.getItem().getTexture(), RH_BOX_X + 5, RH_BOX_Y + 5, 32, 32, null);
				Text.drawString(g, Integer.toString(rightHand.getCount()), RH_BOX_X + 23, RH_BOX_Y + 50, true,
						Color.WHITE, Assets.INSTANCE.getFont("slkscr14"));

			}

			if (selectedHand == HAND_LEFT)
				g.drawImage(Assets.INSTANCE.getTexture("leftHandBoxSel"), LH_BOX_X, LH_BOX_Y, LH_BOX_W, LH_BOX_H, null);
			else
				g.drawImage(Assets.INSTANCE.getTexture("leftHandBox"), LH_BOX_X, LH_BOX_Y, LH_BOX_W, LH_BOX_H, null);

			if (leftHand.getItem() != null) {
				g.drawImage(leftHand.getItem().getTexture(), LH_BOX_X + 5, LH_BOX_Y + 5, 32, 32, null);
				Text.drawString(g, Integer.toString(leftHand.getCount()), LH_BOX_X + 23, LH_BOX_Y + 50, true,
						Color.WHITE, Assets.INSTANCE.getFont("slkscr14"));
			}
		}
	}

	public void saveInventory (JSONObject obj) {
		JSONArray inventory = new JSONArray();
		for (int i = 0; i < slots.length; i++) {
			JSONObject slot = new JSONObject();
			slot.put("index", i);
			if (slots[i].getItem() != null) {
				slot.put("id", slots[i].getItem().getId());
				slot.put("count", slots[i].getCount());
			} else {
				slot.put("id", -1);
				slot.put("count", 0);
			}
			inventory.put(slot);
		}

		JSONObject leftHand = new JSONObject();
		if (this.leftHand.getItem() == null) {
			leftHand.put("id", -1);
			leftHand.put("count", 0);
		} else {
			leftHand.put("id", this.leftHand.getItem().getId());
			leftHand.put("count", this.leftHand.getCount());
		}
		obj.put("leftHand", leftHand);


		JSONObject rightHand = new JSONObject();
		if (this.rightHand.getItem() == null) {
			rightHand.put("id", -1);
			rightHand.put("count", 0);
		} else {
			rightHand.put("id", this.rightHand.getItem().getId());
			rightHand.put("count", this.rightHand.getCount());
		}
		obj.put("rightHand", rightHand);

		obj.put("inventory", inventory);
	}

	public void loadFromFile (JSONObject obj) {
		System.out.println("Loading inventory");

		JSONObject left = obj.getJSONObject("leftHand");
		System.out.println("Left ID: " + left.getInt("id"));
		if (left.getInt("id") != -1) {
			this.leftHand.setItem(Item.items[left.getInt("id")].createNew(0, 0).setPickedUp(true));
			this.leftHand.setCount(left.getInt("count"));
		}

		JSONObject right = obj.getJSONObject("rightHand");
		if (right.getInt("id") != -1) {
			this.rightHand.setItem(Item.items[right.getInt("id")].createNew(0, 0).setPickedUp(true));
			this.rightHand.setCount(right.getInt("count"));
		}

		JSONArray inv = obj.getJSONArray("inventory");
		for (int i = 0; i < inv.length(); i++) {
			JSONObject slot = inv.getJSONObject(i);
			if (slot.getInt("id") == -1)
				continue;

			slots[i].setItem(Item.items[slot.getInt("id")]);
			slots[i].setCount(slot.getInt("count"));
		}
	}

	public void setRightHand (InventorySlot rightHand) {
		this.rightHand = rightHand;
	}

	public void setLeftHand (InventorySlot leftHand) {
		this.leftHand = leftHand;
	}

	public boolean pickUpItem (Item item) {
		if (getSelectedSlot().getItem() == null) {
			getSelectedSlot().setItem(item);
			getSelectedSlot().setCount(1);
			return true;
		} else if (getSelectedSlot().getItem().getId() == item.getId()) {
			getSelectedSlot().setCount(getSelectedSlot().getCount() + 1);

			return true;
		}

		return false;
	}

}
