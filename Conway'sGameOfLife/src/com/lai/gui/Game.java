package com.lai.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import com.lai.entity.Human;
import com.lai.entity.Map;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	static Game game;
	static {
		game = new Game();
	}

	private int width = 1920;
	private int height = 1080;

	private Thread gameLoop;
	private boolean start;

	private Map map;
	private Human human;

	private boolean remove = false;
	private boolean bullet = false;

	public Game() {
		this.setPreferredSize(new Dimension(width + 1, height));
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setCursor(new Cursor(1));
		this.map = new Map(width, height);
		this.human = new Human(width, height);
	}

	public static void main(String[] args) {
		new Window("Game", game).setVisible(true);
	}

	private void startGame() {
		if (!start) {
			this.gameLoop = new Thread(this);
			this.gameLoop.start();
		}
	}

	public void paint(Graphics g) {
		map.draw(g);
		human.draw(g);
	}

	public void update() {
		human.update();
	}

	@Override
	public void run() {
		start = true;
		long startT;
		long endT;
		int fpsTarget = 10;
		int timeTarget = 1000000000 / fpsTarget;
		long sleep;
		while (start) {
			startT = System.nanoTime();
			update();
			repaint();
			endT = System.nanoTime();
			sleep = timeTarget - (endT - startT);
			try {
				if (sleep > 0) {
					Thread.sleep(sleep / 1000000);
				} else {
					Thread.sleep(3);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			startGame();
			break;
		case KeyEvent.VK_O:
			human.update();
			break;
		case KeyEvent.VK_F:
			start = false;
			break;
		case KeyEvent.VK_X:
			remove = true;
			break;
		case KeyEvent.VK_Z:
			System.exit(0);
			break;
		case KeyEvent.VK_C:
			human.clear();
			start = false;
			map.draw(getGraphics());
			break;
		case KeyEvent.VK_B:
			bullet = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_X:
			remove = false;
			break;
		case KeyEvent.VK_B:
			bullet = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (bullet) {
				human.createBullet(e.getX() / Human.size, e.getY() / Human.size);
				repaint();
				return;
			}
			if (remove) {
				human.unZone(e.getX() / Human.size, e.getY() / Human.size);
			} else {
				human.setZone(e.getX() / Human.size, e.getY() / Human.size);
			}
			repaint();
		} catch (Exception e2) {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			if (remove) {
				human.unZone(e.getX() / Human.size, e.getY() / Human.size);
			} else {
				human.setZone(e.getX() / Human.size, e.getY() / Human.size);
			}
			repaint();
		} catch (Exception e2) {
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
