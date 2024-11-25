package Window;

import View.UiDesign;

import javax.swing.*;
import java.awt.*;

public class DialogWindow extends JDialog {
        public DialogWindow(Window parent, UiDesign ui) {
            this.setContentPane(ui.getContent());
            this.setTitle(ui.getTitle());
            this.setVisible(true);
            this.pack();
            this.setLocationRelativeTo(parent);
        }
}
