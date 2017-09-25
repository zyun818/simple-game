package battleship;

/**
 * Submarine class describes specific characteristics of a submarine, the 
 * length of which should be 1
 * 
 * @author Yun Zhang
 * @version 0
 */
public class Submarine extends Ship {
	
	/**
	 * initialize a submarine's length and hit condition
	 */
	Submarine () {
		this.length = 1;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	int getLength() {
		return this.length;
	}
	
	@Override
	String getShipType() {
		return "submarine";
	}
	
	// toString behaves exactly the same for all ship types
}
