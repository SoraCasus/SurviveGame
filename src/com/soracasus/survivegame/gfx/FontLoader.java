package com.soracasus.survivegame.gfx;

import com.soracasus.survivegame.utils.SCFile;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader {
	
	public static Font loadFont(SCFile file, float size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, file.getStream()).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
