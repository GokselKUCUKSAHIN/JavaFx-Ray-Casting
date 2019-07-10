package RayCasting;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Particle extends Pane
{
    ArrayList<Boundary> walls;
    Circle center;
    ArrayList<Ray> rays = new ArrayList<>();
    //
    public Particle()
    {
        this.setPrefSize(600, 600);
        this.setLayoutX(0);
        this.setLayoutY(0);
        //
        center = new Circle(this.getPrefWidth() / 2, this.getPrefHeight() / 2, 3);
        center.setFill(Color.PINK);
        rays.clear();

        for (double i = 0; i < 360; i += 0.1)
        {
            rays.add(new Ray(center.getCenterX(), center.getCenterY(), i));
        }
        show();
    }
    //
    public void show()
    {
        getChildren().add(center);
        for (Ray ray : rays)
        {
            getChildren().add(ray.trace);
        }
    }
    //
    public void setWalls(ArrayList<Boundary> walls)
    {
        this.walls = walls;
    }
    //
    public void look()
    {
        for (Ray ray : rays)
        {
            Point closest = null;
            double record = 10000;
            for (Boundary wall : walls)
            {
                final Point pt = ray.cast(wall);
                if (pt != null)
                {
                    double d = Point.dist(ray.getPos(), pt);
                    if (d < record)
                    {
                        record = d;
                        closest = pt;
                    }
                }
            }
            if (closest != null)
            {
                ray.update(closest);
            }
        }
    }
    //
    public void update(double x, double y)
    {
        center.setCenterX(x);
        center.setCenterY(y);
        for (Ray ray : rays)
        {
            ray.setLocation(x, y);
        }
        look();
    }
}