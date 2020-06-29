package ru.lisitsyna.softwaredesign.drawgraph.application;

import ru.lisitsyna.softwaredesign.drawgraph.TypeGraph;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApiAwt;
import ru.lisitsyna.softwaredesign.drawgraph.graph.Graph;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

public class AwtApplication  implements GraphApplication {
    private int width = 600, height = 600;
    private List<Shape> shapes;

    @Override
    public void startApplication() {
        DrawingApiAwt drawingApi = new DrawingApiAwt(width, height);
        Graph graph = TypeGraph.createGraph(drawingApi);
        graph.getGraph();
        graph.drawGraph();
        shapes = drawingApi.getShapes();
        Frame frame = new AwtFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    class AwtFrame extends Frame {

        public void paint(Graphics g) {
            Graphics2D ga = (Graphics2D) g;
            ga.setPaint(Color.green);
            for (Shape shape : shapes) {
                ga.draw(shape);
            }
        }
    }
}
