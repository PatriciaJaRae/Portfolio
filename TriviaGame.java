package Main;

import java.util.Scanner;

public class TriviaGame{

	int[] SIZE = new int[5];

	@SuppressWarnings("resource")
	public static void run(){
		Scanner input = new Scanner(System.in);
			//these three arrays store the number of questions, answers and points
			String[] questions = new String[5];
			String[] answers = new String[5];
			int[] points = new int[5];

			initialize(questions, answers, points); //this calls the initialize method with the arrays
			boolean more = true;
			char playagain; //declares the playagain scanner input
			
			do{
				while(more){
					play(questions, answers, points);
					System.out.print("Is there another player? ");
					break; //updates the more variable
				}
				playagain = input.next().toLowerCase().charAt(0);
			} 
			while(playagain == 'y'); //the do statement is excuted and the program starts over if satisfied
        	
			System.out.print("\nThanks for playing!");
	}

	public static void initialize(String[] questions, String[] answers, int[] values){
		//this method stores each of the questions, answers, and points
		questions[0] = "When PC gaming, what are the basic movement keys (forward, left, back, right)? ";
		answers[0] =  "wasd";
		values[0] = 1;

		questions[1] = "What are the common colors of the Y, X, B, and A buttons (in that order) on an Xbox controller? ";
		answers[1] = "yellow, blue, red, green"; //holds answers
		values[1] = 2;

		questions[2] = "In Telltale Games The Walking Dead Season 1, where does the game take place (City, State)? ";
		answers[2] = "Savanna, Georgia";
		values[2] = 3; //holds the amount of points

		questions[3] = "How many buttons are on a ps4 conntroller? ";
		answers[3] =  "eighteen";
		values[3] = 1;

		questions[4] = "Who is the main character in Mafia III and what year does the game take place in (First & Last Name, year)? ";
		answers[4] =  "Lincon Clay, 1968";
		values[4] = 3;

	}

	@SuppressWarnings("resource")
	public static int play(String[] questions, String[] answers, int[] values){
		Scanner input = new Scanner(System.in);
			int score = 0; //set to 0 to be added
			
			for(int i = questions.length; i <= 5; i++){
				System.out.println("\nQuestion 1 (1 Point)");
				System.out.print(questions[0]);
				    String guess = input.nextLine().toLowerCase(); //converts input to lower case
				if(guess.equals(answers[0])){
					System.out.println("That is correct!");
					score = values[0] + score; 
				} 
				else{
					System.out.println("Wrong. The correct answer is " + answers[0]);
				}

				System.out.println("\nQuestion 2 (2 Points)");
				System.out.print(questions[1]);
				    guess = input.nextLine().toLowerCase();
				if(guess.equals(answers[1])){ //checks if guess equals answer stored in given array
					System.out.println("That is correct!");
					score = values[1] + score;
				} 
				else{
					System.out.println("Wrong. The correct answer is " + answers[1]);
				}

				System.out.println("\nQuestion 3 (3 Points)");
				System.out.print(questions[2]);
				    guess = input.nextLine();
				if(guess.equals(answers[2])){
					System.out.print("That is correct!\n");
					score = values[2] + score; //adds score variable to given values array
				} 
				else {
					System.out.println("Wrong. The correct answer is " + answers[2]);
				}

				System.out.println("\nQuestion 4 (1 Points)");
				System.out.print(questions[3]);
				    guess = input.nextLine().toLowerCase();
				if(guess.equals(answers[3])){
					System.out.println("That is correct!");
					score = values[3] + score;
				}
				else{
					System.out.println("Wrong. The correct answer is " + answers[3]);
				}

				System.out.println("\nQuestion 5 (3 Points)");
				System.out.print(questions[4]);
				    guess = input.nextLine();
				if(guess.equals(answers[4])){
					System.out.println("That is correct!");
					score = values[4] + score; 
				} 
				else{ //if guess dosnt satisfy if statement this output is displayed
					System.out.println("Wrong. The correct answer is " + answers[4]);
				}
			}
			
			if(score == 10){
				System.out.println("\nYour Score is: " + score + "/10 " + "(" + score + "0%) Perfect! You win a prize!");
				score = score/10;
				return score;
			}

			if(score == 0){
				System.out.println("\nYour Score is: " + score + "/10 " + "(" + score + "%) You failed bad!");
				score = score/10;
				return score;
			}

			else{
				System.out.println("\nYour Score is: " + score + "/10 " + "(" + score + "0%)");
				score = score/10;
				return score;
			}
	}
}
