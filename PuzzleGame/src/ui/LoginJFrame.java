package ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    public LoginJFrame() {
        this.setSize(488, 430);
        this.setVisible(true);

        this.setTitle("Puzzle Game v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
