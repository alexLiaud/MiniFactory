package com.alex.graphics;

public class Sand extends Tile {

    public Sand(int x, int y, int size) {
        super(x, y, size);
        type = "sand";
        isBase = true;
        nb = 1;
        tileSprite = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    }
}
