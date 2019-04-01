package com.alex.graphics;

import com.alex.Component;
import com.alex.game.Game;
import com.alex.textureMap;

import java.io.Serializable;

import static org.lwjgl.opengl.GL11.*;

public class Tile implements Serializable {

    protected int size = 60;
    protected int glType;
    protected int posX = 0;
    protected int posY = 0;
    protected int posXSheet = 0;
    protected int posYSheet = 0;
    protected float nb = 1f;
    protected String type = "void";

    public Tile(int x, int y) {
        glType = GL_QUADS;
        posX = x;
        posY = y;
    }

    public Tile(int x, int y, int size) {
        glType = GL_QUADS;
        posX = x;
        posY = y;
        this.size = size;
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
        if (!textureMap.getInstance().isLoaded(type)) {
            textureMap.getInstance().loadTextureInMap(type);
        }

        textureMap.getTiles(type).bind();
        glBegin(GL_QUADS);
        glTexCoord2f(posXSheet / nb, posYSheet / nb);
        glVertex2f(x, y);
        glTexCoord2f((1 + posXSheet) / nb, posYSheet / nb);
        glVertex2f(x, y + size);
        glTexCoord2f((1 + posXSheet) / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y + size);
        glTexCoord2f(posXSheet / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y);
        glEnd();
        textureMap.getTiles(type).unbind();
    }

    @Override
    public String toString() {
        return "Tile{" +
                ", size=" + size +
                ", glType=" + glType +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
