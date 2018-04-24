package com.soracasus.survivegame.menu;

import com.soracasus.survivegame.Handler;

import java.awt.Graphics;

public abstract class Menu {

	protected Handler handler;

	public abstract void tick ();

	public abstract void render (Graphics g);

}
