package com.soracasus.survivegame.entities.crops;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.Entity;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.items.Item;
import com.soracasus.survivegame.json.JSONObject;

import java.awt.Graphics;
import java.util.Random;

public class RedFruitCrop extends CropEntity {

    private static final int ID = 3;
    private static final int NUM_STAGES = 3;
    private static final int GROWTH_SPEED = 10;
    private static final Random random = new Random();

    public RedFruitCrop(Handler handler, float x, float y) {
        super(handler, Assets.INSTANCE.getAnimation("strawberryCrop"), x, y, NUM_STAGES, GROWTH_SPEED, ID);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentStage(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        if (currentStage == numStages) {
            for (int i = 0; i < random.nextInt(5) + 1; i++)
                handler.getWorld().getItemManager().addItem(Item.redFruitItem.createNew((int) x, (int) y));
            for (int i = 0; i < random.nextInt(5) + 1; i++)
                handler.getWorld().getItemManager().addItem(Item.redFruitSeedsItem.createNew((int) x, (int) y));
        }
    }

    @Override
    public Entity createNew() {
        return new RedFruitCrop(handler, 0, 0);
    }

    @Override
    public void save(JSONObject obj) {
        super.save(obj);
    }
}
