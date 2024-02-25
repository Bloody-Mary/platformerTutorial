package PlatformerTutorial.mainCode;

import PlatformerTutorial.inputs.KeyboardInputs;
import PlatformerTutorial.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[] idleAnimation;
    private int animationTick, animationIndex, animationSpeed = 15;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        idleAnimation = new BufferedImage[5];
        for (int i = 0; i < idleAnimation.length; i++) {
            idleAnimation[i] = img.getSubimage(i * 64, 0, 64, 40);
        }
    }

    private void importImage() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        g.drawImage(idleAnimation[animationIndex], (int) xDelta, (int) yDelta, 120, 80, null);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= idleAnimation.length) {
                animationIndex = 0;
            }
        }
    }
}
