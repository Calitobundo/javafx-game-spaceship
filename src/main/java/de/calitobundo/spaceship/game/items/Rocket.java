package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameResource;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Rocket extends GameItem {


    public Player player = null;



    public Rocket(GameContext context, double x, double y) {
        super(context, 1,30, GameResource.getItemImage(Rocket.class));
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

        double sw = scale * itemImage.width;
        double sh = scale * itemImage.height;

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

            Random random = new Random();

            context.player.points += 20;

            for (int i = 0; i < 10; i++) {

                double randAngle = 2 * Math.PI * random.nextDouble();
                double randDistance = 2 * item.radius * item.scale * random.nextDouble();

                Explosion exp = new Explosion(context);
                exp.x = item.x + randDistance * Math.cos(randAngle);
                exp.y = item.y + randDistance * Math.sin(randAngle);
                context.itemsToAdd.add(exp);

            }
            context.itemsToRemove.add(this);
        }
    }


}
