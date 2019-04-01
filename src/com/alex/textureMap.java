package com.alex;

import com.alex.graphics.Texture;

import java.util.HashMap;

public class textureMap {
    protected static HashMap<String, Texture> textures;
    private static textureMap ourInstance = new textureMap();

    private textureMap() {
        textures = new HashMap<>();
        Texture tiles = Texture.loadTexture("/null.png");
        textures.put("null", tiles);
    }

    public static textureMap getInstance() {
        return ourInstance;
    }

    public static Texture getTiles(String str) {
        if (textures.containsKey(str)) {
            return textures.get(str);
        }
        return textures.get("null");
    }

    public boolean isLoaded(String str) {
        return textures.containsKey(str);
    }

    public void loadTextureInMap(String str) {

        Texture tiles = textures.get("null");
        try {
            if (!isLoaded(str)) {
                tiles = Texture.loadTexture("/" + str + ".png");
            }
        } catch (Exception e) {
            tiles = textures.get("null");
        }
        textures.put(str, tiles);
    }

}
