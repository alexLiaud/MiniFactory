package com.alex;

import com.alex.game.Game;
import com.alex.game.Player;
import com.alex.graphics.Grass;
import com.alex.graphics.Tile;
import org.lwjgl.opengl.Display;

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
                map[i][j] = new Grass(i * 60, j * 60);
            }
        }
        loc = "./data/save_" + locX + "_" + locY + ".dat";
    }

    @Override
    public String toString() {
        return "Chunck{" +
                "loc='" + loc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chunck)) return false;
        Chunck chunck = (Chunck) o;
        return loc.equals(chunck.loc);
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
        ObjectOutputStream oos = null;
        try {
            float[] c = {0.3f, 0f, 0f, 0};
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

    public void render(int time, Player player, Game game) {
        int middleY = Display.getHeight() / 2;
        int middleX = Display.getWidth() / 2;
        for (Tile[] ligne : map) {
            for (Tile tile : ligne) {
                tile.printTexture(tile.getPosX() + middleX - 8 * tile.getSize() + locX * tile.getSize() * map.length - player.getWidth() / 2, middleY - player.getHeight() / 2 - 8 * tile.getSize() + tile.getPosY() + locY * tile.getSize() * map[0].length, game);
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
