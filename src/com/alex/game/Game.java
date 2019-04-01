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
        translateView(0.5f, 0.5f);
        world.update();
    }

    public float getxScroll() {
        return xScroll;
    }

    public float getyScroll() {
        return yScroll;
    }

    public void render(int time) {
        GL11.glTranslatef(xScroll, yScroll, 0);
        world.render(time, player, this);
        player.render(time, this);
    }

    public void stop() {
        world.save();
    }
}
