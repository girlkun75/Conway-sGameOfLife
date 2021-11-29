package com.lai.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Map {
	
	private int size;
	
	private int[][] map;
	
	public Map(int width, int height) {
		size = Human.size;
		map = new int[height/size][width/size];
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(238,238,238));
		g2.fillRect(0, 0, size*map[0].length, size*map.length);
		g2.setColor(Color.black);
//		for(int y = 0; y < map.length; y++) {
//			for(int x = 0; x < map[y].length; x++) {
//				g2.drawRect(x*size, y*size, size, size);
//			}
//		}
	}
	
}
