 // Jesse Alsing

 
import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.Point;
import static java.lang.Math.pow;

public class SneakyKnights{
	 
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize){
		
		// A DX DY array is used for getting all of the possible knight movements
		int DX[] = {2, 1, -1, -2, -2, -1, 1, 2};
		int DY[] = {1, 2, 2, 1, -1, -2, -2, -1};
		
		// We declare our hashset to store our initial positions that will be stored as type Point
		HashSet<Point> moveSet = new HashSet<>();
		
		// This is the bulk of our runtime and space allocation at O(n)
		for(int i = 0; i < coordinateStrings.size(); i++){
			
			// I could have just tested if it was a character and made a new array as long as it was a letter but I know i can split it 
			// with a regular expression that i learned in system software
			String[] part = coordinateStrings.get(i).split("(?<=\\D)(?=\\d)");
			
			// this is my function call to convert the bases, this will run in O(k) time
			int xval = charToInt(part[0]);
			
			//This is a fun java function that changes all the numbers in an array to their int values (in base 10)
			int yval = Integer.parseInt(part[1], 10);
			
			// I tried a few different storage methods before coming across this one that from what I read is garanteed a unique 
			// hash value
			Point startPoint = new Point(xval, yval);
		
			// We add all of our starting points to the hashSet at O(1) operation 
			moveSet.add(startPoint);
			
			// This is a constant for loop that also runs in O(k) time with O(k) space used
			for(int j = 0; j < 8; j++)
			{
				// Move in all eight directions
				int xDir = xval + DX[j];
				int yDir = yval + DY[j];
				
				// This makes sure we only make Points for resulting coordinates that are in bounds
				if(xDir <= boardSize && yDir <= boardSize && xDir >= 1 && yDir >= 1){
					
					Point moveDir = new Point(xDir, yDir);
					
					// We see if our hashSet of starting points contains one of the knights moves, if it does we return false
					if(moveSet.contains(moveDir)){						
						return false;
					}
				
				}
			}
		
		 }
		 
		// We ran all of our knights and compared if any of their moves can hit one another, if we make it here they cannot
		return true;
	}
	
	
	public static int charToInt(String xValue){
		int sum = 0, val = 0;
		// i had to do a length variable because i decrement length later and iff i just used the length it throws off the for loop
		int length = xValue.length();
		 
		for(int i = 0; i < xValue.length(); i++){
			//I know that I can just subtract the ASCII value of lower case a and add one 
			val = (xValue.charAt(i) - 'a') + 1;
			sum += val * pow(26, --length);
		 }
		 return sum;
	 }
	 
	// This was an easy assignment till I found out that some unique values as strings could hash at the same place so I used a unique
	// object to store all of our points. The space complexity wasnt too difficult to figure out and mine ended up being O(kkn) which is
	// O(n) space complexity and O(n) runtime
	public static double difficultyRating(){
	  
	  return 2.5;
	}
  
	public static double hoursSpent(){
	   
	   return 8;
	}



}