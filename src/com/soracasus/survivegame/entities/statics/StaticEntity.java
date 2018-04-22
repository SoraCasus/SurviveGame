package com.soracasus.survivegame.entities.statics;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}
