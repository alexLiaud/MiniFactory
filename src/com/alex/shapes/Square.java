package com.alex.shapes;

import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class Square extends Shape {
    float[] color = {0, 0, 0, 0};
    int size = 50;

    public Square(int x, int y, float[] c, int size) {
        super(GL_QUADS, x, y);
        if (color.length == 4) {
            this.color = c;
        }
        this.size = size;
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
        return "Square{" +
                "color=" + Arrays.toString(color) +
                ", size=" + size +
                ", glType=" + glType +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
