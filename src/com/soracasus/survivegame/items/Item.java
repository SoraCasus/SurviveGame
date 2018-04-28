package com.soracasus.survivegame.items;

import com.soracasus.survivegame.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Item {

	// Handler

	public static Item[] items = new Item[256];
	public static WoodItem woodItem = new WoodItem(Handler.instance);
	public static RockItem rockItem = new RockItem(Handler.instance);

	// Class

	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;

	protected Rectangle bounds;

	protected int x, y;
	protected boolean pickedUp = false;
	protected boolean hasAction = false;

	public Item (Handler handler, BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.handler = handler;

		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

		items[id] = this;
	}

	public void tick () {
		if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = handler.getWorld().getEntityManager().getPlayer().getInventory().pickUpItem(this);
		}
	}

	public abstract void onAction ();

	@Override
	public String toString () {
		return "Item{" +
				"name: " + name +
				", id: " + id +
				", x: " + x +
				", y: " + y +
				'}';
	}

	public void render (Graphics g) {
		if (handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}

	public void render (Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}

	public abstract Item createNew (int x, int y);

	public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}

	// Getters and Setters

	public Handler getHandler () {
		return handler;
	}

	public void setHandler (Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture () {
		return texture;
	}

	public void setTexture (BufferedImage texture) {
		this.texture = texture;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getX () {
		return x;
	}

	public void setX (int x) {
		this.x = x;
	}

	public int getY () {
		return y;
	}

	public void setY (int y) {
		this.y = y;
	}

	public int getId () {
		return id;
	}

	public boolean isPickedUp () {
		return pickedUp;
	}

	public Item setPickedUp (boolean pickedUp) {
		this.pickedUp = pickedUp;
		return this;
	}

	public boolean hasAction () {
		return hasAction;
	}

}
