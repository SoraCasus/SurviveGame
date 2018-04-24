package com.soracasus.survivegame.utils;

import com.soracasus.survivegame.gfx.ImageLoader;
import com.soracasus.survivegame.gfx.SpriteSheet;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.tiles.Tile;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class Utils {

	public static JSONObject loadJSONObject (SCFile file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = file.getReader()) {
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line).append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new JSONObject(sb.toString());

	}

	public static void initTiles (SCFile spriteSheetMap) {

		final int WIDTH = 32;
		final int HEIGHT = 32;

		JSONObject ss = loadJSONObject(spriteSheetMap);
		int count = ss.getInt("tilecount");
		Tile.initTiles(count);

		int sheetWidth = ss.getInt("imagewidth") / WIDTH;
		int sheetHeight = ss.getInt("imageheight") / HEIGHT;

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/spriteSheet.png")));

		JSONObject tileProperties = ss.getJSONObject("tileproperties");

		int index = 0;
		for (int y = 0; y < sheetHeight; y++) {
			for (int x = 0; x < sheetWidth; x++) {
				JSONObject tileData = tileProperties.getJSONObject(Integer.toString(index));
				BufferedImage texture = sheet.crop(WIDTH * x, WIDTH * y, WIDTH, HEIGHT);
				boolean solid = tileData.getBoolean("solid");

//				try {
//					ImageIO.write(texture, "png", new FileOutputStream("res/textures/dump/" + index + ".png"));
//				} catch (IOException e) {
//					e.printStackTrace();
//					System.exit(-1);
//				}

				// Creation of the tile adds the tile to the tile array
				// indexed by the ID number
				Tile tile = new Tile(texture, index) {
					@Override
					public boolean isSolid () {
						return solid;
					}
				};

				Tile.tiles[index] = tile;

				index++;
			}
		}


	}

}
