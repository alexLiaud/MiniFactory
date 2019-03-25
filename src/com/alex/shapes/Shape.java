package com.alex.shapes;

public abstract class Shape {
    int glType;
    int posX = 0;
    int posY = 0;

    public Shape(int type, int x, int y) {
        glType = type;
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void print() {
    }
}
