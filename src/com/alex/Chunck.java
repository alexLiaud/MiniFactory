package com.alex;

import com.alex.game.Game;
import com.alex.game.Player;
import com.alex.graphics.Dirt;
import com.alex.graphics.Sand;
import com.alex.graphics.Tile;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Chunck {
    private int tileSize = 60;
    private Tile[][] solidTiles;
    private int locX = 0;
    private int locY = 0;
    private String loc;

    public Chunck(int x, int y) {
        locY = y;
        locX = x;
        solidTiles = new Tile[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                solidTiles[i][j] = new Dirt(i * tileSize, j * tileSize, tileSize);

            }
        }

        solidTiles[3][5] = new Sand(3 * tileSize, 5 * tileSize, tileSize);


        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                boolean vu = false, vd = false, vl = false, vr = false;
                boolean vur = false, vul = false, vdl = false, vdr = false;

                if (!solidTiles[i][j].isBase()) {
                    if (x + 1 < 16 && solidTiles[x + 1][y].isBase()) {
                        vr = true;
                    }

                    if (x - 1 >= 0 && solidTiles[x - 1][y].isBase()) {
                        vl = true;
                    }

                    if (y + 1 < 16 && solidTiles[x][y + 1].isBase()) {
                        vu = true;
                    }

                    if (y - 1 >= 0 && solidTiles[x][y - 1].isBase()) {
                        vd = true;
                    }

                    if (x + 1 < 16 && y + 1 < 16 && solidTiles[x + 1][y + 1].isBase()) {
                        vur = true;
                    }

                    if (x - 1 >= 0 && y + 1 < 16 && solidTiles[x - 1][y + 1].isBase()) {
                        vul = true;
                    }

                    if (x + 1 < 16 && y - 1 >= 0 && solidTiles[x + 1][y + 1].isBase()) {
                        vdr = true;
                    }

                    if (x - 1 >= 0 && y - 1 >= 0 && solidTiles[x - 1][y - 1].isBase()) {
                        vdl = true;
                    }

                    solidTiles[i][j].setTiles(vu, vd, vl, vr, vur, vul, vdl, vdr);
                }
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
        for (Tile[] tab : solidTiles) {
            for (Tile i : tab) {
                System.out.print("(" + i.getPosX() + ":" + i.getPosY() + ",");
            }
            System.out.println();
        }
    }

    public void save() {
//        ObjectOutputStream oos = null;
//        try {
//            float[] c = {0.3f, 0f, 0f, 0};
//            final FileOutputStream fichier = new FileOutputStream(loc);
//            oos = new ObjectOutputStream(fichier);
//            oos.writeObject(this.solidTiles);
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (oos != null) {
//                oos.flush();
//                oos.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void render(int time, Player player, Game game) {
        int middleY = Display.getHeight() / 2;
        int middleX = Display.getWidth() / 2;
        for (Tile[] ligne : solidTiles) {
            for (Tile tile : ligne) {
                tile.printTexture(tile.getPosX() + middleX - 8 * tile.getSize() + locX * tile.getSize() * solidTiles.length, middleY - 8 * tile.getSize() + tile.getPosY() + locY * tile.getSize() * solidTiles[0].length, game);
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
                solidTiles = (Tile[][]) ois.readObject();
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
