import java.util.ArrayList;
public class RomanNumerals {
	
	static char[] language= new char[] { 'M', 'D', 'C', 'L', 'X', 'V', 'I'};
	char[] v= new char[] { 'D', 'L', 'V'};
	char[] x= new char[] { 'M', 'C', 'X', 'I'};
	static String[] tuplets= new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input= "MMMCMLII";								//Input
		char[] language= new char[] { 'M', 'D', 'C', 'L', 'X', 'V', 'I'};
		char[] v= new char[] { 'D', 'L', 'V'};
		char[] x= new char[] { 'M', 'C', 'X', 'I'};
		String[] tuplets= new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		char[] array= input.toCharArray();
		
		
																//PHASE 1: Check string to see if in language.
		int i = 0;
		while (i < array.length){
			if (isPresent(array[i], language) == false) {
				System.out.println("INVALID");
				return;
			}
			else{
				i++;	
			}
		
	}
		
		i = 0;												//PHASE 2: Check for runs.
		
		int xruncount = 0;
		int vruncount = 0;
		while (i < (array.length - 1)){
			if (array[i] == array[i+1]){
				if (isPresent(array[i], x) == true) {
					xruncount++;
				}
				if (isPresent(array[i], v) == true) {		//if run of D, L, V exceeds 1
					System.out.println("INVALID");
					return;
				}
				if (xruncount > 2 ) {						//if run of I, X, C... exceeds 3
					System.out.println("INVALID");
					return;
				}
				
			}
			
			if (array[i] != array[i+1]){			//reset counters if run is broken
				xruncount = 0;
				vruncount = 0;	
				
			}
			i++;
		}
	
	i = 0;												//PHASE 3: Check ordering (and subtractions)
	int counter1 = 0;
	int lowestcount = getSingleTuplet(array[i]);
	while (i < array.length) {
	counter1 = getSingleTuplet(array[i]);				
		if (lowestcount > counter1) {						//if weird order

			if (isTuple(array[i-1], array[i]) == false) 		//if not subtraction tuple
			{
				System.out.println("INVALID");	
			}
			
			if (isTuple(array[i-1], array[i]) == true) {		//if is subtraction tuple
				lowestcount = getSingleTuplet(array[i-1]);
				
					if (i+1 < array.length ) {
			 		
			 		if (i+ 2 < array.length) {					//if next 2 chars are tuple, make sure its ok
				 		if ((isTuple(array[1], array[i+2]) == true) )
						{
							int e = getTuple(array[i+1], array[i+2]);
							if ((e - lowestcount) < 4){
								System.out.println("INVALID");
								
								return;	
							}
							
						}
			 		}
			 		
			 		else 										//if next char is not tuple, make sure its ok
			 		{
			 			int f = getSingleTuplet(array[i+1]);
			 			int e = getTuple(array[i], array[i]);
			 			if ((f - e) < 4) {
			 				
			 				System.out.println("INVALID");
			 				
							return;	
			 			}
			 		}
			 	}
			}
		
		}
		else{
		lowestcount = getSingleTuplet(array[i]);		//set limit to what chars can be accepted
		
		
		}
		
	i++;
	}
	

		System.out.println("VALID!"); //***if passed all tests, valid***
		return;
	
	}

	
	
	public static boolean isPresent(char a, char[] b){   //checks if character is in a language array
		int j = 0;
		while (j < b.length) {
		if (a == b[j]) {
			return true;
			
		}
		else {
			j++;
		}	
		}
		
		return false;
	}
	
	
	
	public static boolean isTuple(char a, char b){				//checks if 2 chars form a subtraction tuple
		String s = new StringBuilder().append(a).append(b).toString();
		int j = 0;
		while (j < tuplets.length) {
		if (s.equals(tuplets[j])) {
			return true;
			
		}
		else {
			j++;
		}	
		}
		return false;
	
	}	
		public static int getTuple(char a, char b){						//retrieves hierarchy value of sub tuple
			String s = new StringBuilder().append(a).append(b).toString();
			int j = 0;
			while (j < tuplets.length) {
			if (s.equals(tuplets[j])) {
				return j;
				
			}
			else {
				j++;
			}	
			}
			
			return 100;	
}
		
		public static int getSingleTuplet(char a){					//retrievs hierarchy value of single char (poorly named)
			String s = new StringBuilder().append(a).toString();
			
			int j = 0;
			while (j < tuplets.length) {
			if (s.equals(tuplets[j])) {
				return j;
				
			}
			else {
				j++;
			}	
			}
			return 100;
			
		}
}
