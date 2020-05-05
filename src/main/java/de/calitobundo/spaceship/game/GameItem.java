package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Astroid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;


public abstract class GameItem {

    public Color collisionColor = Color.RED;;
    public Color defaultColor = Color.CORNFLOWERBLUE;;
    public Color color = defaultColor;

    public GameContext context;
    public Set<GameItem> collisions = new HashSet<>();

    public int frame = 0;

    public double time = 0;
    public double liveTime = 0;

    public double ax = 0;
    public double ay = 0;

    public double vx = 0;
    public double vy = 0;

    public double x = GameApp.WIDTH/2;
    public double y = 0;

    public double radius = 0;


    public GameItem(GameContext context, double radius){
        this.context = context;
        this.radius = radius;
    }

    public void update(double delta) {
        color = defaultColor;
    }

    public abstract void render(GraphicsContext gc);

    public void onCollision(GameItem item){
        color = collisionColor;
    };

    public abstract void onDestroy();


    public List<GameItem> testCollision(List<GameItem> items) {

        List<GameItem> collisions = new ArrayList<>();
        for (GameItem item : items) {
            if(item instanceof Astroid) {
                double dx = x - item.x;
                double dy = y - item.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double minDistance = radius + item.radius;
                if (distance < minDistance) {
                    collisions.add(item);
                    item.onCollision(this);
                    onCollision(item);
                }
            }
        }
        return collisions;
    }


    public static void testAllOnCollision(List<GameItem> items) {

        items.stream().forEach(item -> {
            item.collisions.clear();
        });

        for (GameItem testItem : items) {

            for (GameItem item : items) {

                if(!testItem.equals(item)) {

                    double dx = testItem.x - item.x;
                    double dy = testItem.y - item.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    double minDistance = testItem.radius + item.radius;

                    if (distance < minDistance) {

                        if(testItem.collisions.add(item))
                            testItem.onCollision(item);

                        if(item.collisions.add(testItem))
                            item.onCollision(testItem);

                    }
                }
            }
        }
    }

    public void checkBoundsAndStopByCollision(){

        if(x < radius) {
            x = radius;
        }
        if(x > GameApp.WIDTH-radius) {
            x = GameApp.WIDTH-radius;
        }
        if(y < radius) {
            y = radius;
        }
        if(y >  GameApp.HEIGHT-radius) {
            y = GameApp.HEIGHT-radius;
        }

    }


    public void checkBoundsAndFlipByCollision(){

        if(x < radius) {
            x = GameApp.WIDTH-radius;
        }
        if(x > GameApp.WIDTH-radius) {
            x = radius;
        }
        if(y < radius) {
            y = GameApp.HEIGHT-radius;
            y = radius;
        }
        if(y >  GameApp.HEIGHT-radius) {
            y = radius;
        }

    }


    public void checkBoundsAndFlipVelocityByCollision(){

        if(x < radius) {
            x = radius;
            vx *= -1;
        }
        if(x > GameApp.WIDTH-radius) {
            x = GameApp.WIDTH-radius;
            vx *= -1;
        }
        if(y < radius) {
            y = radius;
            vy *= -1;
        }
        if(y >  GameApp.HEIGHT-radius) {
            y = GameApp.HEIGHT-radius;
            vy *= -1;
        }
    }

}
