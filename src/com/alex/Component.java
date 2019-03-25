package com.alex;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Component {
    private static String title = "OctoSurvival";
    private static int widht = 800;
    private static int height = 400;
    private boolean running = false;
    private DisplayMode mode = new DisplayMode(widht, height);

    public Component() {
        try {
            Display.setDisplayMode(mode);
            Display.setResizable(true);
            Display.setFullscreen(false);
            Display.setTitle(title);
            Display.create();
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

    public void start() {
        running = true;
        loop();
    }

    public void stop() {
        running = false;
    }

    public void loop() {
        while (running) {
            if (Display.isCloseRequested()) {
                stop();
            }
            System.out.println("Coucou");
            Display.update();
        }
        exit();
    }

    public void exit() {
        Display.destroy();
        System.exit(0);
    }
}
