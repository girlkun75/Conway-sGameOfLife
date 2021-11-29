package com.lai.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Human {

	public static int size = 10;

	private boolean[][] zone;

	private boolean[][] temp;

	private Random rd = new Random();

	public Human(int width, int height) {
		zone = new boolean[height / size][width / size];
		temp = new boolean[height / size][width / size];
//		random();
	}
	
	public void setZone(int x, int y) {
		zone[y][x] = true;
	}
	
	public void unZone(int x, int y) {
		zone[y][x] = false;
		temp[y][x] = false;
	}

	public void update() {
		for (int y = 0; y < zone.length; y++) {
			for (int x = 0; x < zone[y].length; x++) {
				int count = countNeighbor(x, y);
				if(zone[y][x]) {
					if(count > 3 || count < 2) {
						temp[y][x] = false;
					} else {
						temp[y][x] = true;
					}
				} else {
					if(count == 3) {
						temp[y][x] = true;
					}
				}
			}
		}
		for (int y = 0; y < zone.length; y++) {
			for (int x = 0; x < zone[y].length; x++) {
				zone[y][x] = temp[y][x];
			}
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int y = 0; y < zone.length; y++) {
			for (int x = 0; x < zone[y].length; x++) {
				if (zone[y][x]) {
//					g2.setColor(new Color(rd.nextInt(256),rd.nextInt(256),rd.nextInt(256)));
					g2.setColor(new Color(0,0,0));
					g2.fillRect(x * size + 1, y * size + 1, size - 1, size - 1);
				}
			}
		}
	}

	private int countNeighbor(int x, int y) {
		int count = 0;
		for (int y1 = y - 1; y1 <= y + 1; y1++) {
			for (int x1 = x - 1; x1 <= x + 1; x1++) {
				if(y1 < 0 || y1 >= zone.length || x1 < 0 || x1 >= zone[0].length || (x1 == x && y1 == y)) {
					continue;
				}
				if(zone[y1][x1]) {
					count++;
				}
			}
		}
		return count;
	}
	
	private void random() {
		for (int y = 0; y < zone.length; y++) {
			for (int x = 0; x < zone[y].length; x++) {
				zone[y][x] = rd.nextInt(30000)%2==1;
			}
		}
	}
	
	public void clear() {
		for (int y = 0; y < zone.length; y++) {
			for (int x = 0; x < zone[y].length; x++) {
				zone[y][x] = false;
				temp[y][x] = false;
			}
		}
	}
	
	public void createBullet(int x, int y) {
		zone[y][x] = true;
		zone[y+1][x+1] = true;
		zone[y+1][x+2] = true;
		zone[y][x+2] = true;
		zone[y-1][x+2] = true;
	}

}
