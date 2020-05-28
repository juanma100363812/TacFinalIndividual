package juanma.tac.domain.grafo;

public class GraphUtils {

    /**
     * Algoritmo obtiene grafo complementario desde un grafo origin
     */
    public static Graph getComplementaryGraph(Graph origin_graph){
        int n_ver = origin_graph.getVertices();
        char[][] _matrix = new char[n_ver][n_ver];
        char[][] origin_matrix = origin_graph.getAdjMatrix();
        for (int i = 0; i < origin_matrix.length; i++) {
            for (int j = 0; j < origin_matrix[i].length; j++) {
                _matrix[i][j] = origin_matrix[i][j]=='1'?'0':'1';
            }
        }
        return new Graph(_matrix);
    }

    /**
     * Algoritmo comprobacion de si es arbol
     * https://www.geeksforgeeks.org/check-given-graph-tree/
     */
    // Returns true if the graph is a tree, else false.
    public static Boolean isTree(Graph graph) {
        int num_vertex = graph.getVertices();
        // Mark all the vertices as not visited and not part
        // of recursion stack
        Boolean[] visited = new Boolean[num_vertex];
        for (int i = 0; i < num_vertex; i++)
            visited[i] = false;

        // The call to isCyclicUtil serves multiple purposes
        // It returns true if graph reachable from vertex 0
        // is cyclcic. It also marks all vertices reachable
        // from 0.
        if (isCyclicUtil(0, visited, -1, graph))
            return false;

        // If we find a vertex which is not reachable from 0
        // (not marked by isCyclicUtil(), then we return false
        for (int u = 0; u < num_vertex; u++)
            if (!visited[u])
                return false;

        return true;
    }
    /**
     * Algoritmo comprobacion de ciclos, usado para revisar si es un arbol
     * https://www.geeksforgeeks.org/check-given-graph-tree/
     */
    // A recursive function that uses visited[] and parent
    // to detect cycle in subgraph reachable from vertex v.
    private static Boolean isCyclicUtil(int v, Boolean[] visited, int parent, Graph graph) {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        for (Integer integer : graph.getAdj()[v]) {
            i = integer;

            // If an adjacent is not visited, then recur for
            // that adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v,graph))
                    return true;
            }

            // If an adjacent is visited and not parent of
            // current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }


}
