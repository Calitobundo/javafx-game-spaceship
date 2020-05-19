package de.calitobundo.spaceship.game.items2;


import javafx.scene.canvas.GraphicsContext;

//render
//collision
//bonus
//attack
public class Monster implements Renderable, Updatable, Collidable, Destroyable, Bonusable {


    @Override
    public void render(GraphicsContext gc) {

    }


    @Override
    public void update(double delta) {

    }

    @Override
    public void collide(Collidable collidable) {

    }


    @Override
    public void destroy() {

    }

    @Override
    public String bonus() {
        return "50 Points";
    }
}
