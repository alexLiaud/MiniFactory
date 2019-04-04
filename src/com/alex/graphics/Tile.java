package com.alex.graphics;

import com.alex.Component;
import com.alex.TextureMap;
import com.alex.game.Game;

import java.io.Serializable;

import static org.lwjgl.opengl.GL11.*;

public class Tile implements Serializable {

    public int[] tileSprite = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    protected int size = 100;
    protected int glType;
    protected int posX = 0;
    protected int posY = 0;
    protected int posXSheet = 0;
    protected int posYSheet = 0;
    protected float nb = 3f;
    protected String type = "void";

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
        if (!TextureMap.getInstance().isLoaded(type)) {
            TextureMap.getInstance().loadTextureInMap(type);
        }

        int size;
        size = this.size / 2;

        posXSheet += tileSprite[0];
        posYSheet += tileSprite[1];

        TextureMap.getTiles(type).bind();
        glBegin(GL_QUADS);
        glTexCoord2f(posXSheet / nb, posYSheet / nb);
        glVertex2f(x, y);
        glTexCoord2f((1 + posXSheet) / nb, posYSheet / nb);
        glVertex2f(x, y + size);
        glTexCoord2f((1 + posXSheet) / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y + size);
        glTexCoord2f(posXSheet / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y);

        posXSheet -= +tileSprite[0];
        posYSheet -= +tileSprite[1];
        posXSheet += tileSprite[2];
        posYSheet += tileSprite[3];

        x += size;

        glTexCoord2f(posXSheet / nb, posYSheet / nb);
        glVertex2f(x, y);
        glTexCoord2f((1 + posXSheet) / nb, posYSheet / nb);
        glVertex2f(x, y + size);
        glTexCoord2f((1 + posXSheet) / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y + size);
        glTexCoord2f(posXSheet / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y);

        posXSheet -= +tileSprite[2];
        posYSheet -= +tileSprite[3];
        posXSheet += tileSprite[4];
        posYSheet += tileSprite[5];

        y += size;

        glTexCoord2f(posXSheet / nb, posYSheet / nb);
        glVertex2f(x, y);
        glTexCoord2f((1 + posXSheet) / nb, posYSheet / nb);
        glVertex2f(x, y + size);
        glTexCoord2f((1 + posXSheet) / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y + size);
        glTexCoord2f(posXSheet / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y);

        posXSheet -= +tileSprite[4];
        posYSheet -= +tileSprite[5];
        posXSheet += tileSprite[6];
        posYSheet += tileSprite[7];

        x -= size;

        glTexCoord2f(posXSheet / nb, posYSheet / nb);
        glVertex2f(x, y);
        glTexCoord2f((1 + posXSheet) / nb, posYSheet / nb);
        glVertex2f(x, y + size);
        glTexCoord2f((1 + posXSheet) / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y + size);
        glTexCoord2f(posXSheet / nb, (1 + posYSheet) / nb);
        glVertex2f(x + size, y);
        glEnd();
        TextureMap.getTiles(type).unbind();

        posXSheet -= +tileSprite[6];
        posYSheet -= +tileSprite[7];
    }

    public String getType() {
        return type;
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
