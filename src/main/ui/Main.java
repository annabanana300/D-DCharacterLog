package ui;

import javax.swing.SwingUtilities;
import ui.gui.GUI;

//runs a new instance of the application
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
