package training.scrambledwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FileUtils;

public class Main {

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
				
		generateFlag(solution.toString());
	}
	
	private static String generateFlag(String solution) {
		String flag = "{FLG:" + utils.HashUtils.getSHA256(solution).toLowerCase() + "}";
		System.out.println("Soluzione: " + flag);
		return flag;
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
