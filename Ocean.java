package battleship;

import java.util.Random;

/**
 * This class contains a 10x10 array of Ships, representing the "ocean", and 
 * some methods to manipulate it.
 * 
 * @author Yun Zhang
 * @version 0
 */
public class Ocean {
	/** 
	 * a 10x10 array of Ships, a list of 10 real ship, a 10x10 ocean hit 
	 * condition, the total number of shots fired by the user, and the number 
	 * of times a shot hit a ship.
	 */
	Ship[][] ships = new Ship[10][10];
	Ship[] realShip = new Ship[10];
	boolean[][] oceanHit = new boolean[10][10];
	int shotsFired;
	int hitCount;

	/**
	 * Creates an "empty" ocean (fills the ships array with EmptySeas). Also 
	 * initializes any game variables, such as how many shots have been fired.
	 */
	Ocean() {
		 // initialize ships and oceanHit, they have the same size
		for (int i = 0; i < this.ships.length; i++) {
			for (int j = 0; j < this.ships[0].length; j++) {
				Ship emptyship = new EmptySea();
				emptyship.placeShipAt(i, j, true, this);
				oceanHit[i][j] = false;
			}
		}
		for (int i = 0; i < this.realShip.length; i++) { // initialize realShip
			realShip[i] = new EmptySea();
		}
		shotsFired = 0;
		hitCount = 0;
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean, making 
	 * certain that all ships are placed legally. Place larger ships before 
	 * smaller ones. If you place the smaller ships first, you may be unable 
	 * to place larger ships; but if you place the larger ships first, it will 
	 * always be possible to find somewhere to put the smaller ships. You will 
	 * want to use the Random class in the java.util package, Also remember to 
	 * record this ship into realShip Array
	 */
	void placeAllShipsRandomly() {
		int shipCount = 0; // use to record real ship
		for (int count1 = 0; count1 < 1; count1++) { // place 1 Battleship
			Ship battleship = new Battleship();
			placeOneShipRandomly(battleship, shipCount);
			shipCount++;
		}
		for (int count2 = 0; count2 < 2; count2++) { // place 2 Cruiser
			Ship cruiser = new Cruiser();
			placeOneShipRandomly(cruiser, shipCount);
			shipCount++;
		}
		for (int count3 = 0; count3 < 3; count3++) { // place 3 Destroyer
			Ship destroyer = new Destroyer();
			placeOneShipRandomly(destroyer, shipCount);
			shipCount++;
		}
		for (int count4 = 0; count4 < 4; count4++) { // place 4 Submarine
			Ship submarine = new Submarine();
			placeOneShipRandomly(submarine, shipCount);
			shipCount++;
		}
	}
	
	/**
	 * Randomly place the given ship type into this ocean, making certain that 
	 * the ship is placed legally. If the ship is placed illegally, randomly
	 * change another location to place it. Keep change the location until the 
	 * ship is placed legally. Also remember to record this ship into realShip
	 * Array
	 * 
	 * @param ship The given ship type
	 */
	void placeOneShipRandomly(Ship ship, int shipCount) {
		Random random = new Random();
		boolean randhor = random.nextBoolean();
		int randrow = random.nextInt(this.ships.length);
		int randcol = random.nextInt(this.ships[0].length);
		while (!ship.okToPlaceShipAt(randrow, randcol, randhor, this)) {
			randhor = random.nextBoolean();
			randrow = random.nextInt(this.ships.length);
			randcol = random.nextInt(this.ships[0].length);
		}
		ship.placeShipAt(randrow, randcol, randhor, this);
		realShip[shipCount] = this.getShipArray()[randrow][randcol];
	}
	
	/**
	 * Check if the given location contains a ship.
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @return Returns true if the given location contains a ship, 
	 * false if it does not.
	 */
	boolean isOccupied(int row, int column) {
		if (this.ships[row][column].getShipType().equals("empty sea")) 
			return false;
		return true;
	}

	/**
	 * Returns true if the given location contains a "real" ship, still afloat, 
	 * (not an EmptySea), false if it does not. In addition, this method 
	 * updates the number of shots that have been fired, and the number of hits.
	 * Note: If a location contains a "real" ship, shootAt should return true 
	 * every time the user shoots at that same location. Once a ship has been 
	 * "sunk", additional shots at its location should return false.
	 * 
	 * @param row The row of the given location
	 * @param column The column of the give location
	 * @return Returns true if the given location contains a "real" ship, still 
	 * afloat, (not an EmptySea), false if it does not.
	 */
	boolean shootAt(int row, int column) {
		this.oceanHit[row][column] = true;
		this.shotsFired++; // always update shotsFired
		if (isOccupied(row, column) && 
				this.ships[row][column].shootAt(row, column)) { 
			// a real ship is afloat
			this.hitCount++; // update hitCount			
			return true;
		}
		return false;
	}
	
	/**
	 * @return Returns the number of shots fired (in this game).
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in this game). All hits are counted, 
	 * not just the first time a given square is hit. (We allow the user to get 
	 * a worse score by shooting again at the same location.)
	 * @return Returns the number of times a shot hit a ship
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * @return Returns true if all ships have been sunk, otherwise false.
	 */
	boolean isGameOver() {
		int count = 0;
		for (int i = 0; i < this.realShip.length; i++) {
			if (realShip[i].isSunk()) // count the number of ships sunk
				count++;
		}
		if (count >= realShip.length) // if all ships are sunk
			return true;
		return false;
	}
	
	/**
	 * Returns the actual 10x10 array of ships, not a copy. The methods in the 
	 * Ship class that take an Ocean parameter really need to be able to look at
	 * the contents of this array; the placeShipAt method even needs to modify 
	 * it. While it is undesirable to allow methods in one class to directly 
	 * access instance variables in another class, sometimes there is just no 
	 * good alternative.
	 * 
	 * @return Returns the actual 10x10 array of ships
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along 
	 * the left edge of the array, and column numbers should be displayed along 
	 * the top. Numbers should be 0 to 9, not 1 to 10. The top left corner 
	 * square should be 0, 0. Use 'S' to indicate a location that you have 
	 * fired upon and hit a (real) ship, '-' to indicate a location that you 
	 * have fired upon and found nothing there, 'x' to indication location 
	 * containing a sunken ship, and '.' to indicate a location that you have 
	 * never fired upon. 
	 */
	void print() {
		System.out.println("Current ocean condition:");
		// column number
		System.out.println("    0   1   2   3   4   5   6   7   8   9");
		System.out.println("  ----------------------------------------");
		for (int i = 0; i < this.ships.length; i++) {
			System.out.print(i + " |"); // row number
			for (int j = 0; j < this.ships[0].length; j++) {
				if (this.oceanHit[i][j]) // have been fired
					System.out.print(" " + this.ships[i][j].toString() + " |");
				else // never been fired
					System.out.print(" . |");
			}
			System.out.println();
			System.out.println("  ----------------------------------------");
		}
		System.out.println();
	}
	
}
