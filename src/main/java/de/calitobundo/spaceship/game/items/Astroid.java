package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameResource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Astroid extends GameItem {

    private int maxLiveTime = 5;

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

        double sw = scale * itemImage.width;
        double sh = scale * itemImage.height;

        gc.drawImage(itemImage.nextFrame(this), x - sw/2, y - sh/2, sw, sh);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollision(GameItem item) {

        if(item instanceof Rocket){

            if(scale < 0.2) {
                context.itemsToRemove.add(this);
                return;
            }
            Random random = new Random();

            final int count = 5 + random.nextInt(6);
            double angle = Math.PI * random.nextDouble();
            for (int i = 0; i < count; i++) {

                angle += 2 * Math.PI / count;
                Astroid astroid = new Astroid(context);
                astroid.scale = scale/2;
                astroid.maxLiveTime = maxLiveTime/2;
                astroid.x = x;
                astroid.y = y;
                astroid.vx = (50 + 100 * random.nextDouble()) * Math.cos(angle);
                astroid.vy = (50 + 100 * random.nextDouble()) * Math.sin(angle);
                context.itemsToAdd.add(astroid);
            }

            context.itemsToRemove.add(this);
        }


    }

}
