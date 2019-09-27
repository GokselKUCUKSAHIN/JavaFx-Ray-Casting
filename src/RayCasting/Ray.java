package RayCasting;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Ray extends Line
{

    public Line trace;
    double size = 3000;
    double angle;

    //
    public Ray(double x, double y, double angle)
    {
        this.angle = angle;
        this.setStartX(x);
        this.setStartY(y);
        Point end = endPoint(x, y, angle, size);
        this.setEndX(end.getX());
        this.setEndY(end.getY());
        //
        this.trace = new Line(getStartX(), getStartY(), getEndX(), getEndY());
        //
        this.trace.setStroke(Color.WHITE);
        this.trace.setOpacity(0.7);
        this.trace.setStrokeWidth(0.3);
    }

    //
    public double getAngle()
    {
        return this.angle;
    }

    //
    private Point endPoint(double x, double y, double angle, double size)
    {
        x += size * Math.cos(angleToRadian(angle));
        y -= size * Math.sin(angleToRadian(angle));
        return new Point(x, y);
    }

    //
    private double radianToAngle(double radian)
    {
        return radian * (180 / Math.PI);
    }

    //
    private double angleToRadian(double angle)
    {
        return (Math.PI / 180.0) * angle;
    }

    //
    public Point cast(Boundary wall)
    {
        final double x1 = wall.getStartX();
        final double y1 = wall.getStartY();
        final double x2 = wall.getEndX();
        final double y2 = wall.getEndY();
        //
        final double x3 = this.getStartX();
        final double y3 = this.getStartY();
        final double x4 = this.getEndX();
        final double y4 = this.getEndY();
        //
        final double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den == 0)
        {
            return null;
        }
        //
        final double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        final double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
        if (t > 0 && t < 1 && u > 0)
        {
            final Point pt = new Point(x1 + t * (x2 - x1), y1 + t * (y2 - y1));
            return pt;
        } else
        {
            return null;
        }
    }

    //
    public void update(Point pt)
    {
        this.trace.setEndX(pt.getX());
        this.trace.setEndY(pt.getY());
    }

    //
    public void setLocation(double x, double y)
    {
        double dx = this.getStartX() - this.getEndX();
        double dy = this.getStartY() - this.getEndY();
        //
        this.setStartX(x);
        this.setStartY(y);
        //
        this.setEndX(x + dx);
        this.setEndY(y + dy);
        //
        this.trace.setStartX(this.getStartX());
        this.trace.setStartY(this.getStartY());
    }

    //
    public Point getPos()
    {
        return new Point(this.getStartX(), this.getStartY());
    }
}