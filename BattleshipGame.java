package battleship;

import java.util.Scanner;

/**
 * This program is a solo version of battleship game, where the computer places 
 * the ships, and the human attempts to sink them. The initial display of the 
 * ocean shows a 10 by 10 array of locations, and there are 1 battleship, 2 
 * crusiers, 3 destroyers, and 4 submarines. The human player does not know 
 * where the ships are, and they have to sunk all these 10 ships with as few 
 * shots as possible.
 * 
 * @author Yun Zhang
 * @version 0
 */
public class BattleshipGame {
	/** Used for getting input from the user. */
    Scanner scanner = new Scanner(System.in);

	/**
	 * Introduce the battleship game
	 */
	void introduction() {
		System.out.println("Battleship game (solo version):");
		System.out.println("There are 1 battleship, 2 crusiers, 3 destroyers, "
				+ "and 4 submarines in the ocean. The player needs to sunk all "
				+ "these 10 ships with as few shots as possible.");
		System.out.println("The player can hit the same location as many times "
				+ "as he wants, but this behavior only wastes the shoot without "
				+ "receiving a better result.");
		System.out.println("The initial display of the ocean shows a 10 by 10 "
				+ "array of locations. Therefore, when choose the location to "
				+ "hit, please only input integers ranged from 0 to 9 as the "
				+ "row number and the column number.");
	}
	
	/**
	 * Ask the player to give an answer for the related question. If the answer
	 * is not proper, ask him or her to answer again.
	 * 
	 * @param question The given question
	 * @param expected  The expected answers
	 * @return Returns the proper player answer
	 */
	String userinput (String question, String[] expected) {
		boolean proper = false;
		String readInput = "";
        while (!proper) {
        	System.out.println(question);
            readInput= scanner.nextLine();
            for (String s : expected) { // check if the input is proper
            	if (readInput.matches(s))
            		proper = true;
            }
        }
		return readInput;
	}
	
	/**
	 * The process of playing one game. In this process, we should ask the 
	 * player to give proper inputs, decide if the player really shots a ship, 
	 * ask if the player want to continue the game, and check if the game is 
	 * over.
	 * 
	 * @param ocean The randomly created ocean for the current game
	 */
	void playOneGame(Ocean ocean) {
		String[] location = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] playOrNot = {"y", "n"};
		String play = "y";
		String row, column; // the player will input row and column as strings
		int intRow, intColumn; // convert strings into integers	
		while (!ocean.isGameOver() && play.equals("y")) { // play the game
			// can quit during the game~
			play = userinput("Do you want to continue the game? (If yes, "
					+ "enter 'y'; otherwise, enter 'n'.): ", playOrNot);
			if (play.equals("y")) { // if "yes", continue the game
				row = userinput("Please enter the row you want to hit "
						+ "(integer 0 to 9): ", location);
				column= userinput("Please enter the column you want to hit "
						+ "(integer 0 to 9): ", location);
				intRow = Integer.parseInt(row); // convert
				intColumn = Integer.parseInt(column); // convert
				if(ocean.shootAt(intRow, intColumn)) { // hit condition
					System.out.println("Hit!");
					if (ocean.getShipArray()[intRow][intColumn].isSunk()) {
						// just sunk a ship
						System.out.println("You just sank a " + ocean.getShipArray()
								[intRow][intColumn].getShipType() + "!");
					}
				}
				else { 
					System.out.println("Miss!");
				}
				ocean.print(); // display result
			}
		}
	}
	
	/**
	 * Print out the game result. Telling the player his or her total shots, and
	 * tell the play if he or she has win.
	 * 
	 * @param ocean The randomly created ocean for the current game
	 */
	void result(Ocean ocean) {
		System.out.println("The game is over! You shot " + 
				ocean.getShotsFired() + " shots."); // final score
		if (ocean.isGameOver()) { // win or not
			System.out.println("You win :P");
			if (ocean.getShotsFired() == 20)
				System.out.println("You are a genius!");
			else if (ocean.getShotsFired() <= 40)
				System.out.println("You are smart! The best score is 20 shots.");
			else 
				System.out.println("The best score is 20 shots. "
						+ "You need to work hard ;P");
		}
		System.out.println();
	}
	
	/**
	 * The main method just creates a BattleshipGame object and calls its 
	 * <code>run</code> method.
	 * 
	 * @param args Not used.
	 */
	public static void main(String args[]) {    
        new BattleshipGame().run();
    }
	
	/**
	 * The program process:
	 * Set up a battleship game;
	 * Accept "shots" from the user;
	 * Display the results;
	 * Print final scores;
	 * And ask the user if he/she wants to play again.
	 */
	void run() {
		String play = "y";
		String[] playOrNot = {"y", "n"};
		introduction();
		while(play.equals("y")) {
			Ocean ocean = new Ocean(); // set up a new ocean
			ocean.placeAllShipsRandomly(); // randomly place ships
			ocean.print(); // initial ocean
			playOneGame(ocean);
			result(ocean);
			// ask the player to play again
			play = userinput("Do you want to play again? (If yes, enter 'y'; "
					+ "otherwise, enter 'n'.): ", playOrNot);
		}
	}
	
}
