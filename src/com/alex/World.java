package com.alex;

import java.io.Serializable;

public class World implements Serializable {
    Chunck chunck = new Chunck();


    public World() {
        float[] color = {0f, 0.4f, 0.1f};
    }

    public void render(int time) {
        chunck.render(time);
    }

    public void save() {
        chunck.save();
    }
}
