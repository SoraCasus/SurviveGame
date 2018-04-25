package com.soracasus.survivegame.entities.creatures;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.entities.Entity;
import com.soracasus.survivegame.gfx.Animation;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.inventory.HandInventory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

	public static final int
			UP = 0,
			DOWN = 1,
			LEFT = 2,
			RIGHT = 3;

	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	private HandInventory inventory;

	private int currentDirection;

	public Player (Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;

		//Animatons
		animDown = new Animation(250, Assets.INSTANCE.getAnimation("player_down"));
		animUp = new Animation(250, Assets.INSTANCE.getAnimation("player_up"));
		animLeft = new Animation(250, Assets.INSTANCE.getAnimation("player_left"));
		animRight = new Animation(250, Assets.INSTANCE.getAnimation("player_right"));

		inventory = new HandInventory(handler);

		this.currentDirection = DOWN;
	}

	@Override
	public void tick () {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Action
		if (inventory.getSelectedSlot().getItem() != null) {
			if (inventory.getSelectedSlot().getItem().hasAction() && !inventory.isActive()) {
				inventory.getSelectedSlot().getItem().onAction();
			} else {
				checkAttacks();
			}
		} else {
			checkAttacks();
		}
		// Inventory
		inventory.tick();
	}

	private void checkAttacks () {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		if (inventory.isActive())
			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().keyDown(KeyEvent.VK_F)) {
			switch (currentDirection) {
				case UP: {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y - arSize;
				}
				break;

				case DOWN: {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y + cb.height;
				}
				break;

				case LEFT: {

					ar.x = cb.x - arSize;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
				}
				break;

				case RIGHT: {
					ar.x = cb.x + cb.width;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
				}
				break;
			}
		}


		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}

	}

	@Override
	public void die () {
		System.out.println("You lose");
	}

	private void getInput () {
		xMove = 0;
		yMove = 0;

		if (inventory.isActive())
			return;

		if (handler.getKeyManager().up) {
			yMove = -speed;
			currentDirection = UP;
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
			currentDirection = DOWN;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
			currentDirection = LEFT;
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
			currentDirection = RIGHT;
		}
	}

	@Override
	public void render (Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void postRender (Graphics g) {
		inventory.render(g);
	}

	private BufferedImage getCurrentAnimationFrame () {
		boolean moving = xMove != 0 || yMove != 0;

		switch (currentDirection) {
			case UP: {
				if (moving)
					return animUp.getCurrentFrame();
				else
					return animUp.getStillFrame();
			}

			case DOWN: {
				if (moving)
					return animDown.getCurrentFrame();
				else
					return animDown.getStillFrame();
			}

			case LEFT: {
				if (moving)
					return animLeft.getCurrentFrame();
				else
					return animLeft.getStillFrame();
			}

			case RIGHT: {
				if (moving)
					return animRight.getCurrentFrame();
				else
					return animRight.getStillFrame();
			}
		}

		return null;
	}

	public HandInventory getInventory () {
		return inventory;
	}

	public void setInventory (HandInventory inventory) {
		this.inventory = inventory;
	}


	public int getCurrentDirection () {
		return currentDirection;
	}
}
