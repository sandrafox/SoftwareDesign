package ru.lisitsyna.softwaredesign.graph.draw;

public interface DrawingApi {
    long getDrawingAreaWidth();

    long getDrawingAreaHeight();

    void drawCircle(double centerX, double centerY, double radius);

    void drawLine(double startX, double startY, double endX, double endY);
}
