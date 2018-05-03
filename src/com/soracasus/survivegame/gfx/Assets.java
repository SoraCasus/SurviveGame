package com.soracasus.survivegame.gfx;

import com.soracasus.survivegame.utils.SCFile;
import com.soracasus.survivegame.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Assets {

    public static final Assets INSTANCE = new Assets();
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private BufferedImage nullTexture;

    private static final int
            DOWN = 0,
            LEFT = 1,
            RIGHT = 2,
            UP = 3;

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
        Font font14 = FontLoader.loadFont(new SCFile("fonts/slkscr.ttf"), 14);
        fonts.put("slkscr14", font14);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/sheet.png")));

        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/spriteSheet.png")));

        BufferedImage inventoryScreen = ImageLoader.loadImage(new SCFile("textures/inventoryScreen.png"));
        textures.put("inventoryScreen", inventoryScreen);

        BufferedImage wood = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        textures.put("wood", wood);

        BufferedImage mainMenuBG = ImageLoader.loadImage(new SCFile("textures/panel_beige.png"));
        textures.put("mainMenuBG", mainMenuBG);


        BufferedImage[] btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(WIDTH * 6, HEIGHT * 4, WIDTH * 2, HEIGHT);
        btn_start[1] = sheet.crop(WIDTH * 6, HEIGHT * 5, WIDTH * 2, HEIGHT);
        buttons.put("start", btn_start);

        BufferedImage[][] playerAnim = loadAnimation(new SCFile("textures/player.png"));
        animations.put("player_down", playerAnim[DOWN]);
        animations.put("player_up", playerAnim[UP]);
        animations.put("player_left", playerAnim[LEFT]);
        animations.put("player_right", playerAnim[RIGHT]);


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

        BufferedImage brownButton = ImageLoader.loadImage(new SCFile("textures/buttonLong_brown.png"));
        textures.put("brownButton", brownButton);

        BufferedImage rightHandBox = ImageLoader.loadImage(new SCFile("textures/RightHandBox.png"));
        textures.put("rightHandBox", rightHandBox);

        BufferedImage rightHandBoxSel = ImageLoader.loadImage(new SCFile("textures/RightHandBoxSel.png"));
        textures.put("rightHandBoxSel", rightHandBoxSel);

        BufferedImage leftHandBox = ImageLoader.loadImage(new SCFile("textures/LeftHandBox.png"));
        textures.put("leftHandBox", leftHandBox);

        BufferedImage leftHandBoxSel = ImageLoader.loadImage(new SCFile("textures/LeftHandBoxSel.png"));
        textures.put("leftHandBoxSel", leftHandBoxSel);

        SpriteSheet strawberrySheet = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/strawberryCrop.png")));
        BufferedImage[] strawberryCrop = new BufferedImage[4];
        for(int i = 0; i < 4; i++) {
            strawberryCrop[i] = strawberrySheet.crop(i * 32, 0, 32, 32);
        }

        animations.put("strawberryCrop", strawberryCrop);

        SpriteSheet fruits = new SpriteSheet(ImageLoader.loadImage(new SCFile("textures/Fruit.png")));
        textures.put("redFruit", fruits.crop(0, 0, 16, 16));

        BufferedImage seeds = ImageLoader.loadImage(new SCFile("textures/seeds.png"));
        textures.put("seeds", seeds);

        initNullTexture();

        Utils.initTiles(new SCFile("worlds/SpriteSheet.json"));
    }

    public BufferedImage[][] loadAnimation(SCFile file) {
        BufferedImage texture = ImageLoader.loadImage(file);
        SpriteSheet sheet = new SpriteSheet(texture);

        assert (texture != null);

        int width = texture.getWidth() / WIDTH;

        BufferedImage[][] res = new BufferedImage[4][width];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < width; j++) {
                res[i][j] = sheet.crop(WIDTH * j, HEIGHT * i, WIDTH, HEIGHT);
            }
        }

        return res;
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
