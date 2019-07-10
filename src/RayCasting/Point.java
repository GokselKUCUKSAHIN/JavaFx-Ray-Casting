package RayCasting;

public class Point
{
    double x;
    double y;
    //
    public Point()
    {
    }
    //
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    //
    public double getX()
    {
        return x;
    }
    //
    public void setX(double x)
    {
        this.x = x;
    }
    //
    public double getY()
    {
        return y;
    }
    //
    public void setY(double y)
    {
        this.y = y;
    }
    //
    public void setPoint(double x, double y)
    {
        setX(x);
        setY(y);
    }
    //
    @Override
    public String toString()
    {
        return String.format("%3.1f: %3.1f", x, y);
    }
    //
    public static double dist(Point a, Point b)
    {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}