package battleship;

/**
 * Cruiser class describes specific characteristics of a cruiser, the 
 * length of which should be 3
 * 
 * @author Yun Zhang
 * @version 0
 */
public class Cruiser extends Ship {
	
	/**
	 * initialize a cruiser's length and hit condition
	 */
	Cruiser () {
		this.length = 3;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	int getLength() { 
		return this.length;
	}
	
	@Override
	String getShipType() {
		return "cruiser";
	}
	
	// toString behaves exactly the same for all ship types
}
