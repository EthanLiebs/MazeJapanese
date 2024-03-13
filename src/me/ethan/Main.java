package me.ethan;

import javax.swing.SwingUtilities;

import me.ethan.ui.MazeFrame;

public final class Main {
	private Main() {}
	
	public static void main(String[] args) {
		Maze maze = new Maze(20, 20); // Example size
        SwingUtilities.invokeLater(() -> {
            MazeFrame frame = new MazeFrame(maze);
            frame.setVisible(true);
        });
	}
}
