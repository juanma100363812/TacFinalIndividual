package juanma.tac.utils;

import juanma.tac.domain.grafo.Graph;

import java.io.*;

public class FileIOHelper {


    public static void loadGraphFromFile(String pathFile, IOHelperCallback callback) {

            char[][] matrix = null;
            File file = new File(pathFile);
            try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
                int n = Integer.parseInt(fr.readLine());
                matrix = new char[n][n];
                String linea;
                int i = 0;
                while ((linea = fr.readLine()) != null) {
                    matrix[i++] = linea.toCharArray();
                }
            } catch (IOException e) {
                System.out.println("error ->"+file.getAbsolutePath());
            } finally {
                if (matrix != null)
                    callback.success(new Graph(matrix));
            }



    }

    public static void writeFileFromGraph(String pathFile, Graph g) {
        new Thread(() -> {
            try (FileWriter fw = new FileWriter(new File(pathFile))) {
                fw.append(g.getVertices() + "\n");
                char[][] matrix = g.getAdjMatrix();
                for (char[] l : matrix) {
                    for (int letter : l) {
                        fw.append("" + letter);
                    }
                    fw.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public interface IOHelperCallback {
        void success(Graph g);
    }
}
