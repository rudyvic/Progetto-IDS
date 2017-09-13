package Model;

import java.util.*;

public class StoreCatalog extends Catalog {
	/*
	private DatabaseQuery database;
	
	public StoreCatalog(DatabaseQuery database) {
		this.database = database;
	}
	
	public List<Disc> getCatalogRunningOut() {
		synchCatalog();
		
		List<Disc> cat = new ArrayList<Disc>();
		
		for(Map.Entry<Disc,Integer> entry : catalog.entrySet()) {
		    Disc disc = entry.getKey();
		    int quantity = entry.getValue();
		    
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
			int quantityInStore = this.getQuantity(disc);
			int quantityToBuy = cat.getQuantity(disc);
			
			if(quantityToBuy <= quantityInStore) {
				this.editDiscQuantity(disc, quantityInStore-quantityToBuy);
				database.boughtCD(b.getCode(), disc.getCode());
				bought.add(disc, quantityToBuy);
			}
		}
		
		return bought;
	}
	
	private void synchCatalog() {
		Catalog cat = database.getCatalog();
		this.catalog = cat.catalog;
	}
	
	public List<Disc> searchCatalog(String title, String genre, Double minPrice, Double maxPrice){
		synchCatalog();
		Catalog c = new Catalog();
		c = database.searchCatalog(title, genre, minPrice, maxPrice);
		return new ArrayList<Disc>(c.catalog.keySet());
	}
	
	@Override
	public List<Disc> getCatalog() {
		synchCatalog();
		return new ArrayList<Disc>(catalog.keySet());
	}
	
	@Override
	public void add(Disc disc, int quantity) {
		database.addDisc(disc, quantity);
		synchCatalog();
	}
	
	@Override
	public void remove(Disc disc) {
		database.removeFromStore(disc.getCode());
		synchCatalog();
	}

	@Override
	public void editDiscQuantity(Disc disc, int quantity) {
		database.editStoreQuantity(disc.getCode(),quantity);
		synchCatalog();
	}*/
}
