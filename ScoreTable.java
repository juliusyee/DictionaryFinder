// Name: Julius C Yee
// USC NetID: juliusye
// CS 455 PA4
// Fall 2017

/**
 * ScoreTable class
 * 
 * This class stores the information about Scrabble score for each letter. It also
 * contains one static method that will calculate the score for any word.
 */

public class ScoreTable
{
	static int[] scores = new int[] {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	
	 /**
	    * This static method calculates the Scrabble score of the string word based
	    * on each character and its associated score value
	    * PRE: word must only contain letters
	    * @param word: the string of letters to be calculated
	    * @return the Scrabble score for the given string
	    */
	public static int calculateScore(String word)
	{
		int score = 0;
		for (int i = 0; i < word.length(); i++)
		{
			if(Character.isUpperCase(word.charAt(i)))
			{
				score += scores[word.charAt(i) - 'A'];
			}
			else
			{
				score += scores[word.charAt(i) - 'a'];
			}
		}
		return score;
	}
}