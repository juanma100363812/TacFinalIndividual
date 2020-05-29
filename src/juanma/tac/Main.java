package juanma.tac;

import juanma.tac.domain.algoritmos.Algoritmos;
import juanma.tac.domain.grafo.Graph;
import juanma.tac.pruebas.PruebasVertexCover;
import juanma.tac.view.Frame;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // only file automatic probes
       //new PruebasVertexCover().init();
        // using GUI format
        JFrame frame = new Frame("Juan Manuel Torres *TAC-Final 2020");


        // only for collab use
        for (int i = 0; i < args.length; i++) {
            int alg = parseAlgorith(args[i++]);
            int g = parseGraph(args[i++]);
            Graph graph = null;
            boolean[] result =null;
            switch (g){
                case 1:
                    graph = new Graph(Integer.parseInt(args[i++]));
                    graph.addEdgesPercertage(1);
                    break;
                case 2:
                    graph = new Graph(Integer.parseInt(args[i++]));
                    graph.addEdgesPercertage(Float.parseFloat(args[i++]));
                    break;
                case 3:
                    graph = new Graph(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
                    break;
                default:
                    System.out.println("command error");
                    System.exit(-1);
            }
            switch (alg){
                case 1:
                    result = Algoritmos.vertexCover(graph).getArray();
                   break;
                case 2:
                    result = Algoritmos.printVertexCover(graph);
                    break;
                case 3:
                    int k_clique = i++;
                    boolean tiene = Algoritmos.hasKClique(graph, Integer.parseInt(args[k_clique]));
                    System.out.println("tiene k-clique: "+tiene);
                    System.exit(0);
                    break;
                default:
                    System.out.println("command error");
                    System.exit(-1);
            }
            parseResult(graph,result);
        }
    }

    private static void parseResult(Graph graph, boolean[] result) {
        System.out.println("grafo de "+graph.getVertices()+" vertices:");
        char [][]matriz= graph.getAdjMatrix();
        for (char[] line :matriz ) {
            for (char n: line) {
                System.out.print(n==1?'1':'0');
            }
            System.out.println();
        }

        StringBuilder nodos = new StringBuilder("resultado : {");
        if (result.length>0) {
            for (int i = 0; i < result.length; i++) {
                if (result[i]) {
                    nodos.append(i);
                    if (i != result.length - 1) nodos.append(",");
                }
            }
        }
        nodos.append("}");
        System.out.println(nodos.toString());
    }

    public static int parseAlgorith(String parseString) {
        if (parseString.equalsIgnoreCase("exha")) return 1;
        if (parseString.equalsIgnoreCase("apro")) return 2;
        if (parseString.equalsIgnoreCase("cliq")) return 3;
        return -1;
    }

    public static int parseGraph(String parseString) {
        if (parseString.equalsIgnoreCase("comp")) return 1;
        if (parseString.equalsIgnoreCase("ale")) return 2;
        if (parseString.equalsIgnoreCase("tree")) return 3;
        return -1;
    }

}
