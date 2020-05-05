package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Rocket extends GameItem {

    public static final double IMAGE_WIDTH = 100;
    public static final double IMAGE_HEIGHT = 100;
    public static final double IMAGE_FRAMES = 128;

    public static final List<Image> ROCKET_IMAGES = new ArrayList<>();

    static {
        for (int i = 1; i <= IMAGE_FRAMES; i++) {
            Image image = new Image(Rocket.class.getClassLoader().getResourceAsStream("images/rocket/rakete"+i+".png"), IMAGE_WIDTH, IMAGE_HEIGHT, false, false);
            ROCKET_IMAGES.add(image);
        }
    }

    public Rocket(GameContext context, double x, double y) {
        super(context, IMAGE_WIDTH/2);
        this.x = x;
        this.y = y;
        this.vy = -800;
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        liveTime += delta;

        vx += ax * delta;
        vy += ay * delta;

        x += vx * delta;
        y += vy * delta;

        frame++;
        if(frame >= IMAGE_FRAMES - 1)
            frame = 0;

        if (liveTime > 6) {
            context.itemsToRemove.add(this);
        }
    }

    @Override
    public void render(GraphicsContext gc) {

        gc.save();
        gc.translate(x, y);
        gc.rotate(90);
        gc.drawImage(ROCKET_IMAGES.get(frame), -IMAGE_WIDTH/2, -IMAGE_HEIGHT/2);
        gc.restore();
        if (context.debug) {
            gc.setStroke(Color.CORNFLOWERBLUE);
            gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
        }

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollision(GameItem item) {
        if(item instanceof Astroid){
            color = Color.YELLOWGREEN;
            context.itemsToRemove.add(this);
        }
    }


}
