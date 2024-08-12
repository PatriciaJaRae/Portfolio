package Main;
import java.util.Scanner;

public class ConvertTemp{
    public static void main(String[] args){
        try (Scanner input = new Scanner(System.in)) {
			{
				boolean more = true;
				char menu = 0;
				
				do{
					while(more){
						System.out.print("------------------------------------------------------------------------------");
						System.out.println("\n|Celsius to Fahrenheit calculator (1) or Fahrenheit to Celsius calculator (2)|" + "\n------------------------------------------------------------------------------");
							int calculator = input.nextInt();
						
						do{
							while(calculator == 1){
								System.out.print("\nCelsius to Fahrenheit Calculator" + "\n-----------------------------------");
								System.out.print("\nEnter a Celsius degree: ");
									int celsiusDegree = input.nextInt();
   
									celsiusConvert(celsiusDegree);
					
									System.out.println("\n" + celsiusDegree + "°C = " + celsiusConvert(celsiusDegree) + "°F");
									System.out.print("\nBack to Menu? y(yes)/n(no): ");
							
								break;
							}
							
							while(calculator == 2){
								System.out.print("\nFahrenheit to Celsius Calculator" + "\n-----------------------------------");
								System.out.print("\nEnter a Fahrenheit degree: ");
									int fahrenheitDegree = input.nextInt();
   
									fahrenheitConvert(fahrenheitDegree);
					
									System.out.println("\n" + fahrenheitDegree + "°F = " + fahrenheitConvert(fahrenheitDegree) + "°C");
									System.out.print("\nBack to Menu? y(yes)/n(no): ");
							
								break;
							}

							menu = input.next().toLowerCase().charAt(0);
						} while(menu == 'n');
						
						break;
					}
				} while(menu == 'y');
			}
		}
    }
    
    public static float celsiusConvert(float celsiusDegree){
    	float fahrenheit = (celsiusDegree * 1.8f) + 32f;
        return fahrenheit;
    }
    
    public static float fahrenheitConvert(float fahrenheitDegree){
		float celsius = (fahrenheitDegree - 32) / 1.8f;
		return celsius;
	}
}