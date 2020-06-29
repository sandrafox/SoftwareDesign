package ru.lisitsyna.softwaredesign.drawgraph.drawing;

public class Circle {
    private double x;
    private double y;
    private double w;

    public Circle(double x, double y, double w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }
}
