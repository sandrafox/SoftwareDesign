package ru.lisitsyna.softwaredesign.drawgraph.application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.lisitsyna.softwaredesign.drawgraph.TypeGraph;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;
import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApiFx;
import ru.lisitsyna.softwaredesign.drawgraph.graph.Graph;

public class FxApplication extends Application implements GraphApplication {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing circle");
        Group root = new Group();
        int width = 600, height = 600;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        DrawingApi drawingApi = new DrawingApiFx(gc, width, height);
        Graph graph = TypeGraph.createGraph(drawingApi);
        graph.getGraph();
        graph.drawGraph();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, Color.WHITE));
        primaryStage.show();
    }

    public void startApplication() {
        Application.launch(this.getClass());
    }
}
