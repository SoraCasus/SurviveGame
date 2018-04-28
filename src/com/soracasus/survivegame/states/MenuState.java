package com.soracasus.survivegame.states;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.ui.UIManager;
import com.soracasus.survivegame.ui.UITextButton;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState (Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UITextButton(handler.getWidth() / 2, handler.getHeight() / 2, 128, 64, Assets.INSTANCE.getTexture("brownButton"), "Start", Color.BLACK, Color.YELLOW, () -> {
			handler.getMouseManager().setUIManager(null);
			setState(handler.getGame().gameState);
		}));

		uiManager.addObject(new UITextButton(handler.getWidth() / 2, (handler.getHeight() / 2) + 50, 128, 64, Assets.INSTANCE.getTexture("brownButton"), "Load", Color.BLACK, Color.YELLOW, () -> {
			handler.getMouseManager().setUIManager(null);
			setState(handler.getGame().gameState);
			handler.getGame().gameState.loadFromFile();
		}));
	}

	@Override
	public void tick () {
		uiManager.tick();
	}

	@Override
	public void render (Graphics g) {
		g.drawImage(Assets.INSTANCE.getTexture("mainMenuBG"), 0, 0, handler.getWidth(), handler.getHeight(), null);
		uiManager.render(g);
	}

}
