package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Astroid;
import de.calitobundo.spaceship.game.items.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

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

    private final Player player = new Player(this);;


    public GameContext(Canvas canvas) {

        this.canvas = canvas;
        this.timer = new GameTimer(this);
    }


    public void start(){

        items.add(player);
        canvas.addEventHandler(KeyEvent.ANY  , new GameItemEventHandler(player));

        GameEvent addAstdroidEvent = new GameEvent(this,0.4);
        events.add(addAstdroidEvent);
        addAstdroidEvent.set(() -> {

            Random random = new Random();
            for (int i = 0; i < 2; i++) {
                Astroid astroid = new Astroid(this);
                //astroid.frame = random.nextInt(48);
                astroid.scale = 0.5 + 1.4 * random.nextDouble();
                astroid.vx = 400 * random.nextDouble() - 200;
                astroid.vy = 200 + 200 * random.nextDouble();
                astroid.x = GameApp.WIDTH * random.nextDouble();
                astroid.y = -100 * random.nextDouble();
                items.add(astroid);
            }

        });


        timer.start();
    }


    public void nextFrame(double delta, GraphicsContext graphicsContext) {

        updateItems(delta);
        renderItems(graphicsContext);
        updateEvents(delta);
    }


    public void updateItems(double delta){

        //update all items
        for (GameItem item : items) {
            item.update(delta);
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


    public void renderItems(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameApp.WIDTH, GameApp.HEIGHT);

        for (GameItem item : items) {
            item.render(gc);
        }

        //debug
        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.GRAY);
        gc.fillText("GameItems: "+items.size(),20,40);
    }


    public void updateEvents(double delta){
        for (GameEvent event : events) {
            event.update(delta);
        }
    }

    public void addGameItem(GameItem item) {
        itemsToAdd.add(item);
    }
}
