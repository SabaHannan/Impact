import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import numberrangesummarizer.NumberRangeSummarizerClass;

/**
 * @author Saba Hannan
 * Main class gets user input
 * Main class displays manipulated output 
 *
 */
public class Main {

	/**
	 * Main method asks user for input
	 * Creates instance of summarizer class
	 * Manipulates and displays output to user
	 * @param args
	 */
	public static void main(String[] args) {
		//Ask user for input
		System.out.println("Please enter a range of numbers as a comma separated list with no spaces:");
		//Get user response using helper method
		String input = "";
		input = getUserInput();

		//Create an instance of summarizer class
		NumberRangeSummarizerClass nrsc = new NumberRangeSummarizerClass();
		//Convert user input to collection of int
		Collection<Integer> cInt = new ArrayList<>();
		cInt = nrsc.collect(input);
		//Manipulate user input
		String output = nrsc.summarizeCollection(cInt);
		//Display manipulated output
		System.out.println("The output is: " + output);

	}
	
	/**
	 * Helper method
	 * Reads user input from console
	 * @return String - user input read in from console
	 */
	private static String getUserInput() {
		String input = "";
		//If user types in wrong input or doesn't type in input
		String defaultInput = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			//Read the input line
			input = br.readLine();
			//Close the reader 
			br.close();
			
			//Catch any errors
		} catch (IOException e) {
			System.out.println("You did not enter correct input.");
		}
		
		//If user input is not digits separated by commas
		if(!(input.matches("([0-9]{1,},{0,1})*"))) {
			
			//Inform the user
			System.out.println("Your input did not meet the constraints, please try again. The input was changed to a default value.");
			//Then change input to default input
			input = defaultInput;
		}
		
		//Return user input
		return input;
	}
}
