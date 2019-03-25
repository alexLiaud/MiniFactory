package com.alex;

import com.alex.shapes.Square;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

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
        int x = 50, y = 50;

        float[] color = {0f, 0.4f, 0.1f};
        Square square = new Square(30, 20, color, 50);
        Square square1 = new Square(30, 50, color, 70);
        square.print();
        square1.print();
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
            widht = Display.getWidth();
            height = Display.getHeight();
            Display.update();
            render();
        }
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
