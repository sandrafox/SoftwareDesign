package ru.lisitsyna.softwaredesign.drawgraph;

import ru.lisitsyna.softwaredesign.drawgraph.application.AwtApplication;
import ru.lisitsyna.softwaredesign.drawgraph.application.FxApplication;
import ru.lisitsyna.softwaredesign.drawgraph.application.GraphApplication;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApiAwt;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApiFx;
import ru.lisitsyna.softwaredesign.drawgraph.graph.Graph;
import ru.lisitsyna.softwaredesign.drawgraph.graph.ListGraph;
import ru.lisitsyna.softwaredesign.drawgraph.graph.MatrixGraph;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        if (args.length != 2)
            throw new IllegalArgumentException("Amount of arguments must be 2 first - using api, " +
                    "second - way of presentation graph");
        GraphApplication graphApplication;
        if (args[1].equals("matrix")) {
            TypeGraph.graph = GraphType.MATRIX;
        } else if (args[1].equals("list")) {
            TypeGraph.graph = GraphType.LIST;
        } else {
            throw new IllegalArgumentException("Second argument must be \"matrix\" or \"list\"");
        }
        if (args[0].equals("javafx")) {
            graphApplication = new FxApplication();
        } else if (args[0].equals("javaawt")) {
            graphApplication = new AwtApplication();
        } else {
            throw new IllegalArgumentException("First argumet must be \"javafx\" or \"javaawt\"");
        }
        graphApplication.startApplication();
    }
}
