package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameItemImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rocket extends GameItem {

    static {

        GameItemImage itemImage = new GameItemImage("images/rocket/rakete", ".png", 128, 100, 100);
        GameItemImage.map.put(Rocket.class, itemImage);

    }

    public Rocket(GameContext context, double x, double y) {
        super(context, 1,30, GameItemImage.map.get(Rocket.class));
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

        if (liveTime > 6) {
            context.itemsToRemove.add(this);
        }
    }

    @Override
    public void render(GraphicsContext gc) {

        double sw = scale * itemImage.width;
        double sh = scale * itemImage.height;

        gc.save();
        gc.translate(x, y);
        gc.rotate(90);
        gc.drawImage(itemImage.nextFrame(this), -sw/2, -sh/2, sw, sh);
        gc.restore();

        super.render(gc);

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
