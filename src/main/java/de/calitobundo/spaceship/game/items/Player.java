package de.calitobundo.spaceship.game.items;

import de.calitobundo.spaceship.game.*;
import de.calitobundo.spaceship.game.item.GameItem;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class Player extends GameItem implements GameItemController {

    private final double ACCELERATION = 2400;
    private final double VELOCITY_MAX = 600;

    private double autofireTime = 1;
    private boolean autofire = false;

    private double collisionTime = 0;
    public long points = 0;


    private Color liveEnergieColorOnCollide = Color.RED;


    public Player(GameContext context) {
        super(context, 0.625,65, GameResource.getItemImage(Player.class));

        x = GameApp.WIDTH/2;
        y = 4 * GameApp.HEIGHT/5;

    }

    @Override
    protected void update(double delta) {

        time += delta;
        autofireTime += delta;
        collisionTime += delta;

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

        bounds.checkBoundsAndStopByCollision();

        if(autofire){
            if(autofireTime > 0.125) {
                autofireTime = 0;
                Rocket rocket1 = new Rocket(context, x, y, Player.class);
//                Rocket rocket2 = new Rocket(context, x+20, y);
//                Rocket rocket3 = new Rocket(context, x-20, y);
//                Rocket rocket4 = new Rocket(context, x-40, y);
                context.itemsToAdd.add(rocket1);
//                context.itemsToAdd.add(rocket2);
//                context.itemsToAdd.add(rocket3);
//                context.itemsToAdd.add(rocket4);

            }
        }


    }


    @Override
    protected void render(GraphicsContext gc) {

        double sw = bounds.scale * itemImage.width;
        double sh = bounds.scale * itemImage.height;

        gc.drawImage(itemImage.nextFrame(this), x - sw/2, y - sh/2, sw, sh);
        gc.setFont(new Font("Verdana", 20));

        // render players energie
        double x1 = x - sw/2;
        double y1 = y - sh/2 - 10;
        double w = sw * ((double)liveEnergie/100);
        double h = 5;
        if(collisionTime < 0.5)
            gc.setFill(Color.RED);
        else
            gc.setFill(Color.GREEN);
        gc.fillRect(x1, y1, w, h);
        gc.fillRect(0, 0, GameApp.WIDTH * ((double)liveEnergie/100), 20);

        // debug info
        if(context.debug) {
            gc.setFill(Color.GRAY);
            gc.fillText("CollisionCount: " + collissionCount, 20, 60);
            gc.fillText("left: " + left, 20, 80);
            gc.fillText("right: " + right, 20, 100);
            gc.fillText("up: " + up, 20, 120);
            gc.fillText("down: " + down, 20, 140);
        }

    }

    @Override
    public void onDestroy() {

    }
    public long collissionCount = 0;

    @Override
    public void onCollision(GameItem item) {

        if(item instanceof Enemy){
            collisionTime = 0;
            liveEnergie -= 10;
            context.player.points += 100;
            context.itemsToRemove.add(item);
            ItemFactory.createExplosions(context, this, 50, 3, 5);
        }

        if(item instanceof Astroid){
            collisionTime = 0;
            liveEnergie -= 5;
            context.itemsToRemove.add(item);
            ItemFactory.createExplosions(context, this, 10, 3, 2);

        }

        if(item instanceof Bonus){

            points += 500;
            context.itemsToRemove.add(item);
        }

        if(item instanceof Rocket){

            Rocket r = (Rocket) item;
            if(r.type == Enemy.class){
                collisionTime = 0;
                liveEnergie -= 10;
            }
        }

    }


    /**
     *
     *
     *
     *  GameItemController Implementation
     *
     *
     *
     */

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

        //autofireTime = 1;
        if(eventType == KeyEvent.KEY_PRESSED) {
            autofire = true;
        }else if(eventType == KeyEvent.KEY_RELEASED) {
            autofire = false;
        }

    }

}
