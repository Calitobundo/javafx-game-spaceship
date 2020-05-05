package de.calitobundo.spaceship.game;

public class GameEvent {

    public final GameContext context;
    public final double eventTime;
    public double time = 0;

    public GameEventHandler handler = null;

    public GameEvent(GameContext context, double eventTime) {
        this.context = context;
        this.eventTime = eventTime;
    }


    public void update(double delta) {

        if(handler == null)
            return;

        time += delta;
        if(time > eventTime){
            time = 0;
            handler.doEvent();
        }
    }


    public void set(GameEventHandler handler) {
        this.handler = handler;
    }
}
