package w4d2StudyHall;

import java.util.Scanner;

public class W4D2StudyHallExercise {
	/* Replace this line with a proper header which includes your name */

	private static String usersName;
	private static String inputFromUser;
	private static char menuCharacter;

	public static void main(String args[]) {
		/* Initialize the Text Hangman game */
		Scanner keyboard = new Scanner(System.in);
System.out.println("Welcome!");
		/* Welcome the user and request the user to enter a name */
System.out.println("Please enter your name followed by a return");
		/* Read in the user's name */
		/* Display menu */

	usersName = keyboard.nextLine();
System.out.println(usersName + ",please enter a command letter followed by a return!");
System.out.println("p -> Play the game");
System.out.println("s -> View statistics");
System.out.println("q -> Quit");

		while (true) {
			/* Read the menu character */	
{
menuCharacter = keyboard.nextLine().charAt(0);
if (menuCharacter == 'p')
	System.out.println("Play the game command recognized.");
	System.out.println(usersName + ",please enter a command letter followed by a return!");
	System.out.println("p -> Play the game");
	System.out.println("s -> View statistics");
	System.out.println("q -> Quit");
}
menuCharacter = keyboard.nextLine().charAt(0);
if (menuCharacter == 's')
{
		System.out.println("View the statistics command recognized.");
		System.out.println(usersName + ",please enter a command letter followed by a return!");
		System.out.println("p -> Play the game");
		System.out.println("s -> View statistics");
		System.out.println("q -> Quit");
}
menuCharacter = keyboard.nextLine().charAt(0);
if (menuCharacter == 'q')
{
	System.out.println("Quit the game command recognized.");
	System.out.println( "Thanks for playing, "+ usersName);
	break;		
}
else {
	System.out.println("*** Error ***");
	System.out.println("Invalid menu item entered. It must be p, s, or q");
	System.out.println("Invalid menu item entered. It must be p, s, or q");
	System.out.println("The input found was:" );
	System.out.println(usersName + ",please enter a command letter followed by a return!");
	System.out.println("p -> Play the game");
	System.out.println("s -> View statistics");
	System.out.println("q -> Quit");
}
}
}
}
