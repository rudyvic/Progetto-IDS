package Model;

import java.util.Observable;

public class ModelSuggested extends Observable {
	private Catalog suggested;
	
	public ModelSuggested() {
		suggested = new Catalog();
		loadSuggested();
	}
	
	private void loadSuggested() {
		suggested.add(new Disc(), 10);
	}
	
	public Catalog getSuggested() {
		return suggested;
	}
}
