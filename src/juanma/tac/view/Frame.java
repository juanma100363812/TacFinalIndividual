package juanma.tac.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private PanelTac panelTac;
    public Frame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        panelTac = new PanelTac();
        super.setContentPane(panelTac.getInternalJPane());
        this.setVisible(true);
    }

}
