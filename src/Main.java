import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel implements KeyListener {

    private int x = 50, y = 50;
    private int width = 50, height = 50;
    private Image floorImage;
    private Image playerImage;

    public Main() {
        try {
            floorImage = ImageIO.read(new File("floor.png"));
            playerImage = ImageIO.read(new File("player.png"));

            
            if (floorImage == null || playerImage == null) {
                System.out.println("One or boths images don't loads correct.");
            } else {
                System.out.println("Images correct loads");
            }
        } catch (IOException e) {
            System.out.println("Error load: " + e.getMessage());
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(800, 600));
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        for (int i = 0; i < getWidth(); i += floorImage.getWidth(null)) {
            for (int j = 0; j < getHeight(); j += floorImage.getHeight(null)) {
                g.drawImage(floorImage, i, j, this);
            }
        }

        
        g.drawImage(playerImage, x, y, width, height, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            x -= 10;
        }
        if (key == KeyEvent.VK_RIGHT) {
            x += 10;
        }
        if (key == KeyEvent.VK_UP) {
            y -= 10;
        }
        if (key == KeyEvent.VK_DOWN) {
            y += 10;
        }

        
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > getWidth() - width) x = getWidth() - width;
        if (y > getHeight() - height) y = getHeight() - height;

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Example");
        Main game = new Main();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}