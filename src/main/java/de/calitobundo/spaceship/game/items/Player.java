package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.GameApp;
import de.calitobundo.spaceship.game.GameContext;
import de.calitobundo.spaceship.game.GameItem;
import de.calitobundo.spaceship.game.GameItemController;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Player extends GameItem implements GameItemController {

    public static final double IMAGE_WIDTH = 150; //25
    public static final double IMAGE_HEIGHT = 150; //25
    private final double ACCELERATION = 2400;
    private final double VELOCITY_MAX = 1200;

    private Image image;

    private double autofireTime = 0.5;
    private boolean autofire = false;

    public Player(GameContext context) {
        super(context, IMAGE_WIDTH/2);
        this.image = new Image(getClass().getResourceAsStream("/images/ship.png"), IMAGE_WIDTH, IMAGE_HEIGHT, false, false);

        x = GameApp.WIDTH/2;
        y = 2 * GameApp.HEIGHT/5;

    }

    @Override
    public void update(double delta) {
        super.update(delta);

        time += delta;
        autofireTime+= delta;

        if(left)
            ax = -ACCELERATION;
        if(right)
            ax = +ACCELERATION;
        if(left && right || !left && !right) {
            ax = 0;
            vx = 0;
        }


        if(up)
            ay = -ACCELERATION;
        if(down)
            ay = +ACCELERATION;
        if(up && down || !up && !down) {
            ay = 0;
            vy = 0;
        }

        vx += ax * delta;
        vy += ay * delta;

        if(vx > VELOCITY_MAX) vx = VELOCITY_MAX;
        else if(vx < -VELOCITY_MAX) vx = -VELOCITY_MAX;
        if(vy > VELOCITY_MAX) vy = VELOCITY_MAX;
        else if(vy < -VELOCITY_MAX) vy = -VELOCITY_MAX;

        x += vx * delta;
        y += vy * delta;

        checkBoundsAndStopByCollision();

        if(autofire){
            if(autofireTime > 0.1) {
                autofireTime = 0;
                Rocket rocket = new Rocket(context, x, y);
                context.addGameItem(rocket);
            }
        }


    }


    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(image, x - IMAGE_WIDTH/2, y - IMAGE_HEIGHT/2);

        if (context.debug) {
            gc.setStroke(color);
            gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
        }
        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.GRAY);
        gc.fillText("CollisionCount: "+collissionCount, 20, 60);
        gc.fillText("left: "+left, 20, 80);
        gc.fillText("right: "+right, 20, 100);
        gc.fillText("up: "+up, 20, 120);
        gc.fillText("down: "+down, 20, 140);

    }

    @Override
    public void onDestroy() {

    }
    public long collissionCount = 0;

    @Override
    public void onCollision(de.calitobundo.spaceship.game.GameItem item) {
        if(item instanceof Astroid){
            color = Color.GREEN;
            collissionCount++;
            context.itemsToRemove.add(item);
        }
    }


    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;



    @Override
    public void onLeft(EventType<KeyEvent> eventType) {
        if(!left && eventType == KeyEvent.KEY_PRESSED) {
            left = true;
            //ax = -ACCELERATION;
        }
        if(left && eventType == KeyEvent.KEY_RELEASED) {
            left = false;
            //ax = 0;
            //vx = 0;
        }
    }

    @Override
    public void onRight(EventType<KeyEvent> eventType) {
        if(!right && eventType == KeyEvent.KEY_PRESSED) {
            right = true;
            //ax = ACCELERATION;
        }
        if(right && eventType == KeyEvent.KEY_RELEASED){
            right = false;
            //ax = 0;
            //vx = 0;
        }
    }

    @Override
    public void onUp(EventType<KeyEvent> eventType) {
        if (!up && eventType == KeyEvent.KEY_PRESSED) {
            up = true;
            //ay = -ACCELERATION;
        }
        if (up && eventType == KeyEvent.KEY_RELEASED) {
            up = false;
           // ay = 0;
           // vy = 0;
        }
    }

    @Override
    public void onDown(EventType<KeyEvent> eventType) {
        if(!down &&eventType == KeyEvent.KEY_PRESSED) {
            down = true;
           // ay = ACCELERATION;
        }
        if(down && eventType == KeyEvent.KEY_RELEASED) {
            down = false;
           // ay = 0;
            //vy = 0;
        }
    }



    @Override
    public void onFire(EventType<KeyEvent> eventType) {

        autofireTime = 1;
        if(eventType == KeyEvent.KEY_PRESSED) {
            autofire = true;
        }else if(eventType == KeyEvent.KEY_RELEASED) {
            autofire = false;
        }

    }

}
