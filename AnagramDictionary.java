// Name: Julius C Yee
// USC NetID: juliusye
// CS 455 PA4
// Fall 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;



/**
 * AnagramDictionary class
 * 
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary 
{
	private Map<String, HashSet<String>> dictionary;
	
    /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.
    * The anagram dictionary is a HashMap with the lexicographic multiset of letters as the key
    * and a HashSet of words that are anagrams of the key as the entry value.
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException 
   {
	   dictionary = new HashMap<String,HashSet<String>>();
	   
	   //Scanner object for the file
	   File inputFile = new File(fileName);
	   Scanner in = new Scanner(inputFile);
	   
	   //Sort each word lexicographically and organize it with its anagrams
	   while(in.hasNext())
	   {
		   String originalString = in.next();
		   String sortedString = sortString(originalString);
		   if (dictionary.containsKey(sortedString))
		   {
			   dictionary.get(sortedString).add(originalString);
		   }
		   else
		   {
			   HashSet<String> newSet = new HashSet<String>();
			   newSet.add(originalString);
			   dictionary.put(sortedString,newSet);
		   }
	   }
	   in.close();
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) 
   {
	   ArrayList<String> anagrams = new ArrayList<String>();
	   String keyToSearch = sortString(s);
	   Rack rack = new Rack(keyToSearch);
	   
	   //Obtain all subsets of the rack
	   ArrayList<String> subsets = rack.getSubsets();
	   
	   //For each subset, add all of its anagrams to the ArrayList to be returned
	   for (int i = 0; i < subsets.size(); i++)
	   {
		   if (dictionary.containsKey(subsets.get(i)))
		   {
			   HashSet<String> vals = dictionary.get(subsets.get(i));
			   for (String word : vals) 
			   {
				    anagrams.add(word);
				}
		   }
	   }
	   
       return anagrams;
   }
   
   /**
    * Sorts a string lexicographically 
    * E.g. lebron will be sorted into belnor
    * @param original, string to sort
    * @return sorted string
    */
   public String sortString(String original)
   {
	   char[] arr = original.toCharArray();
	   Arrays.sort(arr);
	   return (new String(arr));
   }
   
   /**
    * Sorts a string lexicographically 
    * E.g. lebron will be sorted into belnor
    * @param rack, the original user input
    * @param anagrams, ArrayList of the anagrams of rack
    */
   public void displayResults(ArrayList<String> anagrams, String rack)
   {
	   WordScores[] result = new WordScores[anagrams.size()];
	   for (int i = 0; i < result.length; i++)
	   {
		   String wordToAdd = anagrams.get(i);
		   result[i] = new WordScores(ScoreTable.calculateScore(wordToAdd),wordToAdd);
	   }
	   
	   Arrays.sort(result,new WordScoreComparator());
	   
	   //Display the desired output of scores + anagram words in the order
	   System.out.println("We can make "+ result.length + " words from " + "\"" + sortString(rack) + "\"" );
	   if (result.length != 0)
	   {
		   System.out.println("All of the words with their scores (sorted by score):");
		   for(int j = 0; j < result.length; j++)
		   {
			   System.out.println(result[j].score + ": " + result[j].word);
		   }
	   }
   }
   
   //Inner class to store a word with its associated score
   public class WordScores 
   {
	   private int score;
	   private String word;
	   
	   public WordScores(int s, String w)
	   {
		   score = s;
		   word = w;
	   }
	   
	   public int getScore()
	   {
		   return score;
	   }
	   
	   public String getWord()
	   {
		   return word;
	   }
   }
   
   //inner class that implements Comparator<WordScores> such that the WordScores can be sorted
   //by decreasing order of scores (alphabetical order of words if scores are tied)
   public class WordScoreComparator implements Comparator<WordScores>
   {
	   @Override
	   public int compare(WordScores w1, WordScores w2)
	   {
		   int diff = w2.score - w1.score;
		   if (diff == 0)
		   {
			   return(w1.word.compareTo(w2.word));
		   }
		   return diff;
	   }
   }
}