import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
	
	private static final long serialVersionUID = 1L;
	private int[] snake_x = new int[1000];
	private int[] snake_y = new int[1000];

	private int [] rand_list_x = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675
			, 700, 750, 775, 800, 825, 850}; 
	private int [] rand_list_y = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625}; 
	
	Random rand = new Random();
	
	int	rand_x = rand.nextInt(34);
	int	rand_y = rand.nextInt(23);
 	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	private int score = 0;
	
	private int m = 0;
	
	private ImageIcon rightm;
	private ImageIcon leftm;
	private ImageIcon upm;
	private ImageIcon downm;
	private ImageIcon tail;
	private ImageIcon enemy;
	
	private int len_snake = 3;
	
	private Timer timer;
	private int delay;
	
	private ImageIcon title;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		if(len_snake == 3)
			delay = 100;
		
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		if (m == 0) {
			right = true;
			snake_x[0] = 200;
			snake_x[1] = 175;
			snake_x[2] = 150;
			snake_y[0] = 200;
			snake_y[1] = 200;
			snake_y[2] = 200;
		}
		
		
		g.setColor(Color.white);
		g.drawRect(25, 10, 760, 50);
		
		title = new ImageIcon("title.png");
		title.paintIcon(this, g, 26, 11);
		
		g.setColor(Color.white);
		g.drawRect(800, 10, 75, 50);
		
		g.setColor(Color.blue);
		g.fillRect(801, 11, 74, 49);
		
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 576);
		
		g.setColor(Color.green);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 805, 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " + len_snake, 805, 50);
		
		for(int i = 0; i < len_snake; i++) {
			if(i == 0 && right) {
				rightm = new ImageIcon("rightm.png");
				rightm.paintIcon(this, g, snake_x[i], snake_y[i]);
			}
			if(i == 0 && left) {
				leftm = new ImageIcon("leftm.png");
				leftm.paintIcon(this, g, snake_x[i], snake_y[i]);
			}
			if(i == 0 && up) {
				upm = new ImageIcon("upm.png");
				upm.paintIcon(this, g, snake_x[i], snake_y[i]);
			}
			if(i == 0 && down) {
				downm = new ImageIcon("downm.png");
				downm.paintIcon(this, g, snake_x[i], snake_y[i]);
			}
			if(i != 0) {
				tail = new ImageIcon("tail.png");
				tail.paintIcon(this, g, snake_x[i], snake_y[i]);
			}
		}
		
		enemy = new ImageIcon("enemy.png");
		
		if((rand_list_x[rand_x] == snake_x[0]) && (rand_list_y[rand_y] == snake_y[0])) {
			len_snake ++;
			score = score + 5;
			if(score % 100 == 1)
				delay = delay - 5;
			rand_x = rand.nextInt(33);
			rand_y = rand.nextInt(22);
			for(int i = 0; i < len_snake; i++) {
				if(snake_x[i] == rand_list_x[rand_x] && snake_y[i] == rand_list_y[rand_y]) {
					rand_x = rand.nextInt(33);
					rand_y = rand.nextInt(22);
				}
			}
		}
		enemy.paintIcon(this, g, rand_list_x[rand_x], rand_list_y[rand_y]);
		
		for(int i = 1; i < len_snake; i++)
		{
			if(snake_x[i] == snake_x[0] && snake_y[i] == snake_y[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 14));
				g.drawString("Game Over!", 400, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 14));
				g.drawString("Press SPACE to restart!", 400, 330);
				
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int x = len_snake - 1; x >= 0; x--) {
				snake_y[x+1] = snake_y[x];
			}
			for(int x = len_snake; x >= 0; x--) {
				if(x == 0)
					snake_x[x] = snake_x[x] + 25;
				else
					snake_x[x] = snake_x[x-1];
				
				if(snake_x[x] > 850) {
					snake_x[x] = 25;
				}	
			}
			repaint();
		}
		if(left) {
			for(int x = len_snake - 1; x >= 0; x--) {
				snake_y[x+1] = snake_y[x];
			}
			for(int x = len_snake; x >= 0; x--) {
				if(x == 0)
					snake_x[x] = snake_x[x] - 25;
				else
					snake_x[x] = snake_x[x-1];
				
				if(snake_x[x] < 25) {
					snake_x[x] = 850;
				}	
			}
			repaint();
		}
		if(up) {
			for(int x = len_snake - 1; x >= 0; x--) {
				snake_x[x+1] = snake_x[x];
			}
			for(int x = len_snake; x >= 0; x--) {
				if(x == 0)
					snake_y[x] = snake_y[x] - 25;
				else
					snake_y[x] = snake_y[x-1];
				
				if(snake_y[x] < 75) {
					snake_y[x] = 625;
				}	
			}
			repaint();
		}
		if(down) {
			for(int x = len_snake - 1; x >= 0; x--) {
				snake_x[x+1] = snake_x[x];
			}
			for(int x = len_snake; x >= 0; x--) {
				if(x == 0)
					snake_y[x] = snake_y[x] + 25;
				else
					snake_y[x] = snake_y[x-1];
				
				if(snake_y[x] > 625) {
					snake_y[x] = 75;
				}	
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			m = 0;
			score = 0;
			len_snake = 3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			m++;
			right = true;
			if(left == false)
				right = true;
			else
			{
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			m++;
			up = true;
			if(down == false)
				up = true;
			else
			{
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			m++;
			left = true;
			if(right == false)
				left = true;
			else
			{
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			m++;
			down = true;
			if(up == false)
				down = true;
			else
			{
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
