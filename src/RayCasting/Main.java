package RayCasting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;


public class Main extends Application
{
    boolean autoMode = false;
    final String title = "RayCasting JellyBeanci(TM)";
    //
    double pX = 6.420;
    double pY = -3.6969;
    double res = 0.01;
    //
    static final int width = 900;
    static final int height = 900;
    static final int NUMBER_OF_WALLS = 10;

    public static void main(String[] args)
    {
        launch();//init window
    }

    static Random rand = new Random();
    ArrayList<Boundary> walls = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();
        Rectangle backFill = new Rectangle(width, height);
        backFill.setFill(Color.BLACK);
        backFill.setLayoutX(0);
        backFill.setLayoutY(0);
        root.getChildren().add(backFill);

        //
        //RayCast Things
        //
        //Boundary bound = new Boundary(400, 150, 400, 400);
        for (int i = 0; i < 6; i++)
        {
            int x1 = rand.nextInt(width - 100) + 50;
            int y1 = rand.nextInt(height - 100) + 50;
            int x2 = rand.nextInt(width - 100) + 50;
            int y2 = rand.nextInt(height - 100) + 50;
            walls.add(new Boundary(x1, y1, x2, y2));
        }
        //BORDERS
        walls.add(new Boundary(0, 0, width, 0));
        walls.add(new Boundary(width, 0, width, height));
        walls.add(new Boundary(width, height, 0, height));
        walls.add(new Boundary(0, height, 0, 0));
        //
        Particle p = new Particle();
        p.setWalls(walls);
        //
        root.setOnMouseDragged(e -> {
            if(!autoMode)
            {
                p.update(e.getX(), e.getY());
            }
        });
        root.setOnKeyPressed(e->{
            switch (e.getCode())
            {
                case F1:{
                    stage.setTitle(title+" mode:ON");
                    autoMode = true;
                    break;
                }
                case F2:{
                    stage.setTitle(title+" mode:OFF");
                    autoMode = false;
                    break;
                }
                default:{
                    //stage.setTitle(e.getCode().toString());
                    break;
                }
            }
        });

        Timeline autoMove = new Timeline(new KeyFrame(Duration.millis(100),e->{
            //do some stuff
            if(autoMode)
            {
                double perlinX = PerlinNoise.noise(pX,0);
                double perlinY = PerlinNoise.noise(0,pY);
                pX +=res;
                pY +=res;
                int xPos = (int) PerlinNoise.map(perlinX,-1.0,1.0,0,width);
                int yPos = (int) PerlinNoise.map(perlinY,-1.0,1.0,0,height);
                p.update(xPos,yPos);
            }
        }));
        autoMove.setRate(3);
        autoMove.setCycleCount(Timeline.INDEFINITE);
        autoMove.setAutoReverse(false);
        autoMove.play();
        for (int i = 0; i < NUMBER_OF_WALLS; i++)
        {
            root.getChildren().add(walls.get(i));
        }

        root.getChildren().addAll(p);
        //
        stage.setScene(new Scene(root, width - 10, height - 10));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }
    //END OF THE LINE @JellyBeanci
}