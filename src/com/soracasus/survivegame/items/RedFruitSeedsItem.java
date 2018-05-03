package com.soracasus.survivegame.items;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.creatures.Player;
import com.soracasus.survivegame.entities.crops.RedFruitCrop;
import com.soracasus.survivegame.gfx.Assets;

import java.awt.event.KeyEvent;

public class RedFruitSeedsItem extends Item {

    private static final int DISTANCE = 40;

    public RedFruitSeedsItem(Handler handler) {
        super(handler, Assets.INSTANCE.getTexture("seeds"), "Red Fruit Seeds", 3);
        this.hasAction = true;
    }

    @Override
    public void onAction() {

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
            // Place item in world
            Player player = handler.getWorld().getEntityManager().getPlayer();
            int dir = player.getCurrentDirection();
            int dx = 0;
            int dy = 0;

            if (dir == Player.UP)
                dy = -DISTANCE;
            if (dir == Player.DOWN)
                dy = DISTANCE;
            if (dir == Player.LEFT)
                dx = -DISTANCE;
            if (dir == Player.RIGHT)
                dx = DISTANCE;

            RedFruitCrop rfc = new RedFruitCrop(handler, player.getX() + dx, player.getY() + dy);
            rfc.setActive(true);
            handler.getWorld().getEntityManager().addEntity(rfc);

            // Remove decrement item count
            int count = player.getInventory().getSelectedSlot().getCount();
            player.getInventory().getSelectedSlot().setCount(count - 1);

            if (player.getInventory().getSelectedSlot().getCount() <= 0)
                player.getInventory().getSelectedSlot().setItem(null);
        }
    }

        @Override
        public Item createNew ( int x, int y){
            RedFruitSeedsItem i = new RedFruitSeedsItem(handler);
            i.setPosition(x, y);
            return i;
        }


    }
