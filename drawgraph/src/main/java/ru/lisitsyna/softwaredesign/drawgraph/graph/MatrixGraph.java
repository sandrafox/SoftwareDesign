package ru.lisitsyna.softwaredesign.drawgraph.graph;

import ru.lisitsyna.softwaredesign.drawgraph.drawing.DrawingApi;

import java.util.Scanner;

public class MatrixGraph extends Graph {
    private boolean[][] matrix;
    private int countOfVertex;

    public MatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    @Override
    public void getGraph() {
        System.out.print("Please, input count of vertex: ");
        Scanner in = new Scanner(System.in);
        countOfVertex = in.nextInt();
        matrix = new boolean[countOfVertex][countOfVertex];
        System.out.println("Please, input the adjacency matrix:");
        for (int i = 0; i < countOfVertex; i++) {
            for (int j = 0; j < countOfVertex; j++) {
                matrix[i][j] = in.nextInt() == 1;
            }
        }
    }

    @Override
    public void drawGraph() {
        init(countOfVertex);
        for (int i = 0; i < countOfVertex - 1; i++) {
            for (int j = i + 1; j < countOfVertex; j++) {
                if (matrix[i][j]) drawEdge(i, j);
            }
        }
    }
}
