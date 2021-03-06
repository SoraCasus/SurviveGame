package com.soracasus.survivegame;

import com.soracasus.survivegame.display.Display;
import com.soracasus.survivegame.gfx.GameCamera;
import com.soracasus.survivegame.input.KeyManager;
import com.soracasus.survivegame.input.MouseManager;
import com.soracasus.survivegame.json.JSONObject;
import com.soracasus.survivegame.states.GameState;
import com.soracasus.survivegame.states.MenuState;
import com.soracasus.survivegame.states.State;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Handler.initialize(this);
        handler = Handler.instance;
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();

        if (State.getState() != null)
            State.getState().tick();

        if (keyManager.keyJustPressed(KeyEvent.VK_P))
            saveGame();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        if (State.getState() != null)
            State.getState().render(g);

        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                // System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public void saveGame() {
        JSONObject save = new JSONObject();
        handler.getWorld().getEntityManager().getPlayer().getInventory().saveInventory(save);
        handler.getWorld().getItemManager().saveItems(save);
        handler.getWorld().getEntityManager().saveEntities(save);

        File file = new File("res/save/save1.json");
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(save.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}











