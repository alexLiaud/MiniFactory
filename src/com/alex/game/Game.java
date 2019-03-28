package com.alex.game;

import com.alex.World;
import org.lwjgl.opengl.GL11;

public class Game {
    private World world;
    private Player player;
    private float xScroll = 0;
    private float yScroll = 0;

    public Game() {
        world = new World();
        world.load();
        player = new Player();
    }

    public void init() {

    }

    public void translateView(float xa, float ya) {
        xScroll += xa;
        yScroll += ya;
    }

    public void update() {
        translateView(0.1f, 0.1f);
        world.update();
    }

    public void render(int time) {
        System.out.println("render" + xScroll + "" + yScroll);
        GL11.glTranslatef(xScroll, yScroll, 0);
        world.render(time, player);
        player.render(time);
    }

    public void stop() {
        world.save();
    }
}
