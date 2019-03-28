package com.alex.game;

import com.alex.shapes.Tile;
import org.lwjgl.opengl.Display;

public class Player {
    private int posX = 0;
    private int posy = 0;
    private int posXChunck = 0;
    private int posYChunck = 0;
    private int width = 10;
    private int height = 5;

    public Player() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosXChunck() {
        return posXChunck;
    }

    public void setPosXChunck(int posXChunck) {
        this.posXChunck = posXChunck;
    }

    public int getPosYChunck() {
        return posYChunck;
    }

    public void setPosYChunck(int posYChunck) {
        this.posYChunck = posYChunck;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void render(int time, Game game) {
        float[] couleur = {0.5f, 0.5f, 0, 0};
        Tile player = new Tile(Display.getWidth() / 2 - getWidth() / 2, Display.getHeight() / 2 - getHeight() / 2, couleur, 10);
        player.print(game);
    }
}
