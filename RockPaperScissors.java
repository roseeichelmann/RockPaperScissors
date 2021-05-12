import java.util.Scanner;

/**
 * CS312 Assignment 4.
 *
 * On my honor, Rose Eichelmann, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 *  email address: rose.eichelmann@gmail.com
 *  UTEID: ree585
 *  Number of slip days used on this assignment: 0
 */

public class RockPaperScissors {
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	
    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
    public static void main(String[] args) {
        // CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);
        // CS312 students do no change the following line. 
        // Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);
        String name = enterName(keyboard);
        // Asks player to enter number of rounds they want to play
        int totalRounds = enterRounds(keyboard, name);
        // Plays the rounds of the game
        playRounds(keyboard, name, totalRounds, computerPlayer);
        keyboard.close();
    }

    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }
    
    // Welcomes player and asks them to enter their name
    public static String enterName(Scanner keyboard) {
    	System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
    	System.out.print("Please type in your name and press return: ");
    	String name = keyboard.nextLine();
    	return name;
    }
    
    // Asks player to enter the number of rounds they want to play
    public static int enterRounds(Scanner keyboard, String name) {
    	System.out.println();
    	System.out.println("Welcome " + name + ".");
    	System.out.println();
    	System.out.println("All right " + name + ". How many rounds would you like to play?");
    	System.out.print("Enter the number of rounds you want to play and press return: ");
    	int totalRounds = keyboard.nextInt();
    	System.out.println();
    	return totalRounds;
    }
    
    // Plays the rounds
    public static void playRounds(Scanner keyboard, String name, int totalRounds, 
    		RandomPlayer computerPlayer) {
		int totalDraws = 0;
		int totalComputerWins = 0;
		int totalPlayerWins = 0;
		// Iterates through the number of rounds user input
    	for(int round = 1; round <= totalRounds; round++) {
    		int draw = 0;
    		int computerWin = 0;
    		int playerWin = 0;
    		System.out.println("Round " + round + ".");
    		// Asks player to choose their hand
    		int playerChoice = enterChoice(keyboard, name);
    		// Computer makes random choice
    		int computerChoice = computerPlayer.getComputerChoice();
    		// Prints the choice of player and computer
    		printChoice(name, playerChoice, computerChoice);
    		// Prints statement if the round resulted in a draw
    		draw = resultDraw(playerChoice, computerChoice, draw);
    		// Prints statement if the round resulted in a computer win
    		computerWin = resultComputerWin(playerChoice, computerChoice, computerWin);
    		// Prints statement if the round resulted in a player win
    		playerWin = resultPlayerWin(playerChoice, computerChoice, playerWin);
    		totalDraws = totalDraws + draw;
    		totalComputerWins = totalComputerWins + computerWin;
    		totalPlayerWins = totalPlayerWins + playerWin;
    	}
    	// Prints the stats of the game
    	printStats(name, totalRounds, totalComputerWins, totalPlayerWins, totalDraws);
    	// Prints a statement declaring which player is best or if they are tied
    	printWinner(name, totalComputerWins, totalPlayerWins);
    }
    
    // Asks player to enter their choice for the round
    public static int enterChoice(Scanner keyboard, String name) {
    	System.out.println(name + ", please enter your choice for this round.");
    	System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
    	int choice = keyboard.nextInt();
    	return choice;
    }
    
    // Prints the choices that the player and computer respectively chose
    public static void printChoice(String name, int playerChoice, int computerChoice) {
    	System.out.print("Computer picked ");
    	// Prints the computers choice
    	convertChoice(computerChoice);
    	System.out.print(", " + name + " picked ");
    	// Prints the player's choice
    	convertChoice(playerChoice);
    	System.out.println(".");
    	System.out.println();
    }
    
    // Converts numeric choice into correct string for that choice
    public static void convertChoice(int choice) {
    	// Determines which string corresponds with the numeric choice
    	if (choice == 1) {
    		System.out.print("ROCK");
    	} else if (choice == 2) {
    		System.out.print("PAPER");
    	} else if (choice == 3) {
    		System.out.print("SCISSORS");
    	}
    }
    
    // Prints statement if the result is a draw
    public static int resultDraw(int playerChoice, int computerChoice, int draw) {
    	// Prints draw statement unless there is no draw
    	if (playerChoice == computerChoice) {
    		System.out.println("We picked the same thing! This round is a draw.");
    		System.out.println();
    		draw = 1;
    		return draw;
    	} else {
    		return draw;
    	}
    }
    
    // Prints statement if the result is a computer win
    public static int resultComputerWin(int playerChoice, int computerChoice, int computerWin) {
    	// Prints a statement depending on which hands are played
    	if ((playerChoice == ROCK) && (computerChoice == PAPER)) {
		System.out.println("PAPER covers ROCK. I win.");
		System.out.println();
		computerWin = 1;
		return computerWin;
    	} else if ((playerChoice == PAPER) && (computerChoice == SCISSORS)) {
		System.out.println("SCISSORS cut PAPER. I win.");
		System.out.println();
		computerWin = 1;
		return computerWin;
    	} else if ((playerChoice == SCISSORS) && (computerChoice == ROCK)) {
		System.out.println("ROCK breaks SCISSORS. I win.");
		System.out.println();
		computerWin = 1;
		return computerWin;
    	} else {
    		return computerWin;
    	}
    }
    
    // Prints statement if the result is a player win
    public static int resultPlayerWin(int playerChoice, int computerChoice, int playerWin) {
    	// Prints a statement depending on which hands are played
    	if ((playerChoice == PAPER) && (computerChoice == ROCK)) {
		System.out.println("PAPER covers ROCK. You win.");
		System.out.println();
		playerWin = 1;
		return playerWin;
    	} else if ((playerChoice == SCISSORS) && (computerChoice == PAPER)) {
		System.out.println("SCISSORS cut PAPER. You win.");
		System.out.println();
		playerWin = 1;
		return playerWin;
    	} else if ((playerChoice == ROCK) && (computerChoice == SCISSORS)) {
		System.out.println("ROCK breaks SCISSORS. You win.");
		System.out.println();
		playerWin = 1;
		return playerWin;
    	} else {
    		return playerWin;
    	}
    }
    
    // Prints the total wins for the computer, the player, and the total draws
    public static void printStats(String name, int totalRounds, int totalComputerWins, 
    		int totalPlayerWins, int totalDraws) {
    	System.out.println();
    	System.out.println("Number of games of ROCK PAPER SCISSORS: " + totalRounds);
    	System.out.println("Number of times Computer won: " + totalComputerWins);
    	System.out.println("Number of times " + name + " won: " + totalPlayerWins);
    	System.out.println("Number of draws: " + totalDraws);
    }
    
    // Prints a statement declaring which player is best or if they are tied
    public static void printWinner(String name, int totalComputerWins, int totalPlayerWins) {
    	if (totalComputerWins > totalPlayerWins) {
    		System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
    	} else if (totalPlayerWins > totalComputerWins) {
    		System.out.println("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
    	} else {
    		System.out.println("We are evenly matched.");
    	}
    }
}
