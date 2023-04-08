import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlightSimulator extends JFrame {
    
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    
    private final int PLANE_WIDTH = 60;
    private final int PLANE_HEIGHT = 60;
    private int planeX = 400;
    private int planeY = 300;
    
    private final int OBSTACLE_WIDTH = 40;
    private final int OBSTACLE_HEIGHT = 40;
    private int obstacleX = 700;
    private int obstacleY = 200;
    
    private int score = 0;
    
    private Timer timer;
    
    public FlightSimulator() {
        setTitle("Flight Simulator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Draw the plane
                g.setColor(Color.RED.yellow);
                g.fillRect(planeX, planeY, PLANE_WIDTH, PLANE_HEIGHT);
                
                // Draw the obstacle
                g.setColor(Color.BLUE);
                g.fillRect(obstacleX, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
                
                // Draw the score
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                g.drawString("Score: " + score, 20, 30);
            }
        };
        add(panel);
        
        // Move the obstacle every 500ms
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obstacleX -= 50;
                if (obstacleX < -OBSTACLE_WIDTH) {
                    obstacleX = FRAME_WIDTH;
                    obstacleY = (int) (Math.random() * (FRAME_HEIGHT - OBSTACLE_HEIGHT));
                    score++;
                }
                panel.repaint();
                
                // Check for collision
                if (planeX + PLANE_WIDTH > obstacleX && planeX < obstacleX + OBSTACLE_WIDTH &&
                    planeY + PLANE_HEIGHT > obstacleY && planeY < obstacleY + OBSTACLE_HEIGHT) {
                    timer.stop();
                    JOptionPane.showMessageDialog(FlightSimulator.this, "You crashed! Your score was " + score + ".");
                    dispose();
                }
            }
        });
        timer.start();
        
        // Move the plane with arrow keys
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        panel.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planeX -= 20;
                if (planeX < 0) {
                    planeX = 0;
                }
                panel.repaint();
            }
        });
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        panel.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planeX += 20;
                if (planeX > FRAME_WIDTH - PLANE_WIDTH) {
                    planeX = FRAME_WIDTH - PLANE_WIDTH;
                }
                panel.repaint();
            }
        });
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        panel.getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planeY -= 20;
                if (planeY < 0            ) {
                    planeY = 0;
                }
                panel.repaint();
            }
        });
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        panel.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planeY += 20;
                if (planeY > FRAME_HEIGHT - PLANE_HEIGHT) {
                    planeY = FRAME_HEIGHT - PLANE_HEIGHT;
                }
                panel.repaint();
            }
        });
        
        // Start the game
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightSimulator();
    }
}
//In this game, the objective is to avoid obstacles while collecting points. 
//The player controls a red plane using arrow keys, and must dodge blue obstacles that move from right to left across the screen. 
//Each time an obstacle passes the left edge of the screen, the player earns a point. 
//If the player collides with an obstacle, the game ends and the score is displayed in a dialog box.



