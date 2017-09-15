package Model;

import java.util.Observable;

public class ModelAdminEditDiscQuantity extends Observable {
	private ModelCatalog catalog;
	private int discCode;
	
	public ModelAdminEditDiscQuantity(int discCode) {
		catalog = new ModelCatalog();
		this.discCode = discCode;
	}
	
	public Catalog getCatalog() {
		return catalog.synchCatalog();
	}
	
	public int getDiscCode() {
		return discCode;
	}
}
