package challenge.weird_unsorted_data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtils;
import utils.HashUtils;

public class WeirdUnsortedData {

	public static void main(String[] args) {

		String file = FileUtils.readFile("challenge/weird_unsorted_data/weird-unsorted-data.txt").get(0);

		solution4(file);
	}

	/**
	 * v4 - group by SHA-256 hashes Looked for hex strings of 64 chars
	 */
	private static void solution4(String file) {
		// Generate SHA-256 rainbow table (min and max values has been tuned)
		Map<String, Long> sha256RaimbowTable = new HashMap<>();
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
		}
		for (long i = 0; i <= 5831097; i += 1) {
			byte[] hash = digest.digest(String.valueOf(i).getBytes(StandardCharsets.UTF_8));
			sha256RaimbowTable.put(HashUtils.bytesToHex(hash), i);
		}

		// Tokenize file, extracting hashes and related content
		Map<Long, String> content = new HashMap<>();
		Pattern sha256HashPattern = Pattern.compile("[0-9a-f]{64}");
		Matcher m = sha256HashPattern.matcher(file);
		String lastHash = null;
		int lastHashStart = 0;
		while (m.find()) {
			if (lastHash != null) {
				if (sha256RaimbowTable.containsKey(lastHash)) {
					Long sortIndex = sha256RaimbowTable.get(lastHash);
					if(content.containsKey(sortIndex)) {
						System.err.println("Sort index already found: " + sortIndex);
						System.exit(1);
					}
					// Store previous hash
					content.put(sortIndex, file.substring(lastHashStart + 64, m.start()));
					if(m.end() - m.start() != 64) {
						System.err.println("Hash found with wrong length: " + lastHash);
						System.exit(1);
					}
				} else {
					System.err.println("Hash found but not decrypted: " + lastHash);
					System.exit(1);
				}
			}
			lastHash = m.group();
			lastHashStart = m.start();
		}
		// Store last hash
		if (sha256RaimbowTable.containsKey(lastHash)) {
			content.put(sha256RaimbowTable.get(lastHash), file.substring(lastHashStart, file.length()));
		} else {
			System.err.println("Hash found but not decrypted: " + lastHash);
			System.exit(1);
		}
		sha256RaimbowTable = null;

		System.out.println("Matches found:" + content.size());
		
		// Sort hashed decoded values
		List<Long> sortOrderIndexes = Arrays.asList(content.keySet().toArray(new Long[0]));
		Collections.sort(sortOrderIndexes);
		System.out.println("Min sortOrderIndex: " + sortOrderIndexes.get(0));	// 9
		System.out.println("Max sortOrderIndex: " + sortOrderIndexes.get(sortOrderIndexes.size() - 1));	// 5831097

		// Print solution
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortOrderIndexes.size(); i += 43210) {
			sb.append(content.get(sortOrderIndexes.get(i)));
		}
		System.out.println("Solution 4.a:" + sb.toString());
		
		sb = new StringBuilder();
		for (int i = 0; i < sortOrderIndexes.size(); i += 43210) {
			sb.append(content.get(sortOrderIndexes.get(i)));
		}
		String solution = sb.toString();
		solution = solution.replaceAll("#", "");
		solution = solution.replaceAll("§", "");
		solution = solution.replaceAll("hashedSortIndex", "");
		solution = solution.replaceAll("data", "");
		System.out.println("Solution 4.b:" + solution);
	}

	/**
	 * v3 - group by Hash Looked for: 0, 1, a, {, FLG, hashedSortIndex
	 */
	private static void solution3(String file) {
		String RipeMD = "32df93d5a12952b01beef693be65cb20446bf7df1bcfcb9161d38f262938f8ac";
		String SHA3 = "65d1a7b1faa6d5730ee472fda41e19adcc952b88d507149461b774633bbfdffa";
		String MD6 = "1874efd2a49c5f18eeb040cc93d765be6bddcea8bb3800c2d41300c45a5ff777";
		String SHA = "7343b5be191520cbde75c3b3a479ceb12fa3a7c0f4c88aad86bd64725d4ab3ba";
		List<String> list = new ArrayList<>();
		StringBuilder sb = null;
		for (int i = 0; i < file.length(); i++) {
			char c = file.charAt(i);
			if (c == '#' && (sb == null || sb.charAt(sb.length() - 1) != '#')) {
				if (sb != null) {
					String s = sb.toString();
					list.add(s);
					s = s.replace("#", "");
					if (s.length() == 64 && (s.equals(RipeMD) || s.equals(SHA3) || s.equals(MD6) || s.equals(SHA))) {
						System.out.println("Found");
					}
				}
				sb = new StringBuilder();
			}
			sb.append(c);
		}
		file = null;
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(final String a, String b) {
				return a.split("#").length - b.split("#").length;
			}
		});
		for (int i = 0; i < list.size(); i += 43210) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * v2 - Group by # string length
	 */
	private static void solution2(String file) {
		List<String> list = new ArrayList<>();
		StringBuilder sb = null;
		for (int i = 0; i < file.length(); i++) {
			char c = file.charAt(i);
			if (c == '#' && (sb == null || sb.charAt(sb.length() - 1) != '#')) {
				if (sb != null) {
					list.add(sb.toString());
				}
				sb = new StringBuilder();
			}
			sb.append(c);

		}
		file = null;
		Map<Integer, Set<String>> map = new HashMap<>();
		for (String s : list) {
			Integer i = s.split("#").length - 1;
			if (!map.containsKey(i)) {
				map.put(i, new HashSet<>());
			}
			map.get(i).add(s.substring(i));
		}

		List<Integer> sortedKeys = new ArrayList<>();
		sortedKeys.addAll(map.keySet());
		Collections.sort(sortedKeys);

		for (int i = 0; i < sortedKeys.size(); i += 43210) {
			Integer index = sortedKeys.get(i);
			System.out.println(map.get(index));
		}
	}

	/**
	 * v1 - no group
	 */
	private static void solution1(String file) {
		List<String> list = new ArrayList<>();
		StringBuilder sb = null;
		for (int i = 0; i < file.length(); i++) {
			char c = file.charAt(i);
			if (c == '#' && (sb == null || sb.charAt(sb.length() - 1) != '#')) {
				if (sb != null) {
					list.add(sb.toString());
				}
				sb = new StringBuilder();
			}
			sb.append(c);
		}
		file = null;
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(final String a, String b) {
				return a.split("#").length - b.split("#").length;
			}
		});
		for (int i = 0; i < list.size(); i += 43210) {
			System.out.println(list.get(i));
		}
	}

}
