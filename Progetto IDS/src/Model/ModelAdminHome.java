package Model;

import java.util.Observable;

public class ModelAdminHome extends Observable {
	private Catalog runningOut;
	
	public ModelAdminHome() {
		ModelCatalog shop = new ModelCatalog();
		runningOut = shop.getCatalogRunningOut();
	}
	
	public Catalog getCatalogRunningOut() {
		return runningOut;
	}
}
