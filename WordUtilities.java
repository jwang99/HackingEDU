import java.util.Scanner;

/**
 *  Finds all the words that can be formed with a list of letters,
 *  then finds the word with the highest Scrabble score.
 *
 */
 
public class WordUtilities
{
	private static Scanner infile;
	private static String infileName = "wordlist.txt";
	private static int wordCount = 1;
	private static int currentTopCombo = 0;
	private static String currentTopString = "";
	private static String [] word = new String [90948];
	
	public static void main (String [] args)
	{
		String input = getInput();
		findAllWords(input);
		printWords();
		
		// Score table in alphabetic order according to Scrabble
		int [] scoretable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoretable);
		System.out.println("HELLO\n\n\n" + best + "\n\n\n");
	}
	
	/**
	 *  Enter a list of 3 to 12 letters. It also checks
	 *  all letters to insure they fall between 'a' and 'z'.
	 *
	 *  @return  A string of the letters
	 */
	public static String getInput ( )
	{
		String userInput = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces ");

		return userInput;
	}
	
	/**
	 *  Find all words that can be formed by a list of letters.
	 *
	 *  @param letters  String list of letters
	 *  @return   An array of strings with all words found.
	 */
	public static String [] findAllWords (String letters)
	{		
		infile = OpenFile.openToRead(infileName);
		int cat = 1;
		
		while(infile.hasNext()){
			String currentWord = infile.next();
			if(wordMatch(currentWord, letters)){
				word[cat] = currentWord;
				cat++;
			}
		}
		
		return word;
	}
	
	/**
	 *  Determines if a word can be formed by a list of letters.
	 *
	 *  @param temp  The word to be tested.
	 *  @param letters  A string of the list of letter
	 *  @return   True if word can be formed, false otherwise
	 */
	public static boolean wordMatch (String temp, String letters)
	{
		for(int i=0;i<temp.length();i++){
			int ind = letters.indexOf(temp.charAt(i));
			if(ind == -1)
					return false;
			letters = letters.substring(0,ind) + letters.substring(ind+1);
        }
        return true;
	}
	
	/**
	 *  Print the words found to the screen.
	 *
	 *  @param word  The string array containing the words.
	 */
	public static void printWords ()
	{
		int length = 0;
		for(int arrayCount = 1; word[arrayCount] != null;  arrayCount++){
				System.out.printf("%s\t", word[arrayCount]);
				if(arrayCount%5 == 0){
					System.out.println();
				}	
		}
	}
	
	/**
	 *  Finds the highest scoring word according to Scrabble rules.
	 *
	 *  @param word  An array of words to check.
	 *  @param scoretable  An array of 26 integer scores in letter order.
	 *  @return   The word with the highest score.
	 */
	public static String bestWord (String [] word, int [] scoretable)
	{
		for(wordCount = 0; wordCount<word.length; wordCount++){
			int right = getScore(word[wordCount], scoretable);
					System.out.println(right);

			if(right > currentTopCombo) {
				currentTopCombo = right;
				currentTopString = word[wordCount];
			}
		}
		return currentTopString;
	}
	
	/**
	 *  Calculates the score of a word according to Scrabble rules.
	 *
	 *  @param word  The word to score
	 *  @param scoretable  The array of 26 scores for alphabet.
	 *  @return   The integer score of the word.
	 */
	public static int getScore (String word, int [] scoretable)
	{
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
				int determineWordTotal = 0;
			for(int letterCount = 0; letterCount < word.length(); letterCount ++){
				for(int scoreCount = 0; scoreCount < 26; scoreCount++){
					if(alphabet[scoreCount] == word.charAt(letterCount)){
						determineWordTotal+= scoretable[scoreCount];
					}
				}
			}	
		return determineWordTotal;	
	}
}