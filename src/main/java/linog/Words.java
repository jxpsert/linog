package linog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Random;

/**
 * Utility class to get random words.
 */

public class Words {

	private static ArrayList<String> fiveLetterWords = new ArrayList<String>();
	private static ArrayList<String> twelveLetterWords = new ArrayList<String>();
	
	private static Random randomGen = new Random();
	
	private static String currentWord = "";
	private static String currentPuzzleWord = "";

	/**
	 * Loads the words from txt files into the ArrayLists
	 */
	
	public static void initWords() {
		initFiveLetterWords();
		initTwelveLetterWords();
	}

	/**
	 * Loads words from fiveletters txt file into ArrayList
	 */
	
	private static void initFiveLetterWords() {
		try {
			File file = new File("fiveletters.txt");
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				fiveLetterWords.add(data);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads words from twelvelettersletters txt file into ArrayList
	 */
	
	private static void initTwelveLetterWords() {
		try {
			File file = new File("twelveletters.txt");
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				twelveLetterWords.add(data);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a random five-letter word
	 * @return The word
	 */
	
	public static String getFiveLetterWord() {
		int index = randomGen.nextInt(fiveLetterWords.size());
		return fiveLetterWords.get(index);
	}
	
	/**
	 * Returns a random twelve-letter word
	 * @return The word
	 */
	
	public static String getTwelveLetterWord() {
		int index = randomGen.nextInt(twelveLetterWords.size());
		return twelveLetterWords.get(index);
	}
	
	/**
	 * Get the current word in the game, that the player has to guess 
	 * @return The word
	 */
	
	public static String getCurrentWord() {
		return currentWord;
	}

	/**
	 * Set the current word in the game, that the player has to guess 
	 * @param currentWord The word, of exactly 5 characters
	 */
	
	public static void setCurrentWord(String currentWord) {
		if(currentWord.length() != 5) return;
		Words.currentWord = currentWord;
		System.out.println(currentWord);
	}
	
	/**
	 * Get the current puzzleword in the game, that the player has to guess 
	 * @return The word
	 */

	public static String getCurrentPuzzleWord() {
		return currentPuzzleWord;
	}

	/**
	 * Set the current puzzleword in the game, that the player has to guess 
	 * @param currentPuzzleWord The word, of exactly 12 characters
	 */
	
	public static void setCurrentPuzzleWord(String currentPuzzleWord) {
		if(currentPuzzleWord.length() != 12) return;
		Words.currentPuzzleWord = currentPuzzleWord;
		System.out.println(currentPuzzleWord);
	}
}

