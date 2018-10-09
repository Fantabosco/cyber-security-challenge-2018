package challenge.ctf_ception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.FileUtils;

public class CTFCeption {
	// This CTF consists of challenges in 5 different categories and for each challenge there’s a score and the estimated time (in minutes) for solving it.
	// R-Boy is really good in WEB, so he can solve those challenges in half the estimated time,but he needs twice the time to solve BINARY challenges.
	// The CTF lasts only 24 hours. Can you help R-Boy choose which challenges to solve to achieve the best score possible – and win the prize?
	// The IDs of the challenges to choose, sorted by ETA descending, will reveal you the prize

	public static void main(String[] args) {

		List<String> file = FileUtils.readFile("challenge/ctf_ception/Challenges.csv");
		
		// Remove header
		file.remove(0);
		
		List<Challenge> list = new ArrayList<>();
		for(String line: file) {
			String[] values = line.split("\t");
			list.add(new Challenge(values[0], values[1], values[2], values[3]));
		}
		
		List<Challenge> choosed = solution1(list);
		
		System.out.println("Score: " + getScores(choosed));
		
		// Sort by eta desc
		Collections.sort(choosed, new Comparator<Challenge>() {
			@Override
			public int compare(final Challenge a, Challenge b) {
				return b.eta - a.eta;
			}
		});
		
		// The IDs of the challenges to choose, sorted by ETA descending, will reveal you the prize
		for(Challenge c: choosed) {
			System.out.print(c.id);
		}
	}
	
	/**
	 * Try brute force
	 * @return 
	 */
	private static List<Challenge> solution2(List<Challenge> list) {
		map = new HashMap<>();
		for(Challenge c: list) {
			map.put(c.id, c);
		}
	
		for(int i=0; i<ids.size(); i++) {
			Set<String> idList = new HashSet<>();
			idList.add(ids.get(i));
			recursiveSelector(idList);
		}
		
		return getObjs(best);
	}
	
	private static List<String> ids = Arrays.asList("N","q","I","p","6","A","i","B","j","V","U","e","R","F","f","x","0","Z","M","P","t","Q","8","D","J");
	private static Set<String> best;
	private static Map<String,Challenge> map;
	
	private static boolean recursiveSelector(Set<String> idList) {
		// Check eta
		if(isEtaEnough(idList)) {
			boolean flag = false;
			for(int i=0; i<ids.size(); i++) {
				if(!idList.contains(ids.get(i))) {
					Set<String> idList2 = new HashSet<>();
					idList2.addAll(idList);
					idList2.add(ids.get(i));
					flag = flag || recursiveSelector(idList2);
				}
			}
			if(!flag) {
				// If recursion has not continued, evaluate current solution agains best
				if(isBetter(idList)) {
					best = idList;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isBetter(Set<String> idList) {
		if(best == null) {
			return true;
		}
		List<Challenge> a = getObjs(idList);
		List<Challenge> b = getObjs(best);
		return getScores(a) > getScores(b);
	}
	
	private static int getScores(List<Challenge> a) {
		int t=0;
		for(Challenge c: a) {
			t+=c.score;
		}
		return t;
	}

	private static boolean isEtaEnough(Set<String> idList) {
		double totEta = 0;
		List<Challenge> cl = getObjs(idList);
		for(Challenge c: cl) {
			totEta += c.eta * c.category.timeCoeff;
		}
		return totEta <= (60 * 24);		
	}
	
	private static List<Challenge> getObjs(Set<String> idList) {
		List<Challenge> c = new ArrayList<>();
		for(String id: idList) {
			c.add(map.get(id));
		}
		return c;
	}

	/**
	 * Try greedy
	 * @return 
	 */
	private static List<Challenge> solution1(List<Challenge> list) {
		Collections.sort(list, new Comparator<Challenge>() {
			@Override
			public int compare(final Challenge a, Challenge b) {
				return (int) ((b.value - a.value) * 100000);
			}
		});
		
		int totEta = 0;
		List<Challenge> choosed = new ArrayList<>();
		for(Challenge c: list) {
			if(totEta + (c.eta * c.category.timeCoeff) <= (60 * 24)) {
				choosed.add(c);
				totEta += (c.eta * c.category.timeCoeff);
			}
		}
		return choosed;
	}
}
