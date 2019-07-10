package RayCasting;

import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class Boundary extends Line
{
    public Boundary(double startX, double startY, double endX, double endY)
    {
        super(startX, startY, endX, endY);
        this.setStrokeWidth(2);
        this.setStroke(Color.YELLOW);
    }
    //
    public void updateLocation(double x1, double y1, double x2, double y2)
    {
        setStartX(x1);
        setStartY(y1);
        setEndX(x2);
        setEndY(y2);
    }
}
