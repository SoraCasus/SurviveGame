package com.soracasus.survivegame.items;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;

public class RedFruitItem extends Item {

    public RedFruitItem (Handler handler) {
        super(handler, Assets.INSTANCE.getTexture("redFruit"), "Red Fruit", 2);
        this.hasAction = false;
    }

    @Override
    public void onAction () {

    }

    @Override
    public Item createNew (int x, int y) {
        RedFruitItem i = new RedFruitItem(handler);
        i.setPosition(x, y);
        return i;
    }
}
