package ueb21;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeMap;

public class Consumer {

	private TreeMap<Integer, ArrayList<Long>> sorMap = new TreeMap<Integer, ArrayList<Long>>();
	
	public void consume(Integer i) {
		
		Integer res = 0;
		
		while(i != 0) {
			
			res += i % 10;
			i /= 10;
		}
		if(sorMap.get(res) == null) {
			
			sorMap.put(res, new ArrayList<Long>());
		}
			
		sorMap.get(res).add(System.currentTimeMillis());
	}

	public Integer numberOfDifferentResults() {
		return sorMap.size();
	}

	public Integer numberOfOccurrences(Integer i) {
		return sorMap.get(i).size();
	}

	public NavigableSet<Integer> getCrossTotalsAscending() {
		return sorMap.navigableKeySet();
	}

	public NavigableSet<Integer> getCrossTotalsDescending() {
		return sorMap.descendingKeySet();
	}

	public ArrayList<Long> getTimestampsForResult(Integer i) {
		return sorMap.get(i);
	}
	
	public boolean exists(int i) {
		
		return sorMap.get(i) != null;
	}
	
}
