package com.alex;

import com.alex.shapes.Tile;

import java.io.*;

public class Chunck {
    private Tile[][] map;
    private int locX = 0;
    private int locY = 0;
    private String loc = "./data/save_" + locX + "_" + locY + ".dat";

    public Chunck(int x, int y) {
        locY = y;
        locX = x;
        map = new Tile[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                map[i][j] = new Tile(i * 10, j * 10);
            }
        }
        loc = "./data/save_" + locX + "_" + locY + ".dat";
    }

    public void soutChunck() {
        for (Tile[] tab : map) {
            for (Tile i : tab) {
                System.out.print("(" + i.getPosX() + ":" + i.getPosY() + ",");
            }
            System.out.println();
        }
    }

    public void save() {
        System.out.println(loc);
        ObjectOutputStream oos = null;
        try {
            float[] c = {0.3f, 0f, 0f, 0};
            map[5][5] = new Tile(map[5][5].getPosX(), map[5][5].getPosY(), c, 10);
            final FileOutputStream fichier = new FileOutputStream(loc);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this.map);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (oos != null) {
                oos.flush();
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(int time) {
        for (Tile[] ligne : map) {
            for (Tile tile : ligne) {
                tile.print(tile.getPosX() + locX * tile.getSize() * map.length, tile.getPosY() + locY * tile.getSize() * map[0].length);
            }
        }
    }

    public void load() {
        File file = new File(loc);
        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                FileInputStream fichier = new FileInputStream(loc);
                ois = new ObjectInputStream(fichier);
                map = (Tile[][]) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            save();
        }
    }
}
