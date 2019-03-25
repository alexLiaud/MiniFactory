package com.alex;

import com.alex.shapes.Square;

import java.io.*;

public class Chunck {
    private Square[][] map;

    public Chunck() {
        map = new Square[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                map[i][j] = new Square(i * 10, j * 10);
            }
        }
    }

    public void soutChunck() {
        for (Square[] tab : map) {
            for (Square i : tab) {
                System.out.print("(" + i.getPosX() + ":" + i.getPosY() + ",");
            }
            System.out.println();
        }
    }

    public void save() {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("./data/save.dat");
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
        for (Square[] ligne : map) {
            for (Square square : ligne) {
                square.print();
            }
        }
    }

    public void load() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fichier = new FileInputStream("data/save.dat");
            ois = new ObjectInputStream(fichier);
            map = (Square[][]) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
