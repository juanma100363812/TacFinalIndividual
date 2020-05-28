package juanma.tac.domain.grafo;

import java.util.LinkedList;
import java.util.Random;

import static com.sun.deploy.panel.TreeBuilder.createTree;

// simple class to construct undirected graphs
public class Graph {
    private int vertices;   // No. of vertices
    private LinkedList<Integer>[] adj; //Adjacency List
    private char[][] adjMatrix;
    private Random ran;
    private int treeNodeNumber=0;

    // Constructor simple clean graph
    public Graph(int vertices) {
        this.vertices = vertices;
        adjMatrix = new char[vertices][vertices];
        adj = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adj[i] = new LinkedList();
    }

    // Constructo from adjacency matrix
    public Graph(char[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
        this.vertices = adjMatrix.length;
        adj = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adj[i] = new LinkedList();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                addEdge(i, j);
            }
        }
    }

    public Graph(int numDepth, int expansion) {
        int numNodos =1;
        for (int i = 1; i <numDepth ; i++) {
            numNodos+= Math.pow(expansion, i);
        }
        adj = new LinkedList[numNodos];
        adj[0]= new LinkedList<>();//root node
        this.vertices = adj.length;
        this.adjMatrix = new char[vertices][vertices];
        createTree(numDepth-1, expansion);
    }

    private void createTree( int depth, int expansion) {
        if (depth <= 0) return;
        for (int i = 0; i < expansion; i++) {
           adj[++treeNodeNumber] = new LinkedList<>();
            addEdge(treeNodeNumber-1,treeNodeNumber);
            createTree(depth-1,expansion);
        }
    }


    public int getVertices() {
        return vertices;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        adjMatrix[v][w] = 1;
        adjMatrix[w][v] = 1;
    }

    // Function to generate edges by percentage
    public void addEdgesPercertage(float perc) {
        ran = new Random();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (ran.nextFloat() <= perc || perc == 1.0f)
                    addEdge(i, j);
            }
            //verify all is conected to other nodes, if not generate at least 1 random
            if (adj[i].isEmpty()){
                int j = 0;
                while ((j=ran.nextInt(vertices))== i);
                addEdge(i, j);
            }
        }

    }

    public char[][] getAdjMatrix() {
        return adjMatrix;
    }


}