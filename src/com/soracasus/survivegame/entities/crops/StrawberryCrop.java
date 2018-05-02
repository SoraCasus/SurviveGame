package com.soracasus.survivegame.entities.crops;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.Entity;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class StrawberryCrop extends CropEntity {

	private static final int ID = 3;
	private static final int NUM_STAGES = 3;
	private static final int GROWTH_SPEED = 10;

	public StrawberryCrop (Handler handler, float x, float y) {
		super(handler, x, y, NUM_STAGES, GROWTH_SPEED, ID);
	}

	@Override
	public BufferedImage getCurrentStage () {
		return null;
	}

	@Override
	public void tick () {

	}

	@Override
	public void render (Graphics g) {

	}

	@Override
	public void die () {

	}

	@Override
	public Entity createNew () {
		return null;
	}

	@Override
	public void save (JSONObject obj) {

	}
}
