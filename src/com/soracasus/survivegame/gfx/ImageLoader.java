package com.soracasus.survivegame.gfx;

import com.soracasus.survivegame.utils.SCFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(SCFile file){
		try {
			return ImageIO.read(file.getStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
