package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameItemImage;
import de.calitobundo.spaceship.game.GameResource;
import javafx.scene.canvas.GraphicsContext;

public class Explosion extends GameItem {


    public Explosion(GameContext context) {
        super(context, 2, 50, GameResource.getItemImage(Explosion.class));
    }

    @Override
    protected void update(double delta) {

        liveTime += delta;
        if(liveTime > 0.3)
            context.itemsToRemove.add(this);
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
}
