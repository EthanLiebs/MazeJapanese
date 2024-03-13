package me.ethan.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.ethan.Maze;

public class MazeFrame extends JFrame {
	private final Maze maze;
	private static final int CELL_SIZE = 20; // Cell size in pixels
	
	public MazeFrame(Maze maze) {
		super();
		this.maze = maze;
		
		this.setTitle("Japanese Project");
		this.setSize(maze.getWidth() * CELL_SIZE + CELL_SIZE * 2, maze.getHeight() * CELL_SIZE + 50 + CELL_SIZE * 2); // Extra space for input
        this.setLocationRelativeTo(null); // Center on screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
        
        MazePanel mazePanel = new MazePanel(this.maze, CELL_SIZE);
        this.add(mazePanel, BorderLayout.CENTER);
        
        /*
        JTextField textField = new JTextField();
        
        this.add(textField, BorderLayout.SOUTH);
        textField.addActionListener(event -> {
        	String input = event.getActionCommand();
        	
        	if (input.equalsIgnoreCase("うえ") || input.equalsIgnoreCase("ue")) {
        		maze.movePlayer(0);
        	} else if (input.equalsIgnoreCase("した") || input.equalsIgnoreCase("shita")) {
        		//move player down
        		maze.movePlayer(3);
        	} else if (input.equalsIgnoreCase("みぎ") || input.equalsIgnoreCase("migi")) {
        		//move player right
        		maze.movePlayer(2);
        	} else if (input.equalsIgnoreCase("ひだり") || input.equalsIgnoreCase("hidari")) {
        		//move player left
        		maze.movePlayer(1);
        	}
        	this.repaint();
        });
        */
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        JButton ue = new JButton("上");
        JButton shita = new JButton("下");
        JButton migi = new JButton("右");
        JButton hidari = new JButton("左");
        
        ue.addActionListener(event -> {
        	maze.movePlayer(0);
        	this.repaint();
        });
        shita.addActionListener(event -> {
        	maze.movePlayer(3);
        	this.repaint();
        });
        migi.addActionListener(event -> {
        	maze.movePlayer(2);
        	this.repaint();
        });
        hidari.addActionListener(event -> {
        	maze.movePlayer(1);
        	this.repaint();
        });

        // Add some spacing between buttons
        panel.add(ue);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(hidari);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(migi);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(shita);

        // Add the panel to the frame
        this.add(panel, BorderLayout.SOUTH);
	}
}
