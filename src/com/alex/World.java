package com.alex;

import com.alex.game.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {
    private static int nbCol = 6;
    private static int nbLig = 4;
    private static int viewRender = 1;

    private int charge = 0;
    private int playerX = 0;
    private int playerY = 0;
    private ArrayList<Chunck> loadedChunck;

    public World() {
        float[] color = {0f, 0.4f, 0.1f};
        loadedChunck = new ArrayList<>();
    }

    public void render(int time, Player p) {
        loadChunck(p);
        for (Chunck c : loadedChunck) {
            c.render(time, p);
        }
    }

    public void loadChunck(Player p) {
        ArrayList<Chunck> oldLoadChunck = loadedChunck;
        loadedChunck = new ArrayList<>();
        for (int i = p.getPosXChunck() - viewRender; i <= p.getPosXChunck() + viewRender; i++) {
            for (int j = p.getPosYChunck() - viewRender; j <= p.getPosYChunck() + viewRender; j++) {
                boolean load = true;
                for (Chunck c : oldLoadChunck) {
                    System.out.println(c + "!=" + new Chunck(playerY + i, playerY + j));
                    if (c.equals(new Chunck(playerY + i, playerY + j))) {
                        load = false;
                        loadedChunck.add(c);
                    }
                }
                if (load) {
                    loadedChunck.add(loadOneChunck(playerX + i, playerY + j));
                }
            }
        }
    }

    public Chunck loadOneChunck(int x, int y) {
        System.out.println(charge++);
        Chunck res = new Chunck(x, y);
        res.load();
        return res;
    }

    public void update() {
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
