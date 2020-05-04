package de.calitobundo.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Rocket extends GameItem {

    public static final double IMAGE_WIDTH = 100;
    public static final double IMAGE_HEIGHT = 100;
    public static final List<Image> ROCKET_IMAGES = new ArrayList<>();
    private int frame = 0;

    static {
        for (int i = 1; i <= 128; i++) {
            Image image = new Image(Rocket.class.getClassLoader().getResourceAsStream("images/rocket/rakete"+i+".png"), IMAGE_WIDTH, IMAGE_HEIGHT, false, false);
            ROCKET_IMAGES.add(image);
        }
    }

    public Rocket(double x, double y) {
        this.x = x;
        this.y = y;
        this.ay = -100;
    }

    @Override
    public void update(double delta) {

        time += delta;

        vx += ax * delta;
        vy += ay * delta;

        x += vx * delta;
        y += vy * delta;

        frame++;
        if(frame >= 127)
            frame = 0;

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.save();
        gc.translate(x, y);
        gc.rotate(90);
        gc.drawImage(ROCKET_IMAGES.get(frame), -IMAGE_WIDTH/2, -IMAGE_HEIGHT/2);
        gc.restore();

    }
}
