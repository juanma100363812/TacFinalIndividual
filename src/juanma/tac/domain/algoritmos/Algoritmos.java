package juanma.tac.domain.algoritmos;

import juanma.tac.domain.grafo.Graph;
import juanma.tac.utils.BooleanoArrayUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class Algoritmos {

    /**
     * Algoritmos exhaustivo, realizara todas las pruebas_aprox del arbol
     */
    public static BooleanoArrayUtil vertexCover(Graph g) { // 3 + 2^n * 4n^2+6n+2
        BooleanoArrayUtil camino = null;
        BooleanoArrayUtil visited = new BooleanoArrayUtil(g.getVertices());//1
        while (!visited.isFinished()) { // 2^n -> 2^n * 4n^2+6n+2
            if (verifyVortex(g, visited)) {//2 * interno -> 4n^2+6n+2
                if (camino ==null) camino = new BooleanoArrayUtil(g.getVertices(),true);
                if (visited.total()<camino.total()) //1
                    camino.setArray(visited.cloneArray()); //1 guardamos camino posible
            }
            visited.add();//1
        }
        if(camino==null)camino = new BooleanoArrayUtil(0);
        return camino;//1
    }

    /**
     * Verificador del vertex cover mediante matriz de adyacencia
     */
    private static boolean verifyVortex(Graph g, BooleanoArrayUtil visited) { //1+ n(2n+2) + n = 2n^2+3n+1
        boolean[] nodos_visited = new boolean[g.getVertices()];//1
        for (int i = 0; i < visited.getN(); i++) {//n -> n (2+2n)
            if (visited.getArray()[i]) { //1
                nodos_visited[i] = true; //1
                for (int j = 0; j < visited.getN(); j++) { //n->2n
                    if (i != j) //1
                        nodos_visited[j] = g.getAdjMatrix()[i][j] == 1;//1
                }
            }
        }
        for (boolean n : nodos_visited) {//n
            if (!n) return false;//1
        }
        return true;
    }


    /**
     * Algoritmo Vertex Cover aproximado
     * https://www.geeksforgeeks.org/vertex-cover-problem-set-1-introduction-approximate-algorithm-2/
     * adaptado desde https://www.flipkart.com/introduction-algorithms-3rd/p/itmdwxyrafdburzg?pid=9788120340077&affid=sandeepgfg
     */
    // The function to print vertex cover
    public static boolean[] printVertexCover(Graph g) { // 4 + n + 5n^2 -3n -> 5n^2-2n+4
        int vertex = g.getVertices(); //1
        // Initialize all vertices as not visited.
        boolean[] visited = new boolean[vertex]; //2

        Iterator<Integer> i; //1
        // Consider all edges one by one
        for (int u = 0; u < vertex; u++) { //n*(5n-5+2)-> 5n^2-3n
            // An edge is only picked when both visited[u]
            // and visited[v] are false
            if (!visited[u]) { //1
                // Go through all adjacents of u and pick the
                // first not yet visited vertex (We are basically
                //  picking an edge (u, v) from remaining edges.
                i = g.getAdj()[u].iterator(); //1
                while (i.hasNext()) { // (n-1)*5 -> 5n-5
                    int v = i.next(); //1
                    if (!visited[v]) { //1
                        // Add the vertices (u, v) to the result
                        // set. We make the vertex u and v visited
                        // so that all edges from/to them would
                        // be ignored
                        visited[v] = true; // 1
                        visited[u] = true; // 1
                        break;
                    }
                }
            }
        }


        return visited; //1
    }
}
