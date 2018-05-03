package com.soracasus.survivegame.entities.crops;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.statics.StaticEntity;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.image.BufferedImage;

public abstract class CropEntity extends StaticEntity {

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    protected int numStages;
    protected int growthSpeed;
    protected int currentStage;
    protected long startTime;
    protected BufferedImage[] stages;

    public CropEntity(Handler handler, BufferedImage[] stages, float x, float y, int numStages, int growthSpeed, int id) {
        super(handler, x, y, WIDTH, HEIGHT, id);
        this.numStages = numStages;
        this.growthSpeed = growthSpeed;
        this.currentStage = 0;
        this.startTime = System.currentTimeMillis();
        this.stages = stages;
    }

    public BufferedImage getCurrentStage() {
        return stages[currentStage];
    }

    @Override
    public void tick() {
        long delta = System.currentTimeMillis() - startTime;
        if ((delta / 1000) >= growthSpeed) {
            startTime += delta;
            if (currentStage < numStages)
                currentStage++;
        }
    }

    @Override
    public void save(JSONObject obj) {
        super.save(obj);
        obj.put("numStages", numStages);
        obj.put("growthSpeed", growthSpeed);
        obj.put("currentStage", currentStage);
        obj.put("startTime", startTime);
    }

}
