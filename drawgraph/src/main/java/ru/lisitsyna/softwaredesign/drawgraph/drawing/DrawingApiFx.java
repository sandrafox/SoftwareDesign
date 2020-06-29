package ru.lisitsyna.softwaredesign.drawgraph.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DrawingApiFx implements DrawingApi {
     private GraphicsContext gc;
    private long width, height;

    public DrawingApiFx(GraphicsContext gc, int width, int height) {
        this.gc = gc;
        this.height = height;
        this.width = width;
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
        gc.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    @Override
    public void drawLine(double startX, double startY, double endX, double endY) {
        gc.strokeLine(startX, startY, endX, endY);
    }
}
