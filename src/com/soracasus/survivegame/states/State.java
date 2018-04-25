package com.soracasus.survivegame.states;

import com.soracasus.survivegame.Handler;

import java.awt.Graphics;


public abstract class State {

	private static State currentState = null;

	public State (Handler handler) {
		this.handler = handler;
	}

	public static State getState () {
		return currentState;
	}

	//CLASS

	protected Handler handler;

	public static void setState (State state) {
		currentState = state;
	}

	public abstract void tick ();

	public abstract void render (Graphics g);

	public void loadFromFile () {

	}

}
