package de.calitobundo.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Monster extends GameItem{


    public Monster() {

        x = 200;

    }

    @Override
    public void update(double delta) {
        time += delta;
//        v += 100 * delta;
//        y += v * delta;
//        check();

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.setFill(Color.BLUE);
        gc.fillRect(x-20, y-20, 40, 40);

    }



}
