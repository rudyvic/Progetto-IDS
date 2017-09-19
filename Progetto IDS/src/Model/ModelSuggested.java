package Model;

import java.util.Observable;

public class ModelSuggested extends Observable {
	private Catalog suggested;
	private String user;
	
	public ModelSuggested(String user) {
		this.user = user;
		loadSuggested();
	}
	
	private void loadSuggested() {
		DatabaseQuery db = DatabaseQuery.getInstance();
		suggested = db.getSuggested(user);
	}
	
	public Catalog getSuggested() {
		return suggested;
	}
}
