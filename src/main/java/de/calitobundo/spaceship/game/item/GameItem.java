package de.calitobundo.spaceship.game.item;

import de.calitobundo.spaceship.game.GameApp;
import de.calitobundo.spaceship.game.GameContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class GameItem {

    public Color collisionColor = Color.RED;;
    public Color defaultColor = Color.CORNFLOWERBLUE;
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

    public GameCollisionBounds bounds;

    //public double radius;
    //public double scale;

    public int currentFrame = 0;


    public GameItem(GameContext context, double radius, GameItemImage itemImage){
        this(context, 1, radius, itemImage);

    }

    public GameItem(GameContext context, double scale, double radius, GameItemImage itemImage) {
        this.context = context;
        //this.scale = scale;
        //this.radius = radius;
        this.itemImage = itemImage;
        this.bounds = new GameCollisionBounds(this, scale, radius);
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
        bounds.render(gc);
//        if (context.debug) {
//            gc.setFont(new Font("Verdana", 20));
//            gc.setFill(Color.BLACK);
//            gc.setStroke(Color.WHITE);
//            gc.strokeOval(x - scale*radius, y - scale*radius, 2 * scale*radius, 2 * scale*radius);
//            gc.setFill(Color.GRAY);
//            gc.fillText(""+scale, x, y);
//            gc.setFill(Color.GRAY);
//            //gc.fillText("Delta: "+delta,220,40);
//
//        }

    }

    public void onCollision(GameItem item){
        color = collisionColor;
    };




    public List<GameItem> testCollision(List<GameItem> items) {

        List<GameItem> collisions = new ArrayList<>();
//        for (GameItem item : items) {
//            if(item instanceof Astroid) {
//                double dx = x - item.x;
//                double dy = y - item.y;
//                double distance = Math.sqrt(dx * dx + dy * dy);
//                double minDistance = bounds.radius + item.bounds.radius;
//                if (distance < minDistance) {
//                    collisions.add(item);
//                    item.onCollision(this);
//                    onCollision(item);
//                }
//            }
//        }
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
                    double minDistance = testItem.bounds.getScaledRadius() + item.bounds.getScaledRadius();

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



}
