package com.soracasus.survivegame.gfx;

import com.soracasus.survivegame.utils.SCFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Assets {

    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    public static final Assets INSTANCE = new Assets();

    private BufferedImage nullTexture;

    private Map<String, BufferedImage> textures;
    private Map<String, BufferedImage[]> animations;
    private Map<String, BufferedImage[]> buttons;
    private Map<String, Font> fonts;

    private Assets() {
        this.textures = new HashMap<>();
        this.animations = new HashMap<>();
        this.buttons = new HashMap<>();
        this.fonts = new HashMap<>();

        init();
    }

    private void init() {

        Font font28 = FontLoader.loadFont(new SCFile("fonts/slkscr.ttf"), 28);
        fonts.put("slkscr", font28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/sheet.png")));

        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/spriteSheet.png")));

        BufferedImage inventoryScreen = ImageLoader.loadImage(new SCFile("textures/inventoryScreen.png"));
        textures.put("inventoryScreen", inventoryScreen);

        BufferedImage wood = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        textures.put("wood", wood);


        BufferedImage[] btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(WIDTH * 6, HEIGHT * 4, WIDTH * 2, HEIGHT);
        btn_start[1] = sheet.crop(WIDTH * 6, HEIGHT * 5, WIDTH * 2, HEIGHT);
        buttons.put("start", btn_start);

        BufferedImage[] player_down = new BufferedImage[2];
        BufferedImage[] player_up = new BufferedImage[2];
        BufferedImage[] player_left = new BufferedImage[2];
        BufferedImage[] player_right = new BufferedImage[2];

        player_down[0] = sheet.crop(WIDTH * 4, 0, WIDTH, HEIGHT);
        player_down[1] = sheet.crop(WIDTH * 5, 0, WIDTH, HEIGHT);
        player_up[0] = sheet.crop(WIDTH * 6, 0, WIDTH, HEIGHT);
        player_up[1] = sheet.crop(WIDTH * 7, 0, WIDTH, HEIGHT);
        player_right[0] = sheet.crop(WIDTH * 4, HEIGHT, WIDTH, HEIGHT);
        player_right[1] = sheet.crop(WIDTH * 5, HEIGHT, WIDTH, HEIGHT);
        player_left[0] = sheet.crop(WIDTH * 6, HEIGHT, WIDTH, HEIGHT);
        player_left[1] = sheet.crop(WIDTH * 7, HEIGHT, WIDTH, HEIGHT);

        animations.put("player_down", player_down);
        animations.put("player_up", player_up);
        animations.put("player_left", player_left);
        animations.put("player_right", player_right);


        BufferedImage[] zombie_down = new BufferedImage[2];
        BufferedImage[] zombie_up = new BufferedImage[2];
        BufferedImage[] zombie_left = new BufferedImage[2];
        BufferedImage[] zombie_right = new BufferedImage[2];

        zombie_down[0] = sheet.crop(WIDTH * 4, HEIGHT * 2, WIDTH, HEIGHT);
        zombie_down[1] = sheet.crop(WIDTH * 5, HEIGHT * 2, WIDTH, HEIGHT);
        zombie_up[0] = sheet.crop(WIDTH * 6, HEIGHT * 2, WIDTH, HEIGHT);
        zombie_up[1] = sheet.crop(WIDTH * 7, HEIGHT * 2, WIDTH, HEIGHT);
        zombie_right[0] = sheet.crop(WIDTH * 4, HEIGHT * 3, WIDTH, HEIGHT);
        zombie_right[1] = sheet.crop(WIDTH * 5, HEIGHT * 3, WIDTH, HEIGHT);
        zombie_left[0] = sheet.crop(WIDTH * 6, HEIGHT * 3, WIDTH, HEIGHT);
        zombie_left[1] = sheet.crop(WIDTH * 7, HEIGHT * 3, WIDTH, HEIGHT);

        animations.put("zombie_down", zombie_down);
        animations.put("zombie_up", zombie_up);
        animations.put("zombie_left", zombie_left);
        animations.put("zombie_right", zombie_right);

        BufferedImage dirt = ss.crop(WIDTH * 20, 0, WIDTH, HEIGHT);
        textures.put("dirt", dirt);

        BufferedImage grass = ss.crop(WIDTH * 12, 0, WIDTH, HEIGHT);
        textures.put("grass", grass);

        BufferedImage stone = ss.crop(WIDTH * 12, HEIGHT * 10, WIDTH, HEIGHT);
        textures.put("stone", stone);

        BufferedImage tree = sheet.crop(0, 0, WIDTH, HEIGHT * 2);
        textures.put("tree", tree);

        BufferedImage rock = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
        textures.put("rock", rock);

        initNullTexture();


    }

    public BufferedImage getTexture(String key) {
        BufferedImage res = textures.get(key);
        return res == null ? nullTexture : res;
    }

    public BufferedImage[] getAnimation(String key) {
        BufferedImage[] res = animations.get(key);
        return res == null ? new BufferedImage[]{nullTexture} : res;
    }

    public BufferedImage[] getButton(String key) {
        BufferedImage[] res = buttons.get(key);
        return res == null ? new BufferedImage[]{nullTexture, nullTexture} : res;
    }

    public Font getFont(String key) {
        return fonts.get(key);
    }

    private void initNullTexture() {
        this.nullTexture = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics g = nullTexture.getGraphics();
        g.setColor(new Color(0xFF00FF));
        g.fillRect(0, 0, 32, 32);
        g.setColor(new Color(0x7F007F));
        g.fillRect(0, 0, 16, 16);
        g.fillRect(16, 16, 16, 16);
        textures.put("null", nullTexture);
    }

}
