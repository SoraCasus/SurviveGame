package com.soracasus.survivegame.states;

import java.awt.Graphics;

import com.soracasus.survivegame.Handler;
import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.ui.ClickListener;
import com.soracasus.survivegame.ui.UIImageButton;
import com.soracasus.survivegame.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.INSTANCE.getButton("start"), () -> {
			handler.getMouseManager().setUIManager(null);
			setState(handler.getGame().gameState);
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
