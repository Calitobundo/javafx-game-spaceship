package de.calitobundo.entities;

import de.calitobundo.GameApp;
import javafx.scene.canvas.GraphicsContext;


public abstract class GameItem {

    protected double time = 0;
    protected double ax = 0;
    protected double ay = 0;
    protected double vx = 0;
    protected double vy = 0;
    protected double x = GameApp.WIDTH/2;
    protected double y = 0;

    public GameItem(){

    }

    public abstract void update(double delta);

    public abstract void render(GraphicsContext gc);


    public void check(double size){

        if(x < size) {
            x = size;
        }
        if(x > GameApp.WIDTH-size) {
            x = GameApp.WIDTH-size;
        }
        if(y < size) {
            y = size;
        }
        if(y >  GameApp.HEIGHT-size) {
            y = GameApp.HEIGHT-size;
        }

    }


    public void check(){

        double size = 75;

        if(x < size) {
            x = size;
            vx *= -1;
        }
        if(x > GameApp.WIDTH-size) {
            x = GameApp.WIDTH-size;
            vx *= -1;
        }
        if(y < size) {
            y = size;
            vy *= -1;
        }
        if(y >  GameApp.HEIGHT-size) {
            y = GameApp.HEIGHT-size;
            vy *= -1;
        }

    }

}
