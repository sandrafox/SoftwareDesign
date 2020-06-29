package ru.lisitsyna.softwaredesign.graph.graphs;

import ru.lisitsyna.softwaredesign.graph.draw.DrawingApi;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {
    private DrawingApi drawingApi;
    private Map<Integer, Map.Entry<Double, Double>> vertexCoord = new HashMap<Integer, Map.Entry<Double, Double>>();
    private double vertexRadius = 10, radius, centerX, centerY, corner, deltacorner;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void drawGraph();

    private void drawVertex(int number) {
        if (!vertexCoord.containsKey(number)) {
            double x = centerX + radius * Math.cos(corner), y = centerY + radius * Math.sin(corner);
            drawingApi.drawCircle(x, y, vertexRadius);
            vertexCoord.put(number, new AbstractMap.SimpleEntry<Double, Double>(x, y));
        }
    }
}
