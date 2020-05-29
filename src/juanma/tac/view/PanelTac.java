package juanma.tac.view;

import juanma.tac.domain.algoritmos.Algoritmos;
import juanma.tac.domain.grafo.Graph;
import juanma.tac.utils.FileIOHelper;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;

public class PanelTac {
    private JPanel internalJPane;
    private JLabel jlbNombreFichero;
    private JButton btLoad;
    private JButton btResolEx;
    private JButton brResolAprox;
    private JButton btSave;
    private JTextArea taSolucion;
    private JButton crearGrafo;
    private JButton btKclique;
    private JTextField tfClique;
    private final JFileChooser jFileChooser;
    private Graph graphLoaded;
    private Graph graphComp;

    public PanelTac() {
        jFileChooser = new JFileChooser("./src/pruebas");
        btLoad.addActionListener(e -> {
            jFileChooser.addActionListener(e1 -> {
                if (JFileChooser.APPROVE_SELECTION.equals(e1.getActionCommand())) {
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    jlbNombreFichero.setText(path);
                    writeText("fichero elegido: " + path);
                    loadFile(path);
                    enableAll();
                }
                jFileChooser.removeActionListener(jFileChooser.getActionListeners()[0]);
            });
            jFileChooser.showDialog(null, "Abrir");
        });

        btSave.addActionListener(e -> {
            jFileChooser.addActionListener(e1 -> {
                if (graphLoaded == null) return;
                if (JFileChooser.APPROVE_SELECTION.equals(e1.getActionCommand())) {
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    writeText("Guardando grafo en elegido: " + path);
                    saveFile(path, graphLoaded);
                }
                jFileChooser.removeActionListener(jFileChooser.getActionListeners()[0]);
            });
            jFileChooser.showDialog(null, "Guardar");
        });
        crearGrafo.addActionListener(e -> {
            Completo dialog = new Completo(graph -> {
                PanelTac.this.graphLoaded = graph;
                jlbNombreFichero.setText("Grafo generado - No guardado");
                enableAll();
                printGraph(graphLoaded);

            });
            dialog.pack();
            dialog.setVisible(true);
        });
        btResolEx.addActionListener(e -> {
            boolean[] result = Algoritmos.vertexCover(graphLoaded).getArray();
            parseResult(result);
        });
        brResolAprox.addActionListener(e -> {
            boolean[] result = Algoritmos.printVertexCover(graphLoaded);
            parseResult(result);
        });

        btKclique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphComp = graphLoaded.getComplementary();
                writeText("Grafo complementario");
                printGraph(graphComp);
                int k = Integer.parseInt(tfClique.getText());
                writeText("Se puede obtener mediante de "+k+" nodos? "+Algoritmos.hasKClique(graphComp,k));
            }
        });

    }

    private void enableAll() {
        btResolEx.setEnabled(true);
        brResolAprox.setEnabled(true);
        btSave.setEnabled(true);
        crearGrafo.setEnabled(true);
        btKclique.setEnabled(true);
    }

    private void loadFile(String path) {
        FileIOHelper.loadGraphFromFile(path, g -> {
            graphLoaded = g;
            printGraph(graphLoaded);
        });
    }

    private void saveFile(String path, Graph g) {
        FileIOHelper.writeFileFromGraph(path, g);
    }


    public JPanel getInternalJPane() {
        return internalJPane;
    }

    private void writeText(String string) {
        taSolucion.append("\n" + string);
        if(taSolucion.getLineCount()>=26){
            int start = 0;
            int end = 0;
            try {
                end = taSolucion.getLineEndOffset(0);
                taSolucion.replaceRange(null, start, end);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

        }
    }

    private void parseResult(boolean[] result) {
        StringBuilder nodos = new StringBuilder("resultado : {");
        if (result.length > 0) {
            for (int i = 0; i < result.length; i++) {
                if (result[i]) {
                    nodos.append(i);
                    if (i != result.length - 1) nodos.append(",");
                }
            }
        }
        nodos.append("}");
        writeText(nodos.toString());
    }

    private void printGraph(Graph graph) {
        writeText("nodos: " + graph.getVertices());
        for (int i = 0; i < graph.getVertices(); i++) {
            StringBuilder builder = new StringBuilder("");
            for (int j = 0; j < graph.getVertices(); j++) {
                builder.append(graph.getAdjMatrix()[i][j] == Graph.POSITIVE ? "1" : "0").append(" ");
            }
            writeText(builder.toString());
        }

    }

}
