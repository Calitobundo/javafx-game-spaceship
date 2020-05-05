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

public class GameContext {

    public boolean debug = true;

    private final GameTimer timer;
    private final Canvas canvas;

    public final List<de.calitobundo.spaceship.game.GameItem> items = new ArrayList<>();
    public final List<de.calitobundo.spaceship.game.GameItem> itemsToAdd = new ArrayList<>();
    public final List<de.calitobundo.spaceship.game.GameItem> itemsToRemove = new ArrayList<>();

    private final Player player = new Player(this);;
    private double eventTime = 0;


    public GameContext(Canvas canvas) {

        this.canvas = canvas;
        this.timer = new GameTimer(this);
    }


    public void start(){

        items.add(player);
        canvas.addEventHandler(KeyEvent.ANY  , new GameItemEventHandler(player));
        timer.start();
    }


    public void onFrame(double delta) {

        update(delta);
        render(canvas.getGraphicsContext2D());

        eventTime += delta;
        if(eventTime > 0.15){
            eventTime = 0;
            Random random = new Random();
            for (int i = 0; i < 2; i++){
                Astroid astroid = new Astroid(this);
                astroid.frame = random.nextInt(48);
                astroid.vx = 400 * random.nextDouble() - 200;
                astroid.vy = 200 + 200 * random.nextDouble();
                astroid.x = GameApp.WIDTH * random.nextDouble();
                astroid.y = -100 * random.nextDouble();
                items.add(astroid);
            }
        }
    }


    public void update(double delta){

        //update all items
        for (de.calitobundo.spaceship.game.GameItem item : items) {
            item.update(delta);
        }

        //test on collisions
        de.calitobundo.spaceship.game.GameItem.testAllOnCollision(items);

        //remove destroyed items
        items.removeAll(itemsToRemove);

        //destroy items
        itemsToRemove.forEach(de.calitobundo.spaceship.game.GameItem::onDestroy);
        itemsToRemove.clear();

        //add new items
        items.addAll(itemsToAdd);
        itemsToAdd.clear();

    }


    public void render(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameApp.WIDTH, GameApp.HEIGHT);

        for (de.calitobundo.spaceship.game.GameItem item : items) {
            item.render(gc);
        }

        //debug
        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.GRAY);
        gc.fillText("GameItems: "+items.size(),20,40);
    }


    public void addGameItem(de.calitobundo.spaceship.game.GameItem item) {
        itemsToAdd.add(item);
    }
}
