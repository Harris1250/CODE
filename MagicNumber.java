import java.util.Scanner;

public class MagicNumber {
	public static void main(String[] args) {

		int number = (int)(Math.random()*100);
		
		Scanner input = new Scanner(System.in);
		System.out.println("Guess a magic number between 0 and 100.");
		
		int guess = -1;
		while (guess != number) {

		System.out.print("\nEnter your guess: ");
		guess = input.nextInt();
		
		if(guess == number) {
			System.out.println("Congratulations, you guessed the number is " + number );
		} else if(guess > number) {
			System.out.println("Your guess is too high");
		} else if(guess < number){
			System.out.println("Your guess is too low");
		}
		}
		if(input != null){
		input.close();
		}
	}
}