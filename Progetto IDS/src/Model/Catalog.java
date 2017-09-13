package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Catalog {
	private Map<Disc,Integer> catalog;
	
	public Catalog() {
		catalog = new TreeMap<Disc,Integer>();
	}
	
	public int length() {
		return catalog.size();
	}
	
	public void add(Disc disc, int quantity) {
		catalog.put(disc,quantity);
	}

	public List<Disc> getCatalog() {
		return new ArrayList<Disc>(catalog.keySet());
	}
	
	public Disc getDisc(int code) {
		Disc disc = null;
		for(Disc d : catalog.keySet()) {
			if(d.getCode()==code) {
				disc = d;
			}
		}
		return disc;
	}
	
	public int getQuantity(Disc disc) {
		return catalog.get(disc);
	}
	
	public boolean contains(Disc disc) {
		return catalog.containsKey(disc);
	}
	
	public void remove(Disc disc) {
		catalog.remove(disc);
	}
	
	public void empty() {
		catalog.clear();
	}
	
	public boolean checkDiscQuantity(Disc disc, int quantity) {
		if(contains(disc)) {
			if(quantity <= catalog.get(disc)) {
				return true;
			}
		}
		return false;
	}
	
	public void editDiscQuantity(Disc disc, int quantity) {
		if(catalog.containsKey(disc)) {
			catalog.put(disc, quantity);
		}
	}
}
