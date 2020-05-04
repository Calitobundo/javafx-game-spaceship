package de.calitobundo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApp extends Application {

    public static final double WIDTH = 600;
    public static final double HEIGHT = 1000;

    private GameContext context;
    private GameContext context2;
    private Canvas canvas = new Canvas(WIDTH, HEIGHT);

    public static void main(String[] args) {
        launch(args);
    }
//trdswsw
    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();
        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Canvas Test");
        stage.setScene(scene);
        stage.centerOnScreen();
        //stage.setX(1920+200);
        //stage.setY(100);
        stage.show();

        canvas.requestFocus();
        context = new GameContext(canvas);
        context.start();

    }

}
