package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Astroid;
import de.calitobundo.spaceship.game.items.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameContext {


    public boolean debug = true;

    private final GameTimer timer;
    public final Canvas canvas;

    //Game Events
    public final List<GameEvent> events = new ArrayList<>();

    //Game Items
    public final List<GameItem> items = new ArrayList<>();
    public final List<GameItem> itemsToAdd = new ArrayList<>();
    public final List<GameItem> itemsToRemove = new ArrayList<>();

    private final GameItemImage itemImage = GameResource.getItemImage(GameContext.class);

    public Player player = new Player(this);
    private GameItemEventHandler handler = new GameItemEventHandler(player);

    public GameContext(Canvas canvas) {

        this.canvas = canvas;
        this.timer = new GameTimer(this);
    }

    public void restart() {
        items.clear();
        itemsToAdd.clear();
        itemsToRemove.clear();
        player = new Player(this);
        items.add(player);
        canvas.removeEventHandler(KeyEvent.ANY, handler);
        handler = new GameItemEventHandler(player);
        canvas.addEventHandler(KeyEvent.ANY  , handler);
    }


    public void start(){

        items.add(player);
        handler = new GameItemEventHandler(player);
        canvas.addEventHandler(KeyEvent.ANY  , handler);
        addAstdroids();
        timer.start();
    }

    private void addAstdroids(){

        GameEvent addAstdroidEvent = new GameEvent(this,2);
        events.add(addAstdroidEvent);
        addAstdroidEvent.set(() -> {

            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                Astroid astroid = new Astroid(this);
                astroid.currentFrame = random.nextInt(48);
                astroid.scale = 0.5 + random.nextDouble();
                astroid.vx = 3*(40 * random.nextDouble() - 20);
                astroid.vy = 3*(20 + 20 * random.nextDouble());
                astroid.x = GameApp.WIDTH * random.nextDouble();
                astroid.y = -100 * random.nextDouble();
                items.add(astroid);
            }

        });

    }


    public void nextFrame(double delta, GraphicsContext graphicsContext) {

        if(player.liveEnergie <= 0){
            renderItems(graphicsContext);
        }else{
            updateItems(delta);
            renderItems(graphicsContext);
            updateEvents(delta);
        }

    }


    public void updateItems(double delta){

        //update all items
        for (GameItem item : items) {
            item.updateItem(delta);
        }

        //test on collisions
        GameItem.testAllOnCollision(items);

        //remove destroyed items
        items.removeAll(itemsToRemove);

        //destroy items
        itemsToRemove.forEach(GameItem::onDestroy);
        itemsToRemove.clear();

        //add new items
        items.addAll(itemsToAdd);
        itemsToAdd.clear();

    }


    private double backgroundY = 0;

    public void renderItems(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameApp.WIDTH, GameApp.HEIGHT);

        backgroundY++;
        if(backgroundY > 1000)
            backgroundY = 0;

        gc.drawImage(itemImage.getFirstFrame(), 0, backgroundY);
        gc.drawImage(itemImage.getFirstFrame(), 0, backgroundY - 1000);

        for (GameItem item : items) {
            item.renderItem(gc);
        }

        // render points
        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.WHITE);
        gc.fillText("Points: "+player.points, 10, 1000-10);

        if(player.liveEnergie <= 0){
            gc.setFont(new Font("Verdana", 40));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Game Over", 300, 500);
            gc.fillText(player.points+" Points", 300, 500+40);
            gc.fillText("Press R to restart", 300, 500+80);
            gc.setTextAlign(TextAlignment.LEFT);


        }

        //debug
        if(debug) {
            gc.setFont(new Font("Verdana", 20));
            gc.setFill(Color.GRAY);
            gc.fillText("GameItems: " + items.size(), 20, 40);
        }
            gc.fillText("FPS: " + timer.fps, 500, 40);

    }


    public void updateEvents(double delta){
        for (GameEvent event : events) {
            event.update(delta);
        }
    }


}
