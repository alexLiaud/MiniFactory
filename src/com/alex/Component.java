package com.alex;

import com.alex.shapes.Square;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Component {
    private static int time = 0;
    private static String title = "OctoSurvival";
    private static int widht = 1200;
    private static int height = 800;
    private boolean running = false;
    private DisplayMode mode = new DisplayMode(widht, height);
    private World world;

    public Component() {
        try {
            Display.setDisplayMode(mode);
            Display.setResizable(false);
            Display.setFullscreen(false);
            Display.setTitle(title);
            Display.create();
            initGL();
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
        glClear(GL_COLOR_BUFFER_BIT);
        float[] couleur = {0.5f, 0.5f, 0, 0};
        Square player = new Square(widht / 2 - 5, height / 2 - 5, couleur, 5);
        world.render(time);
        player.print();
    }

    public void tick() {
        time++;
    }

    public void start() {
        running = true;
        world = new World();
        world.load();
        loop();
    }

    public void stop() {
        running = false;
        world.save();
    }

    public void loop() {
        while (running) {
            if (Display.isCloseRequested()) {
                stop();
            }
            widht = Display.getWidth();
            height = Display.getHeight();
            Display.update();
            tick();
            render();
        }
        world.save();
        exit();
    }

    private void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, widht, height, 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void exit() {
        Display.destroy();
        System.exit(0);
    }
}
