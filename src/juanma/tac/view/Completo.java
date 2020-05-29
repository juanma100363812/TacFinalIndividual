package juanma.tac.view;

import juanma.tac.domain.grafo.Graph;

import javax.swing.*;
import java.awt.event.*;

public class Completo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton rbGrafo;
    private JRadioButton rbTree;
    private JTextField jtParam1;
    private JTextField jtParam2;
    private JLabel jlParam1;
    private JLabel jlParam2;
    private ButtonGroup group;
    private final CreaGrafoCallback callback;

    public Completo(CreaGrafoCallback callback) {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.callback = callback;
        group = new ButtonGroup();
        group.add(rbGrafo);
        group.add(rbTree);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        rbGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlParam1.setText("Numero de nodos");
                jlParam2.setText("Probabilidad arista");
            }
        });
        rbTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlParam1.setText("Profundidad");
                jlParam2.setText("Expansion");
            }
        });
    }

    private void onOK() {
        if(group.isSelected(rbGrafo.getModel())){
            Graph graph = new Graph(Integer.parseInt(jtParam1.getText()));
            graph.addEdgesPercertage(Float.parseFloat(jtParam2.getText()));
            callback.succes(graph);
        }else {
            callback.succes(new Graph(Integer.parseInt(jtParam1.getText()), Integer.parseInt(jtParam1.getText())));
        }
        dispose();
    }

    private void onCancel() {

        dispose();
    }



    public interface CreaGrafoCallback{
        void succes(Graph graph);
    }
}
