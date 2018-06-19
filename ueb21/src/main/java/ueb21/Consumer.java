package ueb21;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Consumer {

	private TreeMap<Integer, ArrayList<Long>> treeMap = new TreeMap<Integer, ArrayList<Long>>();
	
	public Integer consume(Integer i) {
		
		if (i == null || i < 0) {
			
			throw new IllegalArgumentException ("Value must be a natural number!");
		}
		
		Integer res = 0;
		
		while(i != 0) {
			
			res += i % 10;
			i /= 10;
		}
		if(treeMap.get(res) == null) {
			
			treeMap.put(res, new ArrayList<Long>());
		}
			
		treeMap.get(res).add(System.currentTimeMillis());
		
		return res;
	}

	public Integer numberOfDifferentResults() {
		return treeMap.size();
	}

	public Integer numberOfOccurrences(Integer i) {
		return treeMap.get(i).size();
	}

	public Set<Integer> getCrossTotalsAscending() {
		return treeMap.navigableKeySet();
	}

	public Set<Integer> getCrossTotalsDescending() {
		return treeMap.descendingKeySet();
	}

	public List<Long> getTimestampsForResult(Integer i) {
		return treeMap.get(i);
	}
	
	public boolean exists(Integer i) {
		
		if (i == null || i < 0) {
			
			throw new IllegalArgumentException ("Value must be a natural number!");
		}
		
		return treeMap.get(i) != null;
	}
	
}
