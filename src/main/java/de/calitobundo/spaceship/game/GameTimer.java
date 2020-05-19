package de.calitobundo.spaceship.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameTimer extends AnimationTimer {

    private static final double nanosPerSecond = 1000000000;
    private double before;
    public double fps = 0;
    private double deltaFps = 0;
    private double countFps = 0;

    private final GameContext context;

    public GameTimer(GameContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        before = System.nanoTime();
        super.start();
    }

    @Override
    public void handle(long now) {
        final double delta = now - before;
        deltaFps += delta/nanosPerSecond;
        countFps++;
        if(deltaFps > 1){
            fps = countFps;
            deltaFps = 0;
            countFps = 0;
        }

        final GraphicsContext graphicsContext = context.canvas.getGraphicsContext2D();
        context.nextFrame(delta/nanosPerSecond, graphicsContext);
        before = now;
    }


}
