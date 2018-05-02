package com.soracasus.survivegame.entities;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.utils.ISerializable;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity implements ISerializable {

	public static Entity[] entities = new Entity[256];

	public static final int DEFAULT_HEALTH = 3;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int health;
	protected boolean active = true;
	protected Rectangle bounds;
	protected final int id;

	public Entity (Handler handler, float x, float y, int width, int height, int id) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		entities[id] = this;

		health = DEFAULT_HEALTH;

		bounds = new Rectangle(0, 0, width, height);
	}

	@Override
	public void save (JSONObject obj) {
		obj.put("x", x);
		obj.put("y", y);
		obj.put("width", width);
		obj.put("height", height);
		obj.put("health", health);
		obj.put("active", active);
		obj.put("id", id);
	}

	public abstract void tick ();

	public abstract void render (Graphics g);

	public abstract void die ();

	public void hurt (int amt) {
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public boolean checkEntityCollisions (float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public Rectangle getCollisionBounds (float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public Rectangle getBounds () {
		return this.bounds;
	}

	public abstract Entity createNew ();

	public float getCenterX () {
		return x + (width / 2);
	}

	public float getCenterY () {
		return y + (height / 2);
	}

	public float getX () {
		return x;
	}

	public void setX (float x) {
		this.x = x;
	}

	public float getY () {
		return y;
	}

	public void setY (float y) {
		this.y = y;
	}

	public int getWidth () {
		return width;
	}

	public void setWidth (int width) {
		this.width = width;
	}

	public void setBounds (Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getHeight () {
		return height;
	}

	public void setHeight (int height) {
		this.height = height;
	}

	public int getHealth () {
		return health;
	}

	public void setHealth (int health) {
		this.health = health;
	}

	public boolean isActive () {
		return active;
	}

	public void setActive (boolean active) {
		this.active = active;
	}

	public int getID () {
		return id;
	}
}
