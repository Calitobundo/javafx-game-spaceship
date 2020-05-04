package de.calitobundo;

import de.calitobundo.entities.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerEventHandler implements EventHandler<KeyEvent> {

    private Player player;


    public PlayerEventHandler(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {

        switch (event.getCode()) {
            case RIGHT:
                player.onRight(event.getEventType());
                break;

            case LEFT:
                player.onLeft(event.getEventType());
                break;

            case UP:
                player.onUp(event.getEventType());
                break;

            case DOWN:
                player.onDown(event.getEventType());
                break;

            case SPACE:
                player.onFire(event.getEventType());
                break;

            default:
                break;
        }

    }
}
