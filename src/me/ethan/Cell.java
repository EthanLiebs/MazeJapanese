package me.ethan;

public class Cell {
	private boolean northWall;
	private boolean southWall;
	private boolean eastWall;
	private boolean westWall;
	private boolean visited;
	
	public Cell(boolean northWall, boolean southWall, boolean eastWall, boolean westWall) {
		this.northWall = northWall;
		this.southWall = southWall;
		this.eastWall = eastWall;
		this.westWall = westWall;
		this.visited = false;
	}

	/**
	 * @return the northWall
	 */
	public boolean isNorthWall() {
		return northWall;
	}

	/**
	 * @param northWall the northWall to set
	 */
	public void setNorthWall(boolean northWall) {
		this.northWall = northWall;
	}

	/**
	 * @return the southWall
	 */
	public boolean isSouthWall() {
		return southWall;
	}

	/**
	 * @param southWall the southWall to set
	 */
	public void setSouthWall(boolean southWall) {
		this.southWall = southWall;
	}

	/**
	 * @return the eastWall
	 */
	public boolean isEastWall() {
		return eastWall;
	}

	/**
	 * @param eastWall the eastWall to set
	 */
	public void setEastWall(boolean eastWall) {
		this.eastWall = eastWall;
	}

	/**
	 * @return the westWall
	 */
	public boolean isWestWall() {
		return westWall;
	}

	/**
	 * @param westWall the westWall to set
	 */
	public void setWestWall(boolean westWall) {
		this.westWall = westWall;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * @param wall if there should be a wall
	 * @param index the index of the wall in the array returned by Maze.getNeighbers
	 */
	public void setWall(boolean wall, int index) {
		if (index == 0) {
			this.setNorthWall(wall);
		} else if (index == 1) {
			this.setWestWall(wall);
		} else if (index == 2) {
			this.setEastWall(wall);
		} else if (index == 3) {
			this.setSouthWall(wall);
		}
	}
}
