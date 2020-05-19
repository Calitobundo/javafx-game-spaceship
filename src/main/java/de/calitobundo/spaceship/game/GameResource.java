package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.items.Astroid;
import de.calitobundo.spaceship.game.items.Explosion;
import de.calitobundo.spaceship.game.items.Player;
import de.calitobundo.spaceship.game.items.Rocket;

import java.util.HashMap;
import java.util.Map;

public final class GameResource {

    public static Map<Class, GameItemImage> map = new HashMap<>();

    static {

        map.put(Astroid.class, new GameItemImage("images/astdroid/gold/steinGold", ".png", 48, 50, 50));
        map.put(Rocket.class, new GameItemImage("images/rocket/rakete", ".png", 128, 100, 100));
        map.put(Explosion.class, new GameItemImage("images/explosion/testbackhole", ".png", 24, 100, 100));

        map.put(Player.class, new GameItemImage("images/ship.png",  150, 150));
        map.put(GameContext.class, new GameItemImage("images/background2.png", 600, 1000));



    }

    public static GameItemImage getItemImage(Class <?> clazz){
        return map.get(clazz);

    }
}
