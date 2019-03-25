package com.alex;

import com.alex.shapes.Shape;
import com.alex.shapes.Square;

import java.io.*;
import java.util.ArrayList;

public class World implements Serializable {
    private ArrayList<Shape> obj;
    private int[][] map = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public World() {
        obj = new ArrayList<>();
        float[] color = {0f, 0.4f, 0.1f};
        Square square = new Square(30, 20, color, 50);
        Square square1 = new Square(30, 50, color, 70);
        obj.add(square1);
        obj.add(square);
        soutMap();
        map[4][5] = 1;
        save();
        load();
        soutMap();
    }

    public void soutMap() {
        for (int[] tab : map) {
            for (int i : tab) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    public void render(int time) {
        for (Shape o : obj) {
            o.print();
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

    public void load() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fichier = new FileInputStream("data/save.dat");
            ois = new ObjectInputStream(fichier);
            map = (int[][]) ois.readObject();
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
