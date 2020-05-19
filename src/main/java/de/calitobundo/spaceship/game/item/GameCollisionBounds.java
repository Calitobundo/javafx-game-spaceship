package de.calitobundo.spaceship.game.item;

import de.calitobundo.spaceship.game.GameApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameCollisionBounds {


    private double radius;
    public double scale;
    public final GameItem item;


    public GameCollisionBounds(GameItem item, double scale, double radius) {
        this.scale = scale;
        this.radius = radius;
        this.item = item;
    }

    public void render(GraphicsContext gc) {

        if (item.context.debug) {
            gc.setFont(new Font("Verdana", 20));
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.WHITE);
            gc.strokeOval(item.x - scale*radius, item.y - scale*radius, 2 * scale*radius, 2 * scale*radius);
            gc.fillOval(item.x, item.y, 3, 3);
            //gc.setFill(Color.GRAY);
            //gc.fillText(""+scale, item.x, item.y);
            //gc.setFill(Color.GRAY);
            //gc.fillText("Delta: "+delta,220,40);

        }
    }


    public double getScaledRadius() {
        return scale * radius;
    }

    public void checkBoundsAndStopByCollision(){

        if(item.x < radius) {
            item.x = radius;
        }
        if(item.x > GameApp.WIDTH-radius) {
            item.x = GameApp.WIDTH-radius;
        }
        if(item.y < radius) {
            item.y = radius;
        }
        if(item.y >  GameApp.HEIGHT-radius) {
            item.y = GameApp.HEIGHT-radius;
        }

    }


    public void checkBoundsAndFlipByCollision(){

        if(item.x < radius) {
            item.x = GameApp.WIDTH-radius;
        }
        if(item.x > GameApp.WIDTH-radius) {
            item.x = radius;
        }
        if(item.y < radius) {
            item.y = GameApp.HEIGHT-radius;
            item.y = radius;
        }
        if(item.y >  GameApp.HEIGHT-radius) {
            item.y = radius;
        }

    }


    public void checkBoundsAndFlipVelocityByCollision(){

        if(item.x < radius) {
            item.x = radius;
            item.vx *= -1;
        }
        if(item.x > GameApp.WIDTH-radius) {
            item.x = GameApp.WIDTH-radius;
            item.vx *= -1;
        }
        if(item.y < radius) {
            item.y = radius;
            item.vy *= -1;
        }
        if(item.y >  GameApp.HEIGHT-radius) {
            item.y = GameApp.HEIGHT-radius;
            item.vy *= -1;
        }
    }


}
