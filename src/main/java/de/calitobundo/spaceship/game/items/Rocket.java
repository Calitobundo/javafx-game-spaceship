package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.ItemFactory;
import de.calitobundo.spaceship.game.item.GameItem;
import de.calitobundo.spaceship.game.GameResource;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Rocket extends GameItem {


    public Player player = null;



    public Rocket(GameContext context, double x, double y) {
        super(context, 0.75,30, GameResource.getItemImage(Rocket.class));
        this.x = x;
        this.y = y;
        this.vy = -800;
    }

    @Override
    protected void update(double delta) {

        liveTime += delta;

        vx += ax * delta;
        vy += ay * delta;

        x += vx * delta;
        y += vy * delta;

        if (liveTime > 6) {
            context.itemsToRemove.add(this);
        }
    }

    @Override
    protected void render(GraphicsContext gc) {

        double sw = bounds.scale * itemImage.width;
        double sh = bounds.scale * itemImage.height;

        gc.save();
        gc.translate(x, y);
        gc.rotate(90);
        gc.drawImage(itemImage.nextFrame(this), -sw/2, -sh/2, sw, sh);
        gc.restore();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollision(GameItem item) {

        if(item instanceof Astroid){

            context.player.points += 20;
            context.itemsToRemove.add(this);
            ItemFactory.createExplosions(context, this, 10, 2, 1);

        }

        if(item instanceof Enemy){

            context.player.points += 100;
            ItemFactory.createExplosions(context, this, 50, 3, 5);
            context.itemsToRemove.add(this);
        }

    }


}
