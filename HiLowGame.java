
import acm.program.*;
import acm.util.*;

public class HiLowGame extends ConsoleProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final double QUARTER_SECOND = 250;
	private static final double HALF_SECOND = 500;
	private static final double ONE_SECOND = 1000;
	private static final double TWO_SECOND = 2000;
	int dollars;
	int dollarsToRisk;
	int counter = 0;

	String highOrLow;
	String smallCapsHighLow;
	String confirmation;

	public void init() {
		// Sets screen size
		setSize(1200,800);
	}
	public void run() {
		dollars = 1000;
		// Sets font
		setFont("sansSerif-BOLD-18");
		println("Welcome to the High Low Game -  Designed by Lovjit Multani");
		pause(HALF_SECOND);
		rules();

		while (true){
			dollarsToRisk();
			dollars = dollarsDecider(prediction());
			println(" ");
			// Number of dollars in your wallet
			println("You now have " + dollars + " dollars in your wallet.");
			// Counts number of guesses
			counter++;

			if (zeroDollars() == true){
				break;
			}

			if (confirmation() == false){
				println("Thanks for playing, you took " + counter + " guess(es)!");
				break;
			}
		}
	}

	/**
	 * Confirms the balance of wallet is greater than $0
	 * @param None
	 * @return true Boolean
	 */
	private Boolean zeroDollars() {
		if (dollars == 0){
			println("Game Over!");
			println("Thanks for playing, you took " + counter + " guess(es)!");
		}else if (dollars > 0){
			return false;
		}
		return true;
	}

	/**
	 * Gives user the ability to decide if they would like to play again.
	 * @param None
	 * @return false Boolean
	 */	
	private Boolean confirmation() {
		println(" ");
		while (true){
			confirmation = readLine("Play again? (Yes, No): ");
			confirmation = confirmation.toLowerCase();
			if (confirmation.equals("no") | confirmation.equals("yes")){
				break;
			}else{
				println("Incorrect input.");
			}
		}
		if (confirmation.equals("yes")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Determines the number of dollars the user will win or lose
	 * @param highOrLow String
	 * @return dollars Integer Number
	 */	
	private int dollarsDecider(String highOrLow) {
		int num = rgen.nextInt(1, 13);

		if (num <=6 && num >= 1 && highOrLow.equals("high")){
			println(" ");
			println("You lost, " + num + " is not a high number.");
			dollars -= dollarsToRisk;
		}else if (num <=6 && num >= 1 && highOrLow.equals("low")){
			println(" ");
			println("You win, " + num + " is indeed a low number.");
			dollars += dollarsToRisk;
		}else if(num == 7){
			println(" ");
			println("You lost, " + num + " is neutral.");
			dollars -= dollarsToRisk;
		}else if(num>=8 && num <= 13 && highOrLow.equals("low")){
			println(" ");
			println("You lost, " + num + " is not a low number.");
			dollars -= dollarsToRisk;
		}else if(num>=8 && num <= 13 && highOrLow.equals("high")){
			println(" ");
			println("You win, " + num + " is indeed a high number.");
			dollars += dollarsToRisk;
		}
		return dollars;
	}

	/**
	 * Gives the user the ability to predict wheter the next number will roll high or low
	 * @param None
	 * @return smallCapsHighLow String
	 */
	private String prediction() {
		while (true){
			highOrLow = readLine("Predict(High, Low): ");
			smallCapsHighLow = highOrLow.toLowerCase();
			if (smallCapsHighLow.equals("high") || smallCapsHighLow.equals("low")) 
				break;
			println("Incorrect input.");
		}
		return smallCapsHighLow;
	}

	/**
	 * Determines the amount of dollars the user will risk
	 * @param None
	 * @return None
	 */
	private void dollarsToRisk() {
		while (true){
			println(" ");
			dollarsToRisk = readInt("Enter Dollars to risk: ");
			if (dollarsToRisk <= dollars){
				break;
			}else{
				println("You don't have " +dollarsToRisk+ " dollars in your wallet.");
			}
		}
	}

	/**
	 * Rules for the game will be outputted.
	 * @param None
	 * @return None
	 */
	private void rules() {
		pause(QUARTER_SECOND);
		println(" ");
		println("Here are the Rules:");
		pause(HALF_SECOND);
		println(" ");
		println("Numbers 1 through 6 are low.");
		pause(ONE_SECOND);
		println("Numbers 8 through 13 are high");
		pause(ONE_SECOND);
		println("Number 7 is neutral.");
		pause(ONE_SECOND);
		println(" ");
		println("You have 1000 Dollars in your wallet.");
		pause(TWO_SECOND);
	}

	/* Private instance variable rgen is visible by
	 * any method in this program, but is not visible
	 * (private) outside this class */
	private RandomGenerator rgen = new RandomGenerator();
}
