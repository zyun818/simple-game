package battleship;

/**
 * Destroyer class describes specific characteristics of a destroyer, the 
 * length of which should be 2
 * 
 * @author Yun Zhang
 * @version 0
 */
public class Destroyer extends Ship {
	
	/**
	 * initialize a destroyer's length and hit condition
	 */
	Destroyer () {
		this.length = 2;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	int getLength() {
		return this.length;
	}
	
	@Override
	String getShipType() {
		return "destroyer";
	}
	
	// toString behaves exactly the same for all ship types
}
