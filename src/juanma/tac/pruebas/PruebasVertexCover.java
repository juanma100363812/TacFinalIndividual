package juanma.tac.pruebas;


import juanma.tac.domain.algoritmos.Algoritmos;
import juanma.tac.domain.grafo.Graph;
import juanma.tac.utils.FileIOHelper;
import juanma.tac.utils.TimeUtils;

import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PruebasVertexCover {

    //private final String PATH_FILE = "/content/drive/My Drive/universidad/4º año/2º cuatrimestre/TAC/final/pruebas/";
    private final String PATH_FILE = "./src/pruebas/";
    private final String COMPLETE_GRAPHS_PATH = PATH_FILE+"completos/C-";
    private final String RANDOM_GRAPHS_PATH = PATH_FILE+"aleatorios/A-";
    private final String TREE_GRAPHS_PATH = PATH_FILE+"tree/T-";

    public void init() {
//        make_files();
//        vertexCoverExhaustive();
        vertexCoverAprox();
    }


    /**
     * Pruebas algoritmo exhaustivo
     */
    private void vertexCoverExhaustive() {
        for (int i = 2; i <= 25; i += 1) {
            FileIOHelper.loadGraphFromFile(COMPLETE_GRAPHS_PATH + i, g1 -> {
                TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.vertexCover(g1));
            });
            System.out.println();
        }

        for (int i = 2; i <= 25; i += 1) {
            FileIOHelper.loadGraphFromFile(RANDOM_GRAPHS_PATH + i, g1 -> {
                TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.vertexCover(g1));
            });
        }

        for (int i = 2; i <= 4; i ++)  {
            for (int j = 1; j <= 3; j++) {
                FileIOHelper.loadGraphFromFile(TREE_GRAPHS_PATH + i+"-"+j, g1 -> {
                    TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.vertexCover(g1));
                });
            }
        }

    }


    /**
     * Pruebas algoritmo apoximado
     */
    private void vertexCoverAprox() {
        for (int i = 50; i <= 2000; i += 25) {
            FileIOHelper.loadGraphFromFile(COMPLETE_GRAPHS_PATH + i, g1 -> {
                TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.printVertexCover(g1));
            });
        }
        for (int i = 50; i <= 2000; i += 25) {
            FileIOHelper.loadGraphFromFile(RANDOM_GRAPHS_PATH + i, g1 -> {
                TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.printVertexCover(g1));
            });
        }
        for (int i = 5; i < 9; i ++) {
            for (int j = 1; j <= 3; j++) {
                FileIOHelper.loadGraphFromFile(TREE_GRAPHS_PATH  + i + "-" + j, g1 -> {
                    TimeUtils.timeElapsed(g1.getVertices(), () -> Algoritmos.printVertexCover(g1));
                });
            }
        }

    }


    /***
     * Generador de archivos de pruebas
     */
    private void make_files() {
        // complete graphs
        for (int i = 2; i < 50; i += 1) {
            Graph graph = new Graph(i);
            graph.addEdgesPercertage(1);
            FileIOHelper.writeFileFromGraph(COMPLETE_GRAPHS_PATH + i, graph);
        }
        for (int i = 50; i <= 2000; i += 25) {
            Graph graph = new Graph(i);
            graph.addEdgesPercertage(1);
            FileIOHelper.writeFileFromGraph(COMPLETE_GRAPHS_PATH + i, graph);
        }
        // random graphs ~ 20% edge probability
        for (int i = 2; i < 50; i += 1) {
            Graph graph = new Graph(i);
            graph.addEdgesPercertage(0.2f);
            FileIOHelper.writeFileFromGraph(RANDOM_GRAPHS_PATH + i, graph);
        }
        for (int i = 50; i <= 2000; i += 25) {
            Graph graph = new Graph(i);
            graph.addEdgesPercertage(0.2f);
            FileIOHelper.writeFileFromGraph(RANDOM_GRAPHS_PATH + i, graph);
        }
//         trees (1-3 node expansion)
        for (int i = 2; i < 9; i ++) {
            for (int j = 1; j <= 3; j++) {
                Graph graph = new Graph(i, j);
                FileIOHelper.writeFileFromGraph(TREE_GRAPHS_PATH + i+"-"+j, graph);
            }

        }
    }
}


