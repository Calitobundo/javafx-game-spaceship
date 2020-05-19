package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.item.GameItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Bonus extends GameItem {


    public Bonus(GameContext context) {
        super(context, 50, null);
    }

    @Override
    protected void update(double delta) {

        liveTime += delta;
        if(liveTime > 10)
            context.itemsToRemove.add(this);
    }

    @Override
    protected void render(GraphicsContext gc) {

        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Bonus 500", x, y);
        gc.setTextAlign(TextAlignment.LEFT);



    }

    @Override
    public void onDestroy() {

    }
}
