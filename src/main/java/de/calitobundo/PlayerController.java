package de.calitobundo;

import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

public interface PlayerController {

    void onLeft(EventType<KeyEvent> eventType);
    void onRight(EventType<KeyEvent> eventType);
    void onUp(EventType<KeyEvent> eventType);
    void onDown(EventType<KeyEvent> eventType);

    void onFire(EventType<KeyEvent> eventType);

}
