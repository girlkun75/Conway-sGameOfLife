package com.lai.gui;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(String title, Game game) {
		this.setUndecorated(true);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(game);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
}
