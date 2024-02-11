package PlatformerTutorial;

import javax.swing.*;

public class GameWindow{
    private JFrame jFrame;

    public GameWindow() {

        jFrame = new JFrame();
        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
    }

}
