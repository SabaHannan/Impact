/**
 * 
 */
package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Saba Hannan
 * Class is responsible for getting user input
 * Class removes duplicates from the user input
 * Class manipulates user input into desired output
 * Class implements the NumberRangeSummarizer interface
 */
public class NumberRangeSummarizerClass implements NumberRangeSummarizer {

	@Override
	/**
	 * Method gets user input
	 * @param String input - The user input
	 * @return Collection<Integer> - A collection int values to manipulate
	 */
	public Collection<Integer> collect(String input) {

		Collection<Integer> cInt = new ArrayList<>();
		//Split user input at , to separate the different numbers 
		String[] words = input.split(",");

		//For each number in the string array
		for(String w : words) {

			//Convert to Integer & add to list 
			cInt.add(Integer.parseInt(w));
		}

		//Return the list
		return cInt;
	}

	@Override
	/**
	 * Method manipulates user input into desired output
	 * @param Collection<Integer> input - User input
	 * @return String - The desired output 
	 */
	public String summarizeCollection(Collection<Integer> input) {

		//Create an empty arraylist
		ArrayList<Integer> arraylist = new ArrayList<>();
		//Traverse the collection
		for(Integer i : input) {
			//Add numbers to the arraylist 
			arraylist.add(i);
		}

		//Create array of arraylist size
		Integer[] array = new Integer[arraylist.size()];
		//Convert arraylist to array object 
		Object[] obj = arraylist.toArray();
		
		//Helper function to cast from Object[] to Integer[]
		CastToInteger(obj, array);

		//Sort the array in ascending order
		Arrays.sort(array);
		
		//Remove duplicates
		int length = removeDuplicates(array);

		//Helper function to structure user input
		String output = manipulateInput(array, length);


		//If output ends with a comma - last number was sequential
		if(output.endsWith(", ") == true) {
			//Remove the comma
			output = output.substring(0, output.length()-2); 
		}

		//Return desired output 
		return output;
	}

	/**
	 * Helper function to manipulate and structure a given array
	 * @param array - Array to manipulate
	 * @return String - Manipulated and structured output
	 */
	private String manipulateInput(Integer[] array, int length) {
		String output = "";
		int counter = 0;

		//Traverse the array
		for(int i=0; i<length; i++) {

			//If on last element of the array
			if(i==array.length-1) {
				//Add the element to the output
				output += array[i].toString() + " ";
				//And stop the loop
				break;
			}

			//If not sequential 
			if(array[i+1] != array[i] + 1) {

				//Add element to output
				output += array[i].toString() + ", ";

				//If sequential
			} else if(array[i+1] == array[i] + 1 || array[i+1] == array[i]){
				//Add element to output
				output += array[i].toString() + "-";
				//Increment to next element of array
				counter++;
				//While end of array is not reached and numbers are sequential
				while(counter != array.length-1 && array[counter+1] == array[counter]+1) {
					//Go to next element
					counter++;
				}

				//Add to string the last sequential number
				output += array[counter].toString() + ", ";
				//Skip forloop to current counter
				i = counter;
			} 

			//Counter is same as index 
			counter = i;
		}
		//Return desired output 
		return output;
	}

	/**
	 * Helper function to cast Object[] to Integer[]
	 * @param obj - The Object array
	 * @param array - The array to change/cast to 
	 */
	private void CastToInteger(Object[] obj, Integer[] array) {
		//Index to traverse the array
		int index = 0;

		//For each object in object array
		for(Object o : obj) {
			//If object is an integer
			if(o instanceof Integer) {
				//Add to array by casting
				array[index] = (Integer) o;
			}
			//Go to next iteration
			index++;
		}
	}
	
	/**
	 * Helper method to remove duplicate values from the array
	 * @param array - The sorted array with duplicates 
	 * @return - The sorted array without duplicates
	 */
	private int removeDuplicates(Integer[] array) {
		
		//If array is empty or only has 1 element
		if (array.length==0 || array.length==1){  
			//Then return array's length
            return array.length;  
        }  
		
		//Create a temporary array
		Integer[] temp = new Integer[array.length];
		//Counter
        int j = 0; 
        
        //Traverse the array
        for (int i=0; i<array.length-1; i++) {  
        	
        	//If next element is not a duplicate
            if (array[i] != array[i+1]){  
            	//Then add to temporary aray
                temp[j++] = array[i];  
            }  
         } 
        
        //Add last element
        temp[j++] = array[array.length-1]; 
        
        //Change original array  
        for (int i=0; i<j; i++){  
        	array[i] = temp[i];  
        }  
        
        //Return length of new array with no duplicates
        return j;
	}
}
