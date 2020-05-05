package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameItemImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Astroid extends GameItem {


    static {

        GameItemImage itemImage = new GameItemImage("images/astdroid/gold/steinGold", ".png", 48, 50, 50);
        GameItemImage.map.put(Astroid.class, itemImage);

    }

    public Astroid(GameContext context) {
        super(context, 1, 25, GameItemImage.map.get(Astroid.class));

    }

    @Override
    public void update(double delta) {
        super.update(delta);

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
    public void render(GraphicsContext gc) {

        double sw = scale * itemImage.width;
        double sh = scale * itemImage.height;

        gc.drawImage(itemImage.nextFrame(this), x - sw/2, y - sh/2, sw, sh);

        super.render(gc);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCollision(GameItem item) {
        if(item instanceof Rocket){
            color = Color.YELLOWGREEN;
            context.itemsToRemove.add(this);
        }
        if(item instanceof Player){
            color = Color.GREEN;
        }
    }

}
