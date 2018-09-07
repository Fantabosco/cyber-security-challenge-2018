package training.scrambledwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FileUtils;
import utils.HashUtils;

public class ScrambledWords {
	
	/**
	 * The SHA-256 hash of the concatenated unscrambled words will be *the content* of the flag and it needs to be converted to lowercase.
	 * To get the complete flag, insert the lowercase string obtained between “{FLG:” and “}” without any blank space after the “:” and before the “}”.
	 * Consider UTF-8 as the character encoding. Unscrambled words must have the same order of scrambled ones and they must be concatenated without spaces.
	 */

	public static void main(String[] args) {
		List<String> dictionaryFile = FileUtils.readFile("training/scrambled-words/dictionary.txt");
		List<String> scrambledWordsFile = FileUtils.readFile("training/scrambled-words/scrambled-words.txt");
		
		Map<String,String> dictionary = new HashMap<>();
		for(String word : dictionaryFile) {
			String normalizedWord = normalizeWord(word);
			if(normalizedWord.length() > 0) {
				dictionary.put(normalizedWord, word);
			}
		}
		List<String> scrambledWords = new ArrayList<>();
		for(String word : scrambledWordsFile) {
			String normalizedWord = normalizeWord(word);
			if(normalizedWord.length() > 0) {
				scrambledWords.add(normalizedWord);
			}
		}
		
		StringBuilder solution = new StringBuilder();
		for(String scrambled : scrambledWords) {
			if(dictionary.containsKey(scrambled)) {
				solution.append(dictionary.get(scrambled));
			}
		}
				
		HashUtils.generateFlag(solution.toString());
	}

	/**
	 * Trim the input and sort the string characters alphabetically
	 */
	private static String normalizeWord(String word) {
		String ret = word.trim();
		List<Character> list = new ArrayList<>();
		for(char c : ret.toCharArray()) {
			list.add(c);
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(Character c : list) {
			sb.append(c);
		}
		return sb.toString();
	}
}
