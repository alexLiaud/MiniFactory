package com.alex;

import com.alex.game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Component {
    private static int time = 0;
    private static String title = "MiniFactory";
    private static int widht = 1200;
    private static int height = 800;
    private boolean running = false;
    private DisplayMode mode = new DisplayMode(widht, height);
    private Game game;
    private boolean tick = false;
    private boolean render = false;

    public Component() {
        try {
            Display.setDisplayMode(mode);
            Display.setResizable(true);
            Display.setFullscreen(false);
            Display.setTitle(title);
            Display.create();
            view2d(Display.getWidth(), Display.getHeight());
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static int getWidht() {
        return widht;
    }

    public static int getHeight() {
        return height;
    }

    public static void main(String[] args) {
        Component main = new Component();
        main.start();
    }

    public void render() {
        glViewport(0, 0, widht, height);
        view2d(Display.getWidth(), Display.getHeight());
        glClear(GL_COLOR_BUFFER_BIT);
        game.render(time);
    }

    public void tick() {
        game.update();
        time++;
    }

    public void start() {
        running = true;
        game = new Game();
        loop();
    }

    public void stop() {
        running = false;
    }

    public void loop() {
        long timer = System.currentTimeMillis();
        int ticks = 0;
        int frames = 0;

        long before = System.nanoTime();
        double elapsed = 0;
        double nanoSeconds = 1000000000.0 / 60.0;

        while (running) {
            tick = false;
            render = false;

            long now = System.nanoTime();

            if (Display.isCloseRequested()) {
                stop();
            }

            widht = Display.getWidth();
            height = Display.getHeight();

            elapsed = now - before;

            if (elapsed > nanoSeconds) {
                before += nanoSeconds;
                tick = true;
                ticks++;
            } else {
                render = true;
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Display.setTitle(title + " | Ticks: " + ticks + "  FPS:" + frames);
                ticks = 0;
                frames = 0;
            }

            Display.update();

            if (tick) tick();
            if (render) render();
        }
        exit();
    }

    private void view2d(int w, int h) {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, w, h, 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glEnable(GL_TEXTURE_2D);
    }

    public void exit() {
        game.stop();
        Display.destroy();
        System.exit(0);
    }
}
