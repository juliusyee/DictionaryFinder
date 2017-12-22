// Name: Julius C Yee
// USC NetID: juliusye
// CS 455 PA4
// Fall 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFinder class
 * 
 * Program to read in a file that contains a dictionary of words given through the command
 * line; if no file is entered, the "sowpods.txt" file is used
 * This contains the main method that is responsible of processing the command line argument and
 * handle any error processing (specifically FileNotFoundException) 
 */

public class WordFinder
{
	public static void main(String[] args)
	{
		String fileName = "";
		try
		{
			if (args.length < 1) 
	         {
	            fileName = "sowpods.txt";
	         }
			else
			{
				fileName = args[0];
			}
			
			AnagramDictionary ad = new AnagramDictionary(fileName);

			//Main Program Loop
			Scanner in = new Scanner(System.in);
			
			System.out.println("Type . to quit.");
			for(;;)
			{
				System.out.print("Rack? ");
				String input = in.next();
				if (input.equals("."))
				{
					break;
				}
				
				//Obtains the all the anagrams and displays them appropriately
				ArrayList<String> anagramsList = ad.getAnagramsOf(input);
				ad.displayResults(anagramsList, input);
			}
			in.close();
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("ERROR: File not found: " + fileName);
		}
	}
}