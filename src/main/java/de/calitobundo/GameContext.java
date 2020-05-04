package de.calitobundo;

import de.calitobundo.entities.GameItem;
import de.calitobundo.entities.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameContext {

    private Player player;

    private final GameTimer timer;
    private final Canvas canvas;
    private final List<GameItem> items = new ArrayList<>();

    public GameContext(Canvas canvas) {
        this.canvas = canvas;
        this.timer = new GameTimer(this);
    }

    public void start(){
        player = new Player(this);
        items.add(player);
        canvas.addEventHandler(KeyEvent.ANY  , new PlayerEventHandler(player));
        timer.start();
    }

    public void onFrame(double delta) {
        update(delta);
        render(canvas.getGraphicsContext2D());
    }

    public void update(double delta){
        for (GameItem item : items) {
            item.update(delta);
        }
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameApp.WIDTH, GameApp.HEIGHT);
        for (GameItem item : items) {
            item.render(gc);
        }
    }

    public void addGameItem(GameItem item) {
        items.add(item);
    }
}
