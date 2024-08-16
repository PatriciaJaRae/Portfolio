package Main;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame{

	public static void main(String[] args){
        guessgame();
    }
        
	@SuppressWarnings("resource")
    public static void guessgame(){
        try{
            Scanner input = new Scanner(System.in);
            Random number = new Random();
            
            char playagain;
            int triestotal = 2;
            int games = 0;
            
            do{
                int tries = 1;
                
                int secretnumber = number.nextInt(100);
                secretnumber = number.nextInt(100);
                
                System.out.print("\nEnter a guess (1-100): ");
                    int guess = input.nextInt();
                    
                while(guess != secretnumber){
                
                    if(guess < secretnumber){
                        System.out.println("Too Low");
                        System.out.print("Enter a guess (1-100): ");
                            guess = input.nextInt();
                    }
                    else{
                        System.out.println("Too High");
                        System.out.print("Enter a guess (1-100): ");
                            guess = input.nextInt();
                    }
            
                tries++;
                triestotal++;
                }
                
                System.out.println("Correct!" + " Number is " + secretnumber);
                System.out.println("Number of tries: " + tries);
                
                System.out.print("Would you like to play agian? Enter y(yes) or n(no): ");
                    playagain = input.next().toLowerCase().charAt(0);
                    
                games++;
            }
            while(playagain == 'y');
            
            System.out.println("\nResults:");
            System.out.println("Total number of tries: " + triestotal);
            System.out.println("Games played: " + games);
            System.out.println("Thanks For Playing!");
        }

        catch(Exception e){
            System.out.println("\nEnter Numbers only, try again! Starting new a game...");
            guessgame();
        }
    }
}