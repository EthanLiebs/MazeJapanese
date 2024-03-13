package me.ethan;

import java.util.Arrays;
import java.util.Collections;

public class Maze {
	private final Cell[][] maze;
	private int playerRow;
	private int playerColumn;
	
	public Maze(int width, int height) {
		this.maze = new Cell[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.maze[i][j] = new Cell(true, true, true, true);
			}
		}
		
		this.depthFirstSearch(this.maze[0][0]);
		this.maze[height - 1][width - 1].setSouthWall(false);
		
		this.playerRow = 0;
		this.playerColumn = 0;
	}
	
	public Cell[][] getMaze() {
		return this.maze;
	}
	
	public int getHeight() {
		return this.maze.length;
	}
	
	public int getWidth() {
		return this.maze[0].length;
	}
	
	public int getPlayerRow() {
		return this.playerRow;
	}
	
	public int getPlayerColumn() {
		return this.playerColumn;
	}
	
	public boolean isComplete() {
		return this.playerColumn == this.getWidth() - 1 && this.playerRow == this.getHeight() - 1;
	}
	
	/**
	 * 0 = move north
	 * 1 = move west
	 * 2 = move east
	 * 3 = move south
	 */
	public void movePlayer(int direction) {
		int rowOffset = (direction == 0 ? -1 : direction == 3 ? 1 : 0);
		int columnOffset = (direction == 1 ? -1 : direction == 2 ? 1 : 0);
		
		Cell current = this.maze[this.playerRow][this.playerColumn];
		
		if (this.invalid(this.playerRow + rowOffset, this.playerColumn + columnOffset) == null) {
			System.out.println("Space does not exist");
			return;
		}
		
		//fix this and then complete maze finished logic
		if ((direction == 0 && current.isNorthWall()) ||
				(direction == 1 && current.isWestWall()) ||
				(direction == 2 && current.isEastWall()) ||
				(direction == 3 && current.isSouthWall())) {
			return;
		}
		
		this.playerRow = this.playerRow + rowOffset;
		this.playerColumn = this.playerColumn + columnOffset;
	}
	
	/**
	 * @return elements in array are nullable
	 * 
	 * 0 = north
	 * 1 = west
	 * 2 = east
	 * 3 = south
	 */
	private Cell[] getNeighbers(Cell cell) {
		int row = 0;
		int column = 0;
		
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				Cell current = this.maze[i][j];
				
				if (cell == current) { //same object in memory
					row = i;
					column = j;
				}
			}
		}
		
		Cell[] cells = new Cell[4];
		for (int i = 0; i < cells.length; i++) {
			cells[i] = this.invalid(row + (i == 1 ? -1 : i == 2 ? 1 : 0), column + (i == 0 ? 1 : i == 3 ? -1 : 0));
		}
		
		return cells;
	}
	
	/**
	 * @return nullable
	 */
	private Cell getRandomUnvistedNeighber(Cell cell) {
		Cell[] cells = this.getNeighbers(cell);
		Collections.shuffle(Arrays.asList(cells));
		
		for (Cell current : cells) {
			if (current != null && !current.isVisited()) {
				return current;
			}
		}
		
		return null;
	}
	
	private void removeWall(Cell one, Cell two) {
	    // Find the relative position of two compared to one
	    int oneRow = -1, oneCol = -1, twoRow = -1, twoCol = -1;

	    // Locate the positions of one and two in the maze
	    for (int i = 0; i < getHeight(); i++) {
	        for (int j = 0; j < getWidth(); j++) {
	            if (maze[i][j] == one) {
	                oneRow = i;
	                oneCol = j;
	            } else if (maze[i][j] == two) {
	                twoRow = i;
	                twoCol = j;
	            }
	        }
	    }

	    // Determine the relative position and remove the walls accordingly
	    if (oneRow == twoRow) { // If they are in the same row, they are east or west of each other
	        if (oneCol < twoCol) { // two is east of one
	            one.setEastWall(false);
	            two.setWestWall(false);
	        } else { // two is west of one
	            one.setWestWall(false);
	            two.setEastWall(false);
	        }
	    } else if (oneCol == twoCol) { // If they are in the same column, they are north or south of each other
	        if (oneRow < twoRow) { // two is south of one
	            one.setSouthWall(false);
	            two.setNorthWall(false);
	        } else { // two is north of one
	            one.setNorthWall(false);
	            two.setSouthWall(false);
	        }
	    }
	}
	
	/**
	 * @return nullable
	 */
	private Cell invalid(int row, int column) {
		try {
			return this.maze[row][column];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private void depthFirstSearch(Cell cell) {
		cell.setVisited(true);
		Cell next = this.getRandomUnvistedNeighber(cell);
		while (next != null) {
			this.removeWall(cell, next);
			this.depthFirstSearch(next);
			next = this.getRandomUnvistedNeighber(cell);
		}
	}
}