package Main;
import java.util.Scanner;
/*import java.util.Arrays;*/

public class Takeout{

	public static void main(String[] args){
        menu();
    }
   
    @SuppressWarnings("resource")
    public static void menu(){
		Scanner input = new Scanner(System.in);

        System.out.println("\nWelcome!");
        System.out.println("1. Burger ($2.00)");
        System.out.println("2. Fries ($1.50)");
        System.out.println("3. Soda ($1.00)");
        System.out.println("4. Done! ");
        System.out.print("What would you like, choose a number: ");
        
        try{
            double price;
            double subtotal = 0;
            double total = 0;
            
            int order = input.nextInt();
            
            while(order <= 3){

                if(order == 1){
                    System.out.println("You've added a Burger");
                    price = 2.00;
                }
                else if(order == 2){
                    System.out.println("You've added Fries");
                    price = 1.50;
                }
                else{
                    System.out.println("You've added a Soda");
                    price = 1.00;
                }
                
                System.out.print("Enter quantity: ");
                    int quantity = input.nextInt();
            
                subtotal = price * quantity;
                System.out.println("Subtotal: " + subtotal);
                    
                System.out.println("\n1. Burger ($2.00)");
                System.out.println("2. Fries ($1.50)");
                System.out.println("3. Soda ($1.00)");
                System.out.println("4. Done! ");
                System.out.print("What would you like, choose a number: ");
                    order = input.nextInt();
                
            }

            if(order > 4){
                System.out.println("\nThat is not an option on the menu!");
                menu();
            }
            
            total = subtotal + subtotal;
            System.out.println("Enjoy your meal!");
            System.out.println("Total: " + total);
        }

        catch(Exception e){
            System.out.println("\nNot a number! Try again!");
            menu();
        }
    }
}