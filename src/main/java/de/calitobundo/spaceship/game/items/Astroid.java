package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.ItemFactory;
import de.calitobundo.spaceship.game.item.GameItem;
import de.calitobundo.spaceship.game.GameResource;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Astroid extends GameItem {

    public double maxLiveTime = 10;

    public Astroid(GameContext context) {
        super(context, 1, 25, GameResource.getItemImage(Astroid.class));
    }

    @Override
    protected void update(double delta) {

        liveTime += delta;

        vx += ax * delta;
        vy += ay * delta;

        x += vx * delta;
        y += vy * delta;

        if (liveTime > maxLiveTime) {
            context.itemsToRemove.add(this);
        }

    }

    @Override
    protected void render(GraphicsContext gc) {

        double radius = bounds.getScaledRadius();
        gc.drawImage(itemImage.nextFrame(this), x - radius, y - radius, 2*radius, 2*radius);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollision(GameItem item) {

        if(item instanceof Rocket){

            if(bounds.scale/2 < 0.5) {
                context.itemsToRemove.add(this);
                return;
            }

            ItemFactory.createAstroids(context, this);
            context.itemsToRemove.add(this);
        }


    }

}
