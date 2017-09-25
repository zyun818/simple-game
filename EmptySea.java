package battleship;

/**
 * EmptySea class describes an empty location of the ocean. It make the program
 * run more easily. The length of an empty sea should be 1.
 * 
 * @author Yun Zhang
 * @version 0
 */
public class EmptySea extends Ship {
	
	/**
	 * initialize an empty sea's length, which should be 1, and hit condition
	 */
	EmptySea () {
		this.length = 1;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	int getLength() {
		return this.length;
	}
	
	@Override
	String getShipType() {
		return "empty sea";
	}
	
	/**
	 * This method overrides shootAt(int row, int column) that is inherited 
	 * from Ship, and always returns false to indicate that nothing was hit.
	 * (Although always return false, we need to update the hit condition, which 
	 * will make the print() method in class Ocean easier.)
	 * 
	 * @param row The row of the given ship location
	 * @param column The column of the given ship location
	 * @return Always returns false
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * This method overrides isSunk() that is inherited from Ship, and always 
	 * returns false to indicate that you didn't sink anything.
	 * 
	 * @return Always returns false
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 * @return Returns a single-character String "-" to use in the Ocean's 
	 * print method
	 */
	@Override
	public String toString() {
		return "-";
	}
}
