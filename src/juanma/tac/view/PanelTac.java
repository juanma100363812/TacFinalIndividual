package juanma.tac.view;

import juanma.tac.domain.algoritmos.Algoritmos;
import juanma.tac.domain.grafo.Graph;
import juanma.tac.utils.BooleanoArrayUtil;
import juanma.tac.utils.FileIOHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PanelTac {
    private JPanel internalJPane;
    private JLabel jlbNombreFichero;
    private JButton btLoad;
    private JButton btResolEx;
    private JButton brResolAprox;
    private JButton btSave;
    private JTextArea taSolucion;
    private JButton crearGrafo;
    private JFileChooser jFileChooser;
    private File file;
    Graph graphLoaded;

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
        crearGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btResolEx.addActionListener(e -> {
            boolean[] result = Algoritmos.vertexCover(graphLoaded).getArray();
            parseResult(result);
        });
        brResolAprox.addActionListener(e -> {
            boolean[] result = Algoritmos.printVertexCover(graphLoaded);
            parseResult(result);
        });
    }

    private void enableAll() {
        btResolEx.setEnabled(true);
        brResolAprox.setEnabled(true);
        btSave.setEnabled(true);
        crearGrafo.setEnabled(true);
    }

    private void loadFile(String path) {
        FileIOHelper.loadGraphFromFile(path, g -> graphLoaded = g);
    }

    private void saveFile(String path, Graph g) {
        FileIOHelper.writeFileFromGraph(path, g);
    }


    public JPanel getInternalJPane() {
        return internalJPane;
    }

    private void writeText(String string) {
        taSolucion.append("\n" + string);
    }

    private void parseResult(boolean result[]) {
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
        writeText(nodos.toString());
    }
}
