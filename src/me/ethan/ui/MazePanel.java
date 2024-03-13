package me.ethan.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import me.ethan.Cell;
import me.ethan.Maze;

public class MazePanel extends JPanel {
    private final Maze maze;
    private final int cellSize; // Cell size in pixels
    
    private static final int OFFSET = 10;

    public MazePanel(Maze maze, int cellSize) {
        this.maze = maze;
        this.cellSize = cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.maze.isComplete()) {
        	g.drawString("Complete", OFFSET, OFFSET);
        	return;
        }
        
        g.setColor(Color.GREEN);
        g.fillRect(this.maze.getPlayerColumn() * this.cellSize + 2 * this.cellSize, this.maze.getPlayerRow() * this.cellSize + 2 * this.cellSize, this.cellSize, this.cellSize);
        g.setColor(Color.BLACK);
        
        for (int x = 0; x < this.maze.getHeight(); x++) {
            for (int y = 0; y < this.maze.getWidth(); y++) {
                Cell cell = this.maze.getMaze()[y][x];
                int drawX = x * this.cellSize + 2 * this.cellSize;
                int drawY = y * this.cellSize + 2 * this.cellSize;

                // Draw the North wall
                if (cell.isNorthWall()) {
                    g.drawLine(drawX, drawY, drawX + this.cellSize, drawY);
                } else {
                	g.setColor(Color.BLUE);
                    g.drawLine(drawX + OFFSET, drawY + OFFSET, drawX + OFFSET, drawY + OFFSET - this.cellSize / 5);
                    g.setColor(Color.BLACK);
                }

                // Draw the South wall
                if (cell.isSouthWall()) {
                    g.drawLine(drawX, drawY + this.cellSize, drawX + this.cellSize, drawY + this.cellSize);
                } else {
                	g.setColor(Color.BLUE);
                    g.drawLine(drawX + OFFSET, drawY + OFFSET, drawX + OFFSET, drawY + OFFSET + this.cellSize / 5);
                    g.setColor(Color.BLACK);
                }

                // Draw the West wall
                if (cell.isWestWall()) {
                    g.drawLine(drawX, drawY, drawX, drawY + this.cellSize);
                } else {
                	g.setColor(Color.BLUE);
                    g.drawLine(drawX + OFFSET, drawY + OFFSET, drawX + OFFSET - this.cellSize / 5, drawY + OFFSET);
                    g.setColor(Color.BLACK);
                }

                // Draw the East wall
                if (cell.isEastWall()) {
                    g.drawLine(drawX + this.cellSize, drawY, drawX + this.cellSize, drawY + this.cellSize);
                } else {
                	g.setColor(Color.BLUE);
                    g.drawLine(drawX + OFFSET, drawY + OFFSET, drawX + OFFSET + this.cellSize / 5, drawY + OFFSET);
                    g.setColor(Color.BLACK);
                }
            }
        }
    }
}

