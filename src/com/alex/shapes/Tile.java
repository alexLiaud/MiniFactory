package com.alex.shapes;

import java.util.Arrays;

import static com.alex.shapes.Type.GRASS;
import static org.lwjgl.opengl.GL11.*;

public class Tile extends Shape {
    private float[] color = {0, 1, 0, 0};
    private int size = 60;
    private int type = GRASS;


    public Tile(int x, int y) {
        super(GL_QUADS, x, y);
    }

    public Tile(int x, int y, float[] c, int size) {
        super(GL_QUADS, x, y);
        if (color.length == 4) {
            this.color = c;
        }
        this.size = size;
    }

    public Tile(int x, int y, float[] c) {
        super(GL_QUADS, x, y);
        if (color.length == 4) {
            this.color = c;
        }
    }

    public int getSize() {
        return size;
    }

    public void print(int x, int y) {
        glBegin(glType);
        glColor3f(color[0], color[1], color[2]);
        glVertex2f(x, y);
        glVertex2d(x, y + size);
        glVertex2d(x + size, y + size);
        glVertex2d(x + size, y);
        glEnd();
    }

    public void print() {
        print(posX, posY);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "color=" + Arrays.toString(color) +
                ", size=" + size +
                ", glType=" + glType +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    public void setColor(float a, float b, float c) {
        color[0] = a;
        color[1] = b;
        color[2] = c;
    }
}
