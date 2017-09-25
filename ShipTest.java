package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	
	@Test
	public void testGetBowRow() {
		Ship ship1 = new Battleship();
		assertEquals(ship1.bowRow, ship1.getBowRow()); 	
		Ship ship2 = new EmptySea();
		assertEquals(ship2.bowRow, ship2.getBowRow()); 
	}
	
	@Test
	public void testGetBowColumn() {
		Ship ship1 = new Battleship();
		assertEquals(ship1.bowColumn, ship1.getBowColumn()); 	
		Ship ship2 = new EmptySea();
		assertEquals(ship2.bowColumn, ship2.getBowColumn()); 
	}
	
	@Test
	public void testIsHorizontal() {
		Ship ship1 = new Battleship();
		assertEquals(ship1.horizontal, ship1.isHorizontal()); 	
		Ship ship2 = new EmptySea();
		assertEquals(ship2.horizontal, ship2.isHorizontal()); 
	}

	@Test
	public void testSetBowRow() {
		Ship ship1 = new Battleship();
		ship1.setBowRow(0);
		assertEquals(0, ship1.getBowRow()); 
		Ship ship2 = new Battleship();
		ship2.setBowRow(10);
		assertEquals(10, ship2.getBowRow()); 
	}
	
	@Test
	public void testSetBowColumn(){
		Ship ship1 = new Battleship();
		ship1.setBowColumn(0);
		assertEquals(0, ship1.getBowColumn()); 
		Ship ship2 = new Battleship();
		ship2.setBowColumn(10);
		assertEquals(10, ship2.getBowColumn()); 
	}
	
	@Test
	public void testSetHorizontal(){
		Ship ship1 = new Battleship();
		ship1.setHorizontal(false);
		assertEquals(false, ship1.isHorizontal());
		Ship ship2 = new Battleship();
		ship2.setHorizontal(true);
		assertEquals(true, ship2.isHorizontal());
	}
	
	// Test getLength() and getShipType() in each subclass
	
	@Test
	public void testBeyondOcean(){
		Ocean ocean = new Ocean();
		Ship ship1 = new Cruiser();
		// beyond the boundary
		assertTrue(ship1.beyondOcean(-1, -1, ocean));
		assertTrue(ship1.beyondOcean(10, 10, ocean));
		assertTrue(ship1.beyondOcean(-1, 5, ocean));
		assertTrue(ship1.beyondOcean(5, -1, ocean));
		assertTrue(ship1.beyondOcean(10, 4, ocean));
		assertTrue(ship1.beyondOcean(4, 10, ocean));
		assertTrue(ship1.beyondOcean(13, 250, ocean));
		assertTrue(ship1.beyondOcean(-13, 250, ocean));
		// in the boundary
		assertFalse(ship1.beyondOcean(0, 0, ocean));
		assertFalse(ship1.beyondOcean(0, 9, ocean));
		assertFalse(ship1.beyondOcean(9, 0, ocean));
		assertFalse(ship1.beyondOcean(9, 9, ocean));
		assertFalse(ship1.beyondOcean(0, 3, ocean));
		assertFalse(ship1.beyondOcean(3, 0, ocean));
		assertFalse(ship1.beyondOcean(9, 3, ocean));
		assertFalse(ship1.beyondOcean(3, 9, ocean));
		assertFalse(ship1.beyondOcean(6, 8, ocean));
	}
	
	@Test
	public void testShipAround(){
		Ocean ocean = new Ocean();
		Ship ship1 = new Cruiser();
		ocean.getShipArray()[1][1] = new Submarine();
		ocean.getShipArray()[1][6] = new Submarine();
		ocean.getShipArray()[2][9] = new Submarine();
		ocean.getShipArray()[5][2] = new Submarine();
		ocean.getShipArray()[5][7] = new Submarine();
		ocean.getShipArray()[7][5] = new Submarine();
		ocean.getShipArray()[9][0] = new Submarine();
		// have ship around or on the location
		assertTrue(ship1.shipAround(0, 0, ocean));
		assertTrue(ship1.shipAround(0, 6, ocean));
		assertTrue(ship1.shipAround(3, 8, ocean));
		assertTrue(ship1.shipAround(5, 1, ocean));
		assertTrue(ship1.shipAround(5, 3, ocean));
		assertTrue(ship1.shipAround(6, 5, ocean));
		assertTrue(ship1.shipAround(6, 6, ocean));
		assertTrue(ship1.shipAround(8, 5, ocean));
		assertTrue(ship1.shipAround(8, 6, ocean));
		// do not have ship around or on the location
		assertFalse(ship1.shipAround(0, 3, ocean));
		assertFalse(ship1.shipAround(0, 9, ocean));
		assertFalse(ship1.shipAround(1, 4, ocean));
		assertFalse(ship1.shipAround(3, 7, ocean));
		assertFalse(ship1.shipAround(7, 7, ocean));
		assertFalse(ship1.shipAround(9, 2, ocean));
	}
	
	@Test
	public void testOkToPlaceShipAt(){
		Ocean ocean = new Ocean();
		Ship ship1 = new Battleship();
		Ship ship2 = new Cruiser();
		Ship ship3 = new Destroyer();
		Ship ship4 = new Submarine();
		ocean.getShipArray()[1][1] = new Submarine();
		ocean.getShipArray()[1][6] = new Submarine();
		ocean.getShipArray()[2][9] = new Submarine();
		ocean.getShipArray()[5][2] = new Submarine();
		ocean.getShipArray()[5][7] = new Submarine();
		ocean.getShipArray()[7][5] = new Submarine();
		ocean.getShipArray()[9][0] = new Submarine();
		// ok to place
		assertTrue(ship1.okToPlaceShipAt(3, 2, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(2, 4, false, ocean));
		assertTrue(ship2.okToPlaceShipAt(9, 2, true, ocean));
		assertTrue(ship2.okToPlaceShipAt(3, 0, false, ocean));
		assertTrue(ship3.okToPlaceShipAt(0, 8, true, ocean));
		assertTrue(ship3.okToPlaceShipAt(7, 2, false, ocean));
		assertTrue(ship4.okToPlaceShipAt(5, 5, true, ocean));
		assertTrue(ship4.okToPlaceShipAt(5, 5, false, ocean));
		// fail to place
		assertFalse(ship1.okToPlaceShipAt(4, 0, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(1, 2, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(5, 5, false, ocean));
		assertFalse(ship2.okToPlaceShipAt(1, 7, true, ocean));
		assertFalse(ship2.okToPlaceShipAt(2, 2, false, ocean));
		assertFalse(ship3.okToPlaceShipAt(5, 0, true, ocean));
		assertFalse(ship3.okToPlaceShipAt(8, 5, false, ocean));
		assertFalse(ship4.okToPlaceShipAt(0, 0, false, ocean));
		assertFalse(ship4.okToPlaceShipAt(9, 0, false, ocean));
	}
	
	@Test
	public void testPlaceShipAt(){
		Ocean ocean = new Ocean();
		// Battleship
		Ship ship1 = new Battleship();
		ship1.placeShipAt(0, 0, true, ocean);
		assertEquals(0, ship1.getBowRow());
		assertEquals(0, ship1.getBowColumn());
		assertTrue(ship1.isHorizontal());
		assertTrue(ocean.getShipArray()[0][0].equals(ship1));
		assertTrue(ocean.getShipArray()[0][1].equals(ship1));
		assertTrue(ocean.getShipArray()[0][2].equals(ship1));
		assertTrue(ocean.getShipArray()[0][3].equals(ship1));
		assertFalse(ocean.getShipArray()[0][4].equals(ship1));
		// Cruiser
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(2, 0, false, ocean);
		assertEquals(2, ship2.getBowRow());
		assertEquals(0, ship2.getBowColumn());
		assertFalse(ship2.isHorizontal());
		assertTrue(ocean.getShipArray()[2][0].equals(ship2));
		assertTrue(ocean.getShipArray()[3][0].equals(ship2));
		assertTrue(ocean.getShipArray()[4][0].equals(ship2));
		assertFalse(ocean.getShipArray()[1][0].equals(ship2));
		assertFalse(ocean.getShipArray()[5][0].equals(ship2));
		// Destroyer
		Ship ship3 = new Destroyer();
		ship3.placeShipAt(5, 5, true, ocean);
		assertEquals(5, ship3.getBowRow());
		assertEquals(5, ship3.getBowColumn());
		assertTrue(ship3.isHorizontal());
		assertTrue(ocean.getShipArray()[5][5].equals(ship3));
		assertTrue(ocean.getShipArray()[5][6].equals(ship3));
		assertFalse(ocean.getShipArray()[4][5].equals(ship3));
		assertFalse(ocean.getShipArray()[6][6].equals(ship3));
		// Submarine
		Ship ship4 = new Submarine();
		ship4.placeShipAt(8, 8, false, ocean);
		assertEquals(8, ship4.getBowRow());
		assertEquals(8, ship4.getBowColumn());
		assertFalse(ship4.isHorizontal());
		assertTrue(ocean.getShipArray()[8][8].equals(ship4));
		assertFalse(ocean.getShipArray()[7][7].equals(ship4));
	}
	
	@Test
	public void testShootAt(){
		Ocean ocean = new Ocean();
		Ship ship1 = new Battleship();
		ship1.placeShipAt(1, 1, true, ocean);
		assertTrue(ship1.shootAt(1, 1));
		assertTrue(ship1.shootAt(1, 1)); // shot more than once
		assertTrue(ship1.shootAt(1, 2));
		assertTrue(ship1.shootAt(1, 3));
		assertTrue(ship1.shootAt(1, 4));
		assertFalse(ship1.shootAt(1, 1)); // the ship should be sunk at this time
		assertFalse(ship1.shootAt(1, 3));
		assertFalse(ship1.shootAt(2, 1));
		assertFalse(ship1.shootAt(1, 5));
	}
	
	@Test
	public void testIsSunk(){
		Ocean ocean = new Ocean();
		// case1
		Ship ship1 = new Cruiser();
		ship1.placeShipAt(1, 1, true, ocean);
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 1);
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 1); // shot more than once
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 2);
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 3);
		assertTrue(ship1.isSunk());
		ship1.shootAt(1, 1);
		assertTrue(ship1.isSunk());
		// case2
		Ship ship2 = new Submarine();
		ship2.placeShipAt(5, 5, false, ocean);
		assertFalse(ship2.isSunk());
		ship2.shootAt(1, 1);
		assertFalse(ship2.isSunk());
		ship2.shootAt(5, 5);
		assertTrue(ship2.isSunk());
	}
	
	@Test
	public void testToString(){
		Ocean ocean = new Ocean();
		
		Ship ship1 = new Cruiser();
		ship1.placeShipAt(1, 1, true, ocean);
		ship1.shootAt(1, 1);
		assertTrue(ship1.toString().equals("S"));
		ship1.shootAt(1, 2);
		assertTrue(ship1.toString().equals("S"));
		ship1.shootAt(1, 2); // shot more than once
		assertTrue(ship1.toString().equals("S"));
		ship1.shootAt(1, 3);
		assertTrue(ship1.toString().equals("x"));
		
		Ship ship2 = new Submarine();
		ship2.placeShipAt(5, 5, false, ocean);
		ship2.shootAt(5, 5);
		assertTrue(ship2.toString().equals("x"));
	}
	
	// subclass
	@Test
	public void testGetLength() {
		Ship ship1 = new Battleship();
		assertEquals(ship1.length, ship1.getLength());
		Ship ship2 = new Cruiser();
		assertEquals(ship2.length, ship2.getLength());
		Ship ship3 = new Destroyer();
		assertEquals(ship3.length, ship3.getLength());
		Ship ship4 = new Submarine();
		assertEquals(ship4.length, ship4.getLength());
		Ship ship5 = new EmptySea();
		assertEquals(ship5.length, ship5.getLength());
	}
	
	@Test
	public void testGetShipType() {
		Ship ship1 = new Battleship();
		assertTrue(ship1.getShipType().equals("battleship"));
		Ship ship2 = new Cruiser();
		assertTrue(ship2.getShipType().equals("cruiser"));
		Ship ship3 = new Destroyer();
		assertTrue(ship3.getShipType().equals("destroyer"));
		Ship ship4 = new Submarine();
		assertTrue(ship4.getShipType().equals("submarine"));
		Ship ship5 = new EmptySea();
		assertTrue(ship5.getShipType().equals("empty sea"));
	}
	
	@Test
	public void testBattleship(){
		Ship ship1 = new Battleship();
		assertEquals(4, ship1.getLength());
		String bool = "";
		for (int i = 0; i < ship1.getLength(); i++) {
			bool += Boolean.toString(ship1.hit[i]) + ", ";
		}
		assertTrue(bool.equals("false, false, false, false, "));
	}

	@Test
	public void testCruiser(){
		Ship ship1 = new Cruiser();
		assertEquals(3, ship1.getLength());
		String bool = "";
		for (int i = 0; i < ship1.getLength(); i++) {
			bool += Boolean.toString(ship1.hit[i]) + ", ";
		}
		assertTrue(bool.equals("false, false, false, "));
	}
	
	@Test
	public void testDestroyer(){
		Ship ship1 = new Destroyer();
		assertEquals(2, ship1.getLength());
		String bool = "";
		for (int i = 0; i < ship1.getLength(); i++) {
			bool += Boolean.toString(ship1.hit[i]) + ", ";
		}
		assertTrue(bool.equals("false, false, "));
	}
	
	@Test
	public void testSubmarine(){
		Ship ship1 = new Submarine();
		assertEquals(1, ship1.getLength());
		String bool = "";
		for (int i = 0; i < ship1.getLength(); i++) {
			bool += Boolean.toString(ship1.hit[i]) + ", ";
		}
		assertTrue(bool.equals("false, "));
	}
	
	// subclass: empty sea
	@Test
	public void testEmptySea(){
		Ship ship1 = new EmptySea();
		assertEquals(1, ship1.getLength());
		String bool = "";
		for (int i = 0; i < ship1.getLength(); i++) {
			bool += Boolean.toString(ship1.hit[i]) + ", ";
		}
		assertTrue(bool.equals("false, ")); 
	}
	
	@Test
	public void testEmptySeaShootAt(){
		Ocean ocean = new Ocean();
		Ship ship1 = new EmptySea();
		ship1.placeShipAt(1, 1, true, ocean);
		assertFalse(ship1.shootAt(1, 1));
	}
	
	@Test
	public void testEmptySeaIsSunk(){
		Ocean ocean = new Ocean();
		Ship ship1 = new EmptySea();
		ship1.placeShipAt(1, 1, true, ocean);
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 1); // after a shoot
		assertFalse(ship1.isSunk());
	}
	
	@Test
	public void testEmptySeaToString(){
		Ocean ocean1 = new Ocean();
		Ship ship1 = new EmptySea();
		ship1.placeShipAt(1, 1, true, ocean1);
		assertTrue(ship1.toString().equals("-"));
	}
	
}
