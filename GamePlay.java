import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private int[] snakeXLength = new int[750];
	private int[] snakeYLength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon rigthMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;

	private int lengthOfSnake = 3;

	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;

	private int[] enemyXPos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };

	private int[] enemyYPos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	private ImageIcon enemyImage;

	private Random random = new Random();

	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);

	private int score = 0;

	private int moves = 0;

	private ImageIcon titleImage;

	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		if (moves == 0) {
			snakeXLength[0] = 100;
			snakeXLength[1] = 75;
			snakeXLength[2] = 50;

			snakeYLength[0] = 100;
			snakeYLength[1] = 100;
			snakeYLength[2] = 100;
		}
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		// draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		// draw border for gamePlay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// draw background for the gamePlay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);

		// draw the score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);

		rigthMouth = new ImageIcon("rightmouth.png");
		rigthMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

		for (int i = 0; i < lengthOfSnake; i++) {
			if (i == 0 && right) {
				rigthMouth = new ImageIcon("rightmouth.png");
				rigthMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i == 0 && down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}

			if (i != 0) {
				rigthMouth = new ImageIcon("snakeimage.png");
				rigthMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);

			}
		}

		enemyImage = new ImageIcon("enemy.png");

		if (enemyXPos[xPos] == snakeXLength[0] && enemyYPos[yPos] == snakeYLength[0]) {
			score++;
			lengthOfSnake++;
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}

		enemyImage.paintIcon(this, g, enemyXPos[xPos], enemyYPos[yPos]);

		for (int i = 1; i < lengthOfSnake; i++) {
			if (snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0]) {
				right = false;
				left = false;
				down = false;
				up = false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space for Restart", 350, 340);
			}
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		moves = 1;
		if (right) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeYLength[i + 1] = snakeYLength[i];
			}

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeXLength[i] = snakeXLength[i] + 25;
				} else {
					snakeXLength[i] = snakeXLength[i - 1];
				}
				if (snakeXLength[i] > 850) {
					snakeXLength[i] = 25;
				}

			}
			removeAll();
			revalidate();
			repaint();
		}

		if (left) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeYLength[i + 1] = snakeYLength[i];
			}

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeXLength[i] = snakeXLength[i] - 25;
				} else {
					snakeXLength[i] = snakeXLength[i - 1];
				}
				if (snakeXLength[i] < 25) {
					snakeXLength[i] = 850;
				}

			}
			revalidate();
			repaint();
		}

		if (down) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeXLength[i + 1] = snakeXLength[i];
			}

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeYLength[i] = snakeYLength[i] + 25;
				} else {
					snakeYLength[i] = snakeYLength[i - 1];
				}
				if (snakeYLength[i] > 625) {
					snakeYLength[i] = 75;
				}

			}
			revalidate();
			repaint();
		}

		if (up) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeXLength[i + 1] = snakeXLength[i];
			}

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeYLength[i] = snakeYLength[i] - 25;
				} else {
					snakeYLength[i] = snakeYLength[i - 1];
				}
				if (snakeYLength[i] < 75) {
					snakeYLength[i] = 625;
				}

			}
			revalidate();
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}

			up = false;
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}

			up = false;
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}

			left = false;
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}

			left = false;
			right = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
