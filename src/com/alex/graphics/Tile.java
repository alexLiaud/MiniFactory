package com.alex.graphics;

import com.alex.Component;
import com.alex.game.Game;

import java.io.Serializable;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class Tile implements Serializable {

    protected float[] color = {0, 1, 0, 0};
    protected int size = 60;
    protected int glType;
    protected int posX = 0;
    protected int posY = 0;

    public Tile(int x, int y) {
        glType = GL_QUADS;
        posX = x;
        posY = y;
    }

    public Tile(int x, int y, float[] c, int size) {
        glType = GL_QUADS;
        posX = x;
        posY = y;
        if (color.length == 4) {
            this.color = c;
        }
        this.size = size;
    }

    public Tile(int x, int y, float[] c) {
        glType = GL_QUADS;
        posX = x;
        posY = y;
        if (color.length == 4) {
            this.color = c;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public void print(int x, int y, Game game) {
        float x0 = x + game.getxScroll();
        float y0 = y + game.getyScroll();
        float x1 = x + getSize() + game.getxScroll();
        float y1 = y + +getSize() + game.getyScroll();
        if (x1 < 0 || y1 < 0 || x0 > Component.getWidht() || y0 > Component.getHeight()) {
            return;
        }
        glBegin(glType);
        glColor3f(color[0], color[1], color[2]);
        glVertex2f(x, y);
        glVertex2f(x, y + size);
        glVertex2f(x + size, y + size);
        glVertex2f(x + size, y);
        glEnd();
    }

    public void print(Game game) {
        print(posX, posY, game);
    }

    public void printTexture(Game game) {
        printTexture(posX, posY, game);
    }

    public void printTexture(int x, int y, Game game) {
        Texture.getTiles().bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(1, 0);
        glVertex2f(x, y + size);
        glTexCoord2f(1, 1);
        glVertex2f(x + size, y + size);
        glTexCoord2f(0, 1);
        glVertex2f(x + size, y);
        glEnd();
        Texture.getTiles().unbind();
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
