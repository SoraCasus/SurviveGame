package com.soracasus.survivegame.menu;

import com.soracasus.survivegame.Handler;

import java.awt.Graphics;

public abstract class Menu {

	protected Handler handler;

	protected boolean active;

	protected Menu () {
		this.active = false;
	}

	public abstract void tick ();

	public abstract void render (Graphics g);

	public boolean isActive () {
		return active;
	}
}
