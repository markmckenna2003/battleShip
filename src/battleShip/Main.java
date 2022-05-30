package battleShip;
import java.util.Random;
import java.util.Scanner;

public class Main 
{
	//Using scanner in main class to be accessible by any method in the class;
	static Scanner scan = new Scanner(System.in);
	//Using userName to store for later uses and static to uses it in any method;
	static String userName;
	//these ints will be used for overall score throughout the game;
	static int userTotalWins;
	static int compterScore;
	//imported random as a static because it will be used a lot in methods;
	static Random ran = new Random();
	static boolean gameOver;
	static char[][] computerGrid = {{' ','1','2','3','4'},
			{'1','-','-','-','-'},
			{'2','-','-','-','-'},
			{'3','-','-','-','-'},
			{'4','-','-','-','-'}};

	static char[][] playerGrid = {{' ','1','2','3','4'},
			{'1','-','-','-','-'},
			{'2','-','-','-','-'},
			{'3','-','-','-','-'},
			{'4','-','-','-','-'}};
	//these ints will be used right away and will need to be used by most methods;
	static int computerRow = ran.nextInt(4 - 1 + 1) + 1;
	static int computerCol = ran.nextInt(4 - 1 + 1) + 1;
	static int playerRow = ran.nextInt(4 - 1 + 1) + 1;
	static int playerCol = ran.nextInt(4 - 1 + 1) + 1;
	//this userGameScore will be for the game score ;
	static int userGameScore = 0;

	//this method will be the main and everything will start here; 
	public static void main(String[] args) 
	{	
		System.out.println("Enter Your Name OR Whatever You Like:");
		//this stores the userName;
		userName = scan.nextLine();
		//this allows the start;
		gameOver = false;
		System.out.println("Welcome to BattleShip, Don't tell Hasbro\nPlay As Long As You Like " + userName + " ;) \n");
		//this will loop until the game makes it true which then the option for to continue will so and change it back to false;
		while(gameOver == false)
		{
			//this will run nextRoundMethod;
			nextRound();

		}



	}

	public static void nextRound()
	{
		//this will run display grid;
		displayGrids();


		//player's turn
		System.out.println("Enter row to bomb");
		int row = scan.nextInt();
		//this checks that the int is the correct value if not it will run again;
		for(int i = 0; i < 2; i++) {
			if(row > 4) {
				System.out.println("Incorrect Value, Try Again");
				row = scan.nextInt();
				//this will cause the loop to reset if the int is not right;
				i--;
			}
		}
		//this will follow the exact same way as row;
		System.out.println("Enter column");
		int col = scan.nextInt();
		for(int i = 0; i < 2; i++) {
			if(col > 4) {
				System.out.println("Incorrect Value, Try Again");
				col = scan.nextInt();
				i--;
			}

		}
		//this will change the row that user selects to change to "x";
		computerGrid[row][col] = 'x';
		//this will add to the users in game score when a turn is taken;
		userGameScore++;
		//this checks if the player wins 
		if(row == computerRow && col == computerCol) {
			displayGrids();
			System.out.println("YOU WIN, You Hit Row " + computerRow + " and Hit Coloum " + computerCol +"\n Your Score for the game is " + (userGameScore-1) + " misses before you hit the ship");
			//turns the game to over;
			gameOver = true;
			//this adds the win to user score;
			userTotalWins++;
			System.out.println(userName + " Scored " + userTotalWins + "  VS Computer Scored " + compterScore + "\n");
			System.out.println("If You would like to continue press 1 \nIf you want to exit press anyother number:");
			//this will check if the user wants to continue if not it will exit with a message;
			int userContinue = scan.nextInt();
			if(userContinue == 1){
				//this will start the game all over;
				gameOver = false;
				resetGame();
				nextRound();
			}
			else {
				System.out.println("Thanks for playing " + userName);
				System.exit(0);}
		}


		//enemy's turn
		//this gets the computer random columns and rows;
		int computerRandomGuessRow = ran.nextInt(4 - 1 + 1) + 1;
		int computerRandomGuessCol = ran.nextInt(4 - 1 + 1) + 1;
		//changes the players board;
		playerGrid[computerRandomGuessRow][computerRandomGuessCol] = 'x';
		//this functions the exact way as players except for the computerscore is changed and the message;
		if(computerRandomGuessRow == playerRow && computerRandomGuessCol == playerCol) {
			displayGrids();
			System.out.println("YOU LOST, Computer Hit Row " + playerRow + " and Hit Coloum " + playerCol);
			gameOver = true;
			compterScore++;
			System.out.println(userName + " Scored " + userTotalWins + "  VS Computer Scored " + compterScore + "\n");
			System.out.println("If You would like to continue press 1 \nIf you want to exit press anyother number:");
			int userContinue = scan.nextInt();
			if(userContinue == 1){

				gameOver = false;
				resetGame();
				nextRound();
			}
			else {
				System.out.println("Thanks for playing " + userName);
				System.exit(0);}
		}


	}
	//this method changes all the values back to default;
	public static void resetGame(){

		char[][] computerGrid2 = {{' ','1','2','3','4'},
				{'1','-','-','-','-'},
				{'2','-','-','-','-'},
				{'3','-','-','-','-'},
				{'4','-','-','-','-'}};

		char[][] playerGrid2 = {{' ','1','2','3','4'},
				{'1','-','-','-','-'},
				{'2','-','-','-','-'},
				{'3','-','-','-','-'},
				{'4','-','-','-','-'}};
		//this changes these grids above to the regular grids;
		computerGrid = computerGrid2;
		playerGrid = playerGrid2;
		//this gets all the new values for the new game
		int computerNewCol = ran.nextInt(4 - 1 + 1) + 1;
		int computerNewRow = ran.nextInt(4 - 1 + 1) + 1;
		int playerNewCol = ran.nextInt(4 - 1 + 1) + 1;
		int playerNewRow = ran.nextInt(4 - 1 + 1) + 1;

		computerRow = computerNewCol;
		computerCol = computerNewRow;
		playerRow = playerNewRow;
		playerCol = playerNewCol;
		//reset the score in game;
		userGameScore = 0;

	}
	//this method displays the current state of the grids;
	public static void displayGrids()
	{
		//this shows the player where his boat is;
		playerGrid[playerCol][playerRow] = 'O';

		System.out.println(userName + " Board");
		//this goes through each ;line of playerGrid;
		for(char[]  line : playerGrid)
		{
			//this print every character in the playersGrid lines at a time;
			for(char  symbol : line)
			{
				System.out.print(symbol);
			}
			System.out.print("\n");
		}//the computer works the same as the players
		System.out.println("Computer's Board");
		for(char[]  line : computerGrid)
		{
			for(char  symbol : line)
			{
				System.out.print(symbol);
			}
			System.out.print("\n");
		}

	}

}