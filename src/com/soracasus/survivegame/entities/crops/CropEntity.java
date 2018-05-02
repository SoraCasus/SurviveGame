package com.soracasus.survivegame.entities.crops;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.statics.StaticEntity;

import java.awt.image.BufferedImage;

public abstract class CropEntity extends StaticEntity {

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	protected int numStages;
	protected int growthSpeed;
	protected int currentStage;

	public CropEntity (Handler handler, float x, float y, int numStages, int growthSpeed, int id) {
		super(handler, x, y, WIDTH, HEIGHT, id);
		this.numStages = numStages;
		this.growthSpeed = growthSpeed;
		this.currentStage = 0;
	}

	public abstract BufferedImage getCurrentStage ();

}
