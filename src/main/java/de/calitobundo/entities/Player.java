package de.calitobundo.entities;

import de.calitobundo.GameApp;
import de.calitobundo.GameContext;
import de.calitobundo.PlayerController;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends GameItem implements PlayerController {

    private final double ACCELERATION = 2400;
    private final double VELOCITY_MAX = 600;

    private final GameContext context;

    private Image image;

    public Player(GameContext context) {
        this.context = context;
        this.image = new Image(getClass().getResourceAsStream("/images/ship.png"), 150, 150, false, false);

        x = GameApp.WIDTH/2;
        y = 2 * GameApp.HEIGHT/5;

    }

    @Override
    public void update(double delta) {
        time += delta;

        vx += ax * delta;
        vy += ay * delta;

        if(vx >= VELOCITY_MAX){
            vx = VELOCITY_MAX;
        }
        if(vx <= -VELOCITY_MAX){
            vx = -VELOCITY_MAX;
        }
        if(vy >= VELOCITY_MAX){
            vy = VELOCITY_MAX;
        }
        if(vy <= -VELOCITY_MAX){
            vy = -VELOCITY_MAX;
        }

        x += vx * delta;
        y += vy * delta;

        check(75);

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(image, x-75, y-75, 150, 150);

    }


    @Override
    public void onLeft(EventType<KeyEvent> eventType) {
        if(eventType == KeyEvent.KEY_PRESSED)
            ax = -ACCELERATION;
        if(eventType == KeyEvent.KEY_RELEASED) {
            ax = 0;
            vx = 0;
        }
    }

    @Override
    public void onRight(EventType<KeyEvent> eventType) {
        if(eventType == KeyEvent.KEY_PRESSED)
            ax = ACCELERATION;
        if(eventType == KeyEvent.KEY_RELEASED){
            ax = 0;
            vx = 0;
        }
    }

    @Override
    public void onUp(EventType<KeyEvent> eventType) {
        if (eventType == KeyEvent.KEY_PRESSED)
            ay = -ACCELERATION;
        if (eventType == KeyEvent.KEY_RELEASED) {
            ay = 0;
            vy = 0;
        }
    }

    @Override
    public void onDown(EventType<KeyEvent> eventType) {
        if(eventType == KeyEvent.KEY_PRESSED)
            ay = ACCELERATION;
        if(eventType == KeyEvent.KEY_RELEASED) {
            ay = 0;
            vy = 0;
        }
    }

    @Override
    public void onFire(EventType<KeyEvent> eventType) {

        if(eventType == KeyEvent.KEY_PRESSED) {
            Rocket rocket = new Rocket(x, y);
            context.addGameItem(rocket);
        }
    }

}
