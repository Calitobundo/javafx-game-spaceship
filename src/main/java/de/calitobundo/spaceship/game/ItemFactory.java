package de.calitobundo.spaceship.game;

import de.calitobundo.spaceship.game.item.GameItem;
import de.calitobundo.spaceship.game.items.Astroid;
import de.calitobundo.spaceship.game.items.Explosion;
import de.calitobundo.spaceship.game.items.Player;

import java.util.Random;

public class ItemFactory {


    public static void createExplosions(GameContext context, GameItem item, int count, int scale1, int scale2) {

        Random random = new Random();
        for (int i = 0; i < count; i++) {

            double randAngle = 2 * Math.PI * random.nextDouble();
            double randDistance = scale1 * item.bounds.getScaledRadius() * random.nextDouble();

            Explosion exp = new Explosion(context, scale2);
            exp.x = item.x + randDistance * Math.cos(randAngle);
            exp.y = item.y + randDistance * Math.sin(randAngle);
            context.itemsToAdd.add(exp);
        }
    }


    public static void createAstroids(GameContext context, Astroid destroyedAstroid) {

        Random random = new Random();

        final int count = 5 + random.nextInt(4);
        double angle = Math.PI * random.nextDouble();
        for (int i = 0; i < count; i++) {

            angle += 2 * Math.PI / count;
            Astroid astroid = new Astroid(context);
            astroid.bounds.scale = destroyedAstroid.bounds.scale/2;
            astroid.maxLiveTime = destroyedAstroid.maxLiveTime/1.5;
            astroid.x = destroyedAstroid.x;
            astroid.y = destroyedAstroid.y;
            astroid.vx = (50 + 100 * random.nextDouble()) * Math.cos(angle);
            astroid.vy = (50 + 100 * random.nextDouble()) * Math.sin(angle);
            context.itemsToAdd.add(astroid);
        }

    }
}
