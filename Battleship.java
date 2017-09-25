package battleship;

/**
 * Battleship class describes specific characteristics of a battleship, the 
 * length of which should be 4
 * 
 * @author Yun Zhang
 * @version 0
 */
public class Battleship extends Ship {

	/**
	 * initialize a battleship's length and hit condition
	 */
	Battleship () {
		this.length = 4;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	int getLength() { 
		return this.length;
	}
	
	@Override
	String getShipType() {
		return "battleship";
	}
	
	// toString behaves exactly the same for all ship types	
}
