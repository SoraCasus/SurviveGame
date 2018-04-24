package com.soracasus.survivegame.ui;

import com.soracasus.survivegame.gfx.Assets;
import com.soracasus.survivegame.gfx.Text;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UITextButton extends UIObject {

	private BufferedImage image;
	private ClickListener clicker;
	private String text;
	private Color color1;
	private Color color2;

	public UITextButton (float x, float y, int width, int height, BufferedImage image, String text, Color color1, Color color2, ClickListener clicker) {
		super(x - (width / 2), y - (height / 2), width, height);
		this.image = image;
		this.clicker = clicker;
		this.text = text;
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public void tick () {
	}

	@Override
	public void render (Graphics g) {
		int xOff = (int) (x + (width / 2));
		int yOff = (int) (y + (height / 2));
		if (hovering) {
			g.drawImage(image, (int) x, (int) y, width, height, null);
			Text.drawString(g, text, xOff, yOff, true, color2, Assets.INSTANCE.getFont("slkscr"));
		} else {
			g.drawImage(image, (int) x, (int) y, width, height, null);
			Text.drawString(g, text, xOff, yOff, true, color1, Assets.INSTANCE.getFont("slkscr"));
		}
	}

	@Override
	public void onClick () {
		clicker.onClick();
	}


}
