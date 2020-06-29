package ru.lisitsyna.softwaredesign.drawgraph;

import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;
import ru.lisitsyna.softwaredesign.drawgraph.graph.Graph;
import ru.lisitsyna.softwaredesign.drawgraph.graph.ListGraph;
import ru.lisitsyna.softwaredesign.drawgraph.graph.MatrixGraph;

public class TypeGraph {
    public static GraphType graph;

    public static Graph createGraph(DrawingApi drawingApi) {
        switch (graph) {
            case LIST: return new ListGraph(drawingApi);

            case MATRIX: return new MatrixGraph(drawingApi);
        }
        return null;
    }
}
