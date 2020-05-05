package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Player;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

public class GameItemEventHandler implements EventHandler<KeyEvent> {

    private GameItem item;



    public GameItemEventHandler(GameItem item) {
        this.item = item;
    }


    public void handle(KeyEvent event) {

        Player player;
        if(item instanceof Player) {
            player = (Player) item;
        }else{
            return;
        }
        EventType<KeyEvent> eventType = event.getEventType();
        switch (event.getCode()) {
            case RIGHT:
                player.onRight(eventType);
                break;

            case LEFT:
                player.onLeft(eventType);
                break;

            case UP:
                player.onUp(eventType);
                break;

            case DOWN:
                player.onDown(eventType);
                break;

            case SPACE:
                player.onFire(eventType);
                break;

            case D:
                if(event.getEventType() == KeyEvent.KEY_PRESSED)
                    item.context.debug = !item.context.debug;
                break;

            default:
                break;
        }

        event.consume();
    }


}
