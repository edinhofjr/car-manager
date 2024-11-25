package Window;

import View.UIHome;
import View.UiDesign;
import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(UiDesign ui) {
        this.setContentPane(ui.getContent());
        this.setTitle(ui.getTitle());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(((UIHome) ui).getMenuBar());
        this.pack();
    }
}
