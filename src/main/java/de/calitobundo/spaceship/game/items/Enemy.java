package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameResource;
import de.calitobundo.spaceship.game.item.GameItem;
import de.calitobundo.spaceship.game.item.GameItemImage;
import javafx.scene.canvas.GraphicsContext;

public class Enemy extends GameItem {


    public Enemy(GameContext context) {
        super(context, 50, GameResource.getItemImage(Player.class));
    }

    @Override
    protected void update(double delta) {

        x += vx * delta;

        bounds.checkBoundsAndFlipVelocityByCollision();

    }

    @Override
    protected void render(GraphicsContext gc) {

        double radius = bounds.getScaledRadius();
        gc.drawImage(itemImage.getFirstFrame(), x - radius, y - radius, 2*radius, 2*radius);

    }

    @Override
    public void onCollision(GameItem item) {

        if(item instanceof Rocket){

            context.itemsToRemove.add(this);

        }
    }

    @Override
    public void onDestroy() {

    }
}
