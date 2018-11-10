import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class WordLinksPuzzleGame {

	public static ArrayList readDictionary() {
		ArrayList<String> dictionaryList = new ArrayList<String>();
		try {
			FileReader reader = new FileReader("words.txt");
			BufferedReader bReader = new BufferedReader(reader);
			boolean end = false;
			while (!end) {
				String word = bReader.readLine();
				if (word == null)
					end = true;
				else {
					dictionaryList.add(word);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dictionaryList;
	}

	public static String[] readWordList(String list) {
		String[] wordList = list.split(",");
		return wordList;
	}

	public static boolean isUniqueList(String[] list) {
		boolean unique = true;
		for (int i = 0; i < list.length; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list[i].equals(list[j]))
				{
					unique = false;
					break;
				}
			}
		}
		return unique;
	}

	public static boolean isEnglishWord(String word) {
		boolean isEnglishWord = false;
		ArrayList<String> dictionary = readDictionary();
		int binarySearch = binarySearch(dictionary, word);
		if (binarySearch < 0)
			isEnglishWord = false;

		else
			isEnglishWord = true;

		return isEnglishWord;
	}

	public static int binarySearch(ArrayList<String> dictionary, String key) {
		int start = 0;
		int end = dictionary.size() - 1;
		int mid = (start + end) / 2;
		while (start <= end && !dictionary.get(mid).equalsIgnoreCase(key)) {
			if (dictionary.get(mid).compareTo(key) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}

			mid = (start + end) / 2;
			if (start > end) {
				mid = -1;
			}
		}
		return mid;
	}

	public static boolean isDifferentByOne(String str1, String str2) {
		boolean different = false;
		char[] charArray1 = str1.toCharArray();
		char[] charArray2 = str2.toCharArray();
		int count = 0;
		if (charArray1.length == charArray2.length) {
			for (int i = 0; i < charArray1.length; i++) {
				if (charArray1[i] != charArray2[i])
					count++;
			}
			if (count == 1)
				different = true;
		}
		return different;
	}

	public static boolean isWordChain(String[] wordList) {
		if(isUniqueList(wordList) == true)
		{
			for(int j = 0; j<wordList.length; j++)
			{
				if(isEnglishWord(wordList[j]) == false)
					break;
				else
				{
					for(int i = 0; i<wordList.length; i++)
					{
						String str1 = wordList[i];
						String str2 = wordList[i+1];
						if(isDifferentByOne(str1, str2) == true)
							return true;
						else
							break;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		boolean listEntered = true;
		boolean notEnglish = false;
		readDictionary();
		while(listEntered)
		{
			System.out.println("Enter a comma seperated list of words (or an empty list by clicking the space bar to quit)");
			String words = input.nextLine();
			if(words.equals(" "))
			{
				listEntered = false;
			}
			else
			{
				String[] list = readWordList(words);
				if(isWordChain(list) == true)
					System.out.println("Valid chain of words from Lewis Carroll's word-links game.");

				else
					System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
			}
		}
	}

}
