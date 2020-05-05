package de.calitobundo.spaceship.game;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {

    private static final double nanosPerSecond = 1000000000;
    private double before;

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
        context.onFrame(delta/nanosPerSecond);
        before = now;
    }


}
