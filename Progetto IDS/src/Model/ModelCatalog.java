package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import Model.Catalog;
import Model.Disc;

public class ModelCatalog extends Observable{
	
	private DatabaseQuery database = DatabaseQuery.getInstance();
	private Catalog catalog;
	
	public ModelCatalog(){
		
	}
	
	public double getMaxPrice(){
		return database.getMaxPrice();
	}
	
	public List<Disc> getCatalogRunningOut() {
		synchCatalog();
		
		List<Disc> cat = new ArrayList<Disc>();
		
		for(Disc disc : catalog.getCatalog()) {
		    int quantity = catalog.getQuantity(disc);
		    
		    if(quantity<=2) {
		    	cat.add(disc);
		    }
		}
		
		return cat;
	}
	
	public Catalog buy(Catalog cat, Buy b) {
		Catalog bought = new Catalog();

		b = database.buyCD(b);
		
		for(Disc disc : cat.getCatalog()) {
			int quantityInStore = this.catalog.getQuantity(disc);
			int quantityToBuy = cat.getQuantity(disc);
			
			if(quantityToBuy <= quantityInStore) {
				this.editDiscQuantity(disc, quantityInStore-quantityToBuy);
				database.boughtCD(b.getCode(), disc.getCode());
				bought.add(disc, quantityToBuy);
			}
		}
		
		return bought;
	}
	
	public Catalog synchCatalog() {
		this.catalog = database.getCatalog();
		return this.catalog;
	}
	
	public List<Disc> searchCatalog(String title, String genre, Double minPrice, Double maxPrice){
		Catalog c = synchCatalog();
		c = database.searchCatalog(title, genre, minPrice, maxPrice);
		return new ArrayList<Disc>(c.getCatalog());
	}

	public void add(Disc disc, int quantity) {
		database.addDisc(disc, quantity);
		synchCatalog();
	}
	
	public void remove(Disc disc) {
		database.removeFromStore(disc.getCode());
		synchCatalog();
	}

	public void editDiscQuantity(Disc disc, int quantity) {
		database.editStoreQuantity(disc.getCode(),quantity);
		synchCatalog();
	}
}
