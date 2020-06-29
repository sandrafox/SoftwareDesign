package ru.lisitsyna.softwaredesign.drawgraph.drawing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingApiAwt implements DrawingApi {
    List<Shape> shapes;
    private int width ,height;

    public DrawingApiAwt(int width, int height) {
        this.shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(double centerX, double centerY, double radius) {
        shapes.add(new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2* radius));
    }

    @Override
    public void drawLine(double startX, double startY, double endX, double endY) {
        shapes.add(new Line2D.Double(startX, startY, endX, endY));
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
