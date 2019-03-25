package com.alex;

import com.alex.shapes.Shape;
import com.alex.shapes.Square;

import java.util.ArrayList;

public class World {
    private ArrayList<Shape> obj;

    public World() {
        obj = new ArrayList<>();
        float[] color = {0f, 0.4f, 0.1f};
        Square square = new Square(30, 20, color, 50);
        Square square1 = new Square(30, 50, color, 70);
        obj.add(square1);
        obj.add(square);
    }

    public void render(int time) {
        for (Shape o : obj) {
            o.print();
        }
    }
}
