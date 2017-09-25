package battleship;

/**
 * These classes describe characteristics of different kinds of ships.
 * The abstract class Ship describes characteristics common to all the ships. 
 * It has five subclasses, Battleship, Cruiser, Destroyer, Submarine, and EmptySea.
 * Each of these classes describes specific characteristics of a type of ship.
 * 
 * @author Yun Zhang
 * @version 0
 */
public abstract class Ship {
	/** 
	 * the location (bowRow and bowColumb), the length, the direction (horizontal
	 *  or vertical), and the hit condition of a ship
	 */
	int bowRow;
	int bowColumn;
	int length;
	boolean horizontal;
	boolean [] hit = new boolean[4]; // all false
	
	/**
	 * Returns the length of this particular ship. (An abstract "ship" doesn't 
	 * have a fixed length.)
	 * 
	 * @return Returns the length of this particular ship.
	 */
	abstract int getLength();
	
	/**
	 * @return Returns bowRow
	 */
	int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * @return Returns bowColumn
	 */
	int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * @return Returns horizontal
	 */
	boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * @param row Sets the value of bowRow
	 */
	void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * @param column Sets the value of bowColumn
	 */
	void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * @param horizontal Sets the value of the instance variable horizontal
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Returns the length of this particular ship. (An abstract "ship" doesn't 
	 * have a specific type.)
	 * 
	 * @return Returns the type of this ship
	 */
	abstract String getShipType();
	
	/**
	 * Check if the given location is beyond the boundary of the ocean
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @param ocean An object contains a 10x10 array of Ships, representing 
	 * the "ocean", and some methods to manipulate it.
	 * @return Returns true if the location is beyond the ocean's boundary, else
	 * returns false
	 */
	boolean beyondOcean (int row, int column, Ocean ocean) {
		if (row >= 0 && row < ocean.getShipArray().length
				&& column >= 0 && column < ocean.getShipArray()[0].length) 
			return false;
		else 
			return true;
	}
	
	/**
	 * Check if there is ship around or on the given location. "Around" means
	 *  there is a ship diagonally, horizontally, or vertically adjacent this
	 *  location.
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @param ocean An object contains a 10x10 array of Ships, representing 
	 * the "ocean", and some methods to manipulate it.
	 * @return Returns true if there is a ship around or on the location, else
	 * return false
	 */
	boolean shipAround (int row, int column, Ocean ocean) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				if (!beyondOcean(i, j, ocean)) { // if the location is in the ocean
					if (!ocean.getShipArray()[i][j].
							getShipType().equals("empty sea")) 
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true if it is okay to put a ship of this length with its bow in 
	 * this location, with the given orientation, and returns false otherwise. 
	 * The ship must not overlap another ship, or touch another ship (vertically, 
	 * horizontally, or diagonally), and it must not "stick out" beyond the 
	 * array. Does not actually change either the ship or the Ocean, just says 
	 * whether it is legal to do so. (Note: The length of this ship is available 
	 * as an instance variable, so we don't need to supply it as a parameter.)
	 * 
	 * @param row The row of the given ship location
	 * @param column The column of the give ship location
	 * @param horizontal The orientation of this ship
	 * @param ocean An object contains a 10x10 array of Ships, representing 
	 * the "ocean", and some methods to manipulate it.
	 * @return Returns true if this ship can be placed into the given location,
	 * else returns false
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (beyondOcean(row, column, ocean)) { // if bow is beyond the ocean
			return false;
		}
		else { // if bow is in the ocean
			if (horizontal) { // check the stern of the ship
				if (beyondOcean(row, column + this.getLength() - 1, ocean)) 
					return false;
			}
			else { // check the stern of the ship
				if (beyondOcean(row + this.getLength() - 1, column, ocean)) 
					return false;
			}
		}
		// overlap or touch another ship
		if (horizontal) { // check all parts of the ship
			for (int i = column; i < column + this.getLength(); i++) {
				if (shipAround(row, i, ocean)) 		
					return false;
			}
		}
		else { // check all parts of the ship
			for (int j = row; j < row + this.getLength(); j++) {
				if (shipAround(j, column, ocean)) 
					return false;
			}
		}
		return true;
	}
	
	/**
	 * "Puts" the ship in the ocean. This involves giving values to the bowRow, 
	 * bowColumn, and horizontal instance variables in the ship, and it also 
	 * involves putting a reference to the ship in each of 1 or more locations 
	 * (up to 4) in the ships array in the Ocean object. (Note: This will be as 
	 * many as four identical references; you can't refer to a "part" of a ship, 
	 * only to the whole ship.)
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @param horizontal The orientation of this ship
	 * @param ocean An object contains a 10x10 array of Ships, representing 
	 * the "ocean", and some methods to manipulate it.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);
		// put a reference to the ship
		if (horizontal) {
			for (int i = 0; i < this.getLength(); i++) 
				ocean.getShipArray()[row][column + i] = this;
		}
		else {
			for (int j = 0; j < this.getLength(); j++) 
				ocean.getShipArray()[row + j][column] = this;
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship 
	 * hasn't already been sunk, mark that part of the ship as "hit" (in the 
	 * hit array, 0 indicates the bow) and return true, otherwise return false.
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @return Returns true if the given location can be shot, else returns false
	 */
	boolean shootAt(int row, int column) {
		if (!isSunk()) {
			if (isHorizontal()) { // horizontal
				if (column - this.bowColumn >= 0 && 
						column - this.bowColumn < this.getLength()) {
					this.hit[column - this.bowColumn] = true; //
					return true;
				}
			}
			else { // vertical
				if (row - this.bowRow >= 0 && 
						row - this.bowRow < this.getLength()) {
					this.hit[row - this.bowRow] = true; //
					return true;
				}
			}
		}	
		return false;
	}
	
	/**
	 * Check if the ship is sunk
	 * 
	 * @return Returns true if every part of the ship has been hit, 
	 * false otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < this.getLength(); i++) {
			// only need to check used locations
			if (this.hit[i] == false) 
				return false;
		}
		return true;
	}
	
	/**
	 * Override the superclass' toString() function. This method should return 
	 * "x" if the ship has been sunk, "S" if it has not been sunk. This method 
	 * can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at.
	 * 
	 * @return Returns a single-character String to use in the Ocean's print method
	 */
	@Override
	public String toString() {
		if (isSunk()) 
			return "x";
		else 
			return "S";
	}

}
