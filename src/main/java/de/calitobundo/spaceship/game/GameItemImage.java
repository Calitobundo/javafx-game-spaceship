package de.calitobundo.spaceship.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameItemImage {

    public static Map<Class, GameItemImage> map = new HashMap<>();

    public final List<Image> images = new ArrayList<>();

    public int imageFrames = 0;
    public int width = 0;
    public int height = 0;


    public GameItemImage(String prefix, String suffix, int frames, int width, int height) {

        this.imageFrames = frames;
        this.width = width;
        this.height = height;

        for (int i = 1; i <= frames; i++) {

            Image image = new Image(GameItemImage.class.getClassLoader().getResourceAsStream(prefix + i + suffix), width, height, false, false);
            images.add(image);
        }
    }

    public GameItemImage(String file, int width, int height) {

        this.imageFrames = 1;
        this.width = width;
        this.height = height;

        Image image = new Image(GameItemImage.class.getClassLoader().getResourceAsStream(file), width, height, false, false);
        images.add(image);
    }


    public Image nextFrame(GameItem item) {

        item.currentFrame++;

        if (item.currentFrame >= imageFrames - 1)
            item.currentFrame = 0;

        return images.get(item.currentFrame);

    }
}
