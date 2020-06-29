package ru.lisitsyna.softwaredesign.drawgraph.graph;

import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Graph {
    private DrawingApi drawingApi;
    private Map<Integer, Map.Entry<Double, Double>> vertexCoord = new HashMap<>();
    private double vertexRadius = 10, radius, centerX, centerY, corner, deltaCorner;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void getGraph();

    public abstract void drawGraph();

    protected void init(int countVertex) {
        long width = drawingApi.getDrawingAreaWidth();
        long height = drawingApi.getDrawingAreaHeight();
        radius = ((double) Math.min(width, height)) / 2.0 - 20;
        centerX = (double) width / 2;
        centerY = (double) height / 2;
        deltaCorner = 2 * Math.PI / countVertex;
    }

    private void drawVertex(int number) {
        if (!vertexCoord.containsKey(number)) {
            double x = centerX + radius * Math.cos(corner), y = centerY + radius * Math.sin(corner);
            drawingApi.drawCircle(x, y, vertexRadius);
            vertexCoord.put(number, new AbstractMap.SimpleEntry<>(x, y));
            corner += deltaCorner;
        }
    }

    private void connectVertex(int number1, int number2) {
        Map.Entry<Double, Double> start = vertexCoord.get(number1), end = vertexCoord.get(number2);
        drawingApi.drawLine(start.getKey(), start.getValue(), end.getKey(), end.getValue());
    }

    protected void drawEdge(int number1, int number2) {
        drawVertex(number1);
        drawVertex(number2);
        connectVertex(number1, number2);
    }
}
