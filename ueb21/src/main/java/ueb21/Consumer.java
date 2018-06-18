package ueb21;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeMap;

public class Consumer {

	private TreeMap<Integer, ArrayList<Long>> treMap = new TreeMap<Integer, ArrayList<Long>>();
	
	public Integer consume(Integer i) {
		
		if (i == null || i < 0) {
			
			throw new IllegalArgumentException ("Value must be a natural number!");
		}
		
		Integer res = 0;
		
		while(i != 0) {
			
			res += i % 10;
			i /= 10;
		}
		if(treMap.get(res) == null) {
			
			treMap.put(res, new ArrayList<Long>());
		}
			
		treMap.get(res).add(System.currentTimeMillis());
		
		return res;
	}

	public Integer numberOfDifferentResults() {
		return treMap.size();
	}

	public Integer numberOfOccurrences(Integer i) {
		return treMap.get(i).size();
	}

	public NavigableSet<Integer> getCrossTotalsAscending() {
		return treMap.navigableKeySet();
	}

	public NavigableSet<Integer> getCrossTotalsDescending() {
		return treMap.descendingKeySet();
	}

	public ArrayList<Long> getTimestampsForResult(Integer i) {
		return treMap.get(i);
	}
	
	public boolean exists(Integer i) {
		
		if (i == null || i < 0) {
			
			throw new IllegalArgumentException ("Value must be a natural number!");
		}
		
		return treMap.get(i) != null;
	}
	
}
