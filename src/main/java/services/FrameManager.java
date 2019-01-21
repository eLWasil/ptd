package services;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

public class FrameManager extends ApplicationFrame {

    public FrameManager(String title) {
        super(title);
    }

    public void build() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
