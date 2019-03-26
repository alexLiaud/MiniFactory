package com.alex;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {
    private static int nbCol = 6;
    private static int nbLig = 4;

    private int playerX = 0;
    private int playerY = 0;
    private ArrayList<Chunck> loadedChunck;

    public World() {
        float[] color = {0f, 0.4f, 0.1f};
        loadedChunck = new ArrayList<>();
    }

    public void render(int time) {
        loadChunck();
        for (Chunck c : loadedChunck) {
            c.render(time);
        }
    }

    public void loadChunck() {
        loadedChunck = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                loadedChunck.add(loadOneChunck(playerX + i, playerY + j));
            }
        }
    }

    public Chunck loadOneChunck(int x, int y) {
        Chunck res = new Chunck(x, y);
        res.load();
        return res;
    }

    public void load() {
        for (Chunck c : loadedChunck) {
            c.load();
        }
    }

    public void save() {
        for (Chunck c : loadedChunck) {
            c.save();
        }
    }
}
