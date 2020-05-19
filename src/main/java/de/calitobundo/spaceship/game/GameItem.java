package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Astroid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.*;


public abstract class GameItem {

    public Color collisionColor = Color.RED;;
    public Color defaultColor = Color.CORNFLOWERBLUE;;
    public Color color = defaultColor;

    public final GameContext context;
    public final GameItemImage itemImage;
    public final Set<GameItem> collisions = new HashSet<>();

    public double time = 0;
    public long liveEnergie = 100;
    public double liveTime = 0;

    public double ax = 0;
    public double ay = 0;

    public double vx = 0;
    public double vy = 0;

    public double x = GameApp.WIDTH/2;
    public double y = 0;

    public double radius;
    public double scale;

    public int currentFrame = 0;



    public GameItem(GameContext context, double radius, GameItemImage itemImage){
        this(context, 1, radius, itemImage);

    }

    public GameItem(GameContext context, double scale, double radius, GameItemImage itemImage) {
        this.context = context;
        this.scale = scale;
        this.radius = radius;
        this.itemImage = itemImage;
    }

    protected abstract void update(double delta);

    protected abstract void render(GraphicsContext gc);

    public abstract void onDestroy();


    public void updateItem(double delta) {
        color = defaultColor;
        this.delta = delta;
        update(delta);
    }

    private double delta = 0;
    public void renderItem(GraphicsContext gc) {
        render(gc);
        if (context.debug) {
            gc.setFont(new Font("Verdana", 20));
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.WHITE);
            gc.strokeOval(x - scale*radius, y - scale*radius, 2 * scale*radius, 2 * scale*radius);
            gc.fillText(""+currentFrame, x, y);
            gc.setFill(Color.GRAY);
            gc.fillText("Delta: "+delta,220,40);

        }

    }

    public void onCollision(GameItem item){
        color = collisionColor;
    };




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

        items.forEach(GameItem::clearCollisions);

        for (GameItem testItem : items) {

            for (GameItem item : items) {

                if(!testItem.equals(item)) {

                    double dx = testItem.x - item.x;
                    double dy = testItem.y - item.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    double minDistance = testItem.scale * testItem.radius + testItem.scale * item.radius;

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

    public void clearCollisions() {
        collisions.clear();
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
