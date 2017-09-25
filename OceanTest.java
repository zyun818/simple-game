package battleship;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean ocean;
	
	@Before
	public void setUp() {
		ocean = new Ocean();
	}
	
	@Test
	public void testGetShotsFired(){
		assertEquals(ocean.shotsFired, ocean.getShotsFired());
	}
	
	@Test
	public void testGetHitCount(){
		assertEquals(ocean.hitCount, ocean.getHitCount());
	}
	
	@Test
	public void testGetShipArray(){
		for (int i = 0; i < ocean.ships.length; i++) {
			for (int j = 0; j < ocean.ships[0].length; j++)
				assertEquals(ocean.ships[i][j], ocean.getShipArray()[i][j]);			
		}
	}
	
	@Test
	public void testOcean() {
		boolean empty = true;
		boolean hit = false;
		boolean real = true;
		// test Ships array
		for (int i = 0; i < ocean.getShipArray().length; i++) {
			for (int j = 0; j < ocean.getShipArray()[0].length; j++) {
				if (!ocean.getShipArray()[i][j].getShipType().equals("empty sea"))
					empty = false;
			}
		}
		assertTrue(empty);
		// test oceanHit array
		for (int i = 0; i < ocean.oceanHit.length; i++) {
			for (int j = 0; j < ocean.oceanHit[0].length; j++) {
				if (ocean.oceanHit[i][j])
					hit = true;
			}
		}
		assertFalse(hit);
		// test realShip array
		for (int i = 0; i < ocean.realShip.length; i++) {
			if (!ocean.realShip[i].getShipType().equals("empty sea"))
				real = false;
		}
		assertTrue(real);
		// test hitCount and shotsFired
		assertEquals(0, ocean.getHitCount()); 
		assertEquals(0, ocean.getShotsFired()); 
	}
	
	@Test
	public void testplaceOneShipRandomly(){
		// Battleship
		Ocean ocean1 = new Ocean();
		Ship ship1 = new Battleship();
		int placeShip1 = 0;
		int count1 = 0;
		ocean1.placeOneShipRandomly(ship1, placeShip1);
		for (int i = 0; i < ocean1.getShipArray().length; i++) {
			for (int j = 0; j < ocean1.getShipArray()[0].length; j++) {
				if (ocean1.getShipArray()[i][j].getShipType().equals("battleship"))
					count1++;
			}
		}
		assertTrue(ocean1.realShip[placeShip1].getShipType().equals("battleship"));
		assertEquals(4, count1); 
		// Cruiser
		Ocean ocean2 = new Ocean();
		Ship ship2 = new Cruiser();
		int placeShip2 = 0;
		int count2 = 0;
		ocean2.placeOneShipRandomly(ship2, placeShip2);
		for (int i = 0; i < ocean2.getShipArray().length; i++) {
			for (int j = 0; j < ocean2.getShipArray()[0].length; j++) {
				if (ocean2.getShipArray()[i][j].getShipType().equals("cruiser")) 
					count2++;
			}
		}
		assertTrue(ocean2.realShip[placeShip2].getShipType().equals("cruiser"));
		assertEquals(3, count2);
		// Destroyer
		Ocean ocean3 = new Ocean();
		Ship ship3 = new Destroyer();
		int placeShip3 = 0;
		int count3 = 0;
		ocean3.placeOneShipRandomly(ship3, placeShip3);
		for (int i = 0; i < ocean3.getShipArray().length; i++) {
			for (int j = 0; j < ocean3.getShipArray()[0].length; j++) {
				if (ocean3.getShipArray()[i][j].getShipType().equals("destroyer")) 
					count3++;
			}
		}
		assertTrue(ocean3.realShip[placeShip3].getShipType().equals("destroyer"));
		assertEquals(2, count3);
		// Submarine
		Ocean ocean4 = new Ocean();
		Ship ship4 = new Submarine();
		int placeShip4 = 0;
		int count4 = 0;
		ocean4.placeOneShipRandomly(ship4, placeShip4);
		for (int i = 0; i < ocean4.getShipArray().length; i++) {
			for (int j = 0; j < ocean4.getShipArray()[0].length; j++) {
				if (ocean4.getShipArray()[i][j].getShipType().equals("submarine")) 
					count4++;
			}
		}
		assertTrue(ocean4.realShip[placeShip4].getShipType().equals("submarine"));
		assertEquals(1, count4);	
	}
	
	@Test
	public void testplaceAllShipsRandomly(){ 
		Ocean ocean1 = new Ocean();
		int count = 0; // total ships
		int count1 = 0; // Battleship
		int count2 = 0; // Cruiser
		int count3 = 0; // Destroyer
		int count4 = 0; // Submarine
		ocean1.placeAllShipsRandomly();
		for (int i = 0; i < ocean1.getShipArray().length; i++) {
			for (int j = 0; j < ocean1.getShipArray()[0].length; j++) {
				if (!ocean1.getShipArray()[i][j].getShipType().equals("empty sea")) {
					count++;
				}
				if (ocean1.getShipArray()[i][j].getShipType().equals("battleship")) {
					count1++;
				}
				else if (ocean1.getShipArray()[i][j].getShipType().equals("cruiser")) {
					count2++;
				}
				else if (ocean1.getShipArray()[i][j].getShipType().equals("destroyer")) {
					count3++;
				}
				else if (ocean1.getShipArray()[i][j].getShipType().equals("submarine")) {
					count4++;
				}		
			}
		}
		assertEquals(20, count);
		assertEquals(4, count1);
		assertEquals(6, count2);
		assertEquals(6, count3);
		assertEquals(4, count4);
		// test realShip array's update condition
		boolean real = true;
		for (int i = 0; i < ocean1.realShip.length; i++) {
			if (i < 1) {
				if (!ocean1.realShip[i].getShipType().equals("battleship")) 
					real = false;
			}
			else if (i >= 1 && i < 3) {
				if (!ocean1.realShip[i].getShipType().equals("cruiser")) 
					real = false;
			}
			else if (i >= 3 && i < 6) {
				if (!ocean1.realShip[i].getShipType().equals("destroyer")) 
					real = false;
			}
			else if (i >= 6 && i <10) {
				if (!ocean1.realShip[i].getShipType().equals("submarine")) 
					real = false;
			}
			else {
				real = false;
			}
 		}
		assertTrue(real);
	}

	@Test
	public void testIsOccupied(){
		Ocean ocean1 = new Ocean();	
		Ship ship1 = new Cruiser();
		ship1.placeShipAt(1, 1, true, ocean1);
		assertTrue(ocean1.isOccupied(1, 1));
		assertTrue(ocean1.isOccupied(1, 2));
		assertTrue(ocean1.isOccupied(1, 3));
		assertFalse(ocean1.isOccupied(0, 0));
		assertFalse(ocean1.isOccupied(2, 2));
		assertFalse(ocean1.isOccupied(6, 7));
	}
	
	@Test
	public void testShootAt(){
		Ship ship1 = new Battleship();
		ship1.placeShipAt(0, 0, true, ocean);
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(2, 0, false, ocean);
		Ship ship3 = new Destroyer();
		ship3.placeShipAt(5, 5, true, ocean);
		Ship ship4 = new Submarine();
		ship4.placeShipAt(8, 8, false, ocean);
		// true case
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(ocean.shootAt(0, 2));
		assertTrue(ocean.shootAt(0, 2)); // the ship is afloat
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(8, 8));
		// false case
		assertFalse(ocean.shootAt(9, 0));
		assertFalse(ocean.shootAt(5, 5)); // the ship is sunk
		assertFalse(ocean.shootAt(5, 6)); // the ship is sunk
		assertFalse(ocean.shootAt(8, 8)); // the ship is sunk
		// test getShotsFired method
		assertEquals(10, ocean.getShotsFired());
	}
	
	@Test
	public void testIsGameOver(){
		Ocean ocean1 = new Ocean();
		Ship ship1 = new Battleship();
		// set an ocean map
		ship1.placeShipAt(6, 9, false, ocean1);
		ocean1.realShip[0] = ship1;
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(1, 1, true, ocean1);
		ocean1.realShip[1] = ship2;
		Ship ship3 = new Cruiser();
		ship3.placeShipAt(3, 2, true, ocean1);
		ocean1.realShip[2] = ship3;
		Ship ship4 = new Destroyer();
		ship4.placeShipAt(1, 6, false, ocean1);
		ocean1.realShip[3] = ship4;
		Ship ship5 = new Destroyer();
		ship5.placeShipAt(2, 9, false, ocean1);
		ocean1.realShip[4] = ship5;
		Ship ship6 = new Destroyer();
		ship6.placeShipAt(5, 1, true, ocean1);
		ocean1.realShip[5] = ship6;
		Ship ship7 = new Submarine();
		ship7.placeShipAt(3, 0, false, ocean1);
		ocean1.realShip[6] = ship7;
		Ship ship8 = new Submarine();
		ship8.placeShipAt(5, 4, false, ocean1);
		ocean1.realShip[7] = ship8;
		Ship ship9 = new Submarine();
		ship9.placeShipAt(5, 7, false, ocean1);
		ocean1.realShip[8] = ship9;
		Ship ship10 = new Submarine();
		ship10.placeShipAt(9, 0, false, ocean1);
		ocean1.realShip[9] = ship10;
		// test
		assertFalse(ocean1.isGameOver());
		ocean1.shootAt(0, 1);
		assertFalse(ocean1.isGameOver());	
		ocean1.shootAt(1, 1);
		ocean1.shootAt(1, 2);	
		assertFalse(ocean1.isGameOver());
		ocean1.shootAt(1, 3); // 1st ship sunk
		assertFalse(ocean1.isGameOver());
		ocean1.shootAt(1, 6);
		ocean1.shootAt(2, 6); // 2nd ship sunk
		ocean1.shootAt(2, 9);
		ocean1.shootAt(3, 9); // 3rd ship sunk
		ocean1.shootAt(3, 0); // 4th ship sunk
		assertFalse(ocean1.isGameOver());		
		ocean1.shootAt(3, 2);
		ocean1.shootAt(3, 3);
		ocean1.shootAt(3, 4); // 5th ship sunk
		ocean1.shootAt(5, 1);
		ocean1.shootAt(5, 2); // 6th ship sunk
		assertFalse(ocean1.isGameOver());
		ocean1.shootAt(5, 4); // 7th ship sunk
		ocean1.shootAt(5, 7); // 8th ship sunk
		ocean1.shootAt(6, 9);
		ocean1.shootAt(7, 9);
		ocean1.shootAt(8, 9);
		ocean1.shootAt(9, 9); // 9th ship sunk
		ocean1.shootAt(9, 0); // 10th ship sunk
		assertTrue(ocean1.isGameOver());
	}
	
}
