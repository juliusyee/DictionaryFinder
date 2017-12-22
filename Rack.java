// Name: Julius C Yee
// USC NetID: juliusye
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * /**
 * Rack class
 * 
 * This class represents a rack of tiles in Scrabble, stored as a character
 * array with only the letters. The class contains two methods: a wrapper method and 
 * the given method to compute all of the subsets of a multiset.
 */

public class Rack 
{
	private char[] trueRack;
	
	public Rack(String letters)
	{
		//Remove the non-letters from the string and sort the characters lexicographically
		String temp = letters.replaceAll("[^a-zA-Z0-9]", "");
		trueRack = temp.toCharArray();
		Arrays.sort(trueRack);
	}
	
	/**
	* Wrapper method for the allSubsets method
	* This method prepares the parameters for the allSubsets method
	* Creates the unique string of letters present in the Rack and its appropriate multiplicities
	* stored in an integer array
	* PRE: mult.length must be at least as big as unique.length()
	*      0 <= k <= unique.length()
	* @return all subsets of a multiset in an ArrayList
	*/
	public ArrayList<String> getSubsets()
	{
		Set<Character> tracker = new HashSet<Character>();
		String uniqueString = "";
		ArrayList<Integer> mult = new ArrayList<Integer>(trueRack.length);
		ArrayList<Character> index = new ArrayList<Character>(trueRack.length);
		
		for(int i = 0; i < trueRack.length; i++)
		{
			//Letter already accounted for, so increase its multiplicity by one
			if (tracker.contains(trueRack[i]))
			{
				int pos = index.indexOf(trueRack[i]);
				mult.set(pos, mult.get(pos) + 1);
			}
			//new character, so add to string and set its multiplicity to 1
			else
			{
				tracker.add(trueRack[i]);
				uniqueString = uniqueString + trueRack[i];
				mult.add(1);
				index.add(trueRack[i]);
			}
		}
		
		//Create the final multiplicity array
		int[] multiplicity = new int[mult.size()];
		for ( int i = 0; i < mult.size(); i++)
		{
			multiplicity[i] = mult.get(i);
		}

		return(allSubsets(uniqueString, multiplicity,0));
	}
	
   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
    public static ArrayList<String> allSubsets(String unique, int[] mult, int k) 
    {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) 
      {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }   
}