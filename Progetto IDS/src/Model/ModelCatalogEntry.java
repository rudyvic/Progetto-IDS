package Model;

import java.util.Observable;

public class ModelCatalogEntry  extends Observable {
	private Disc disc;
	private Catalog catalog;
	
	public ModelCatalogEntry(Disc disc){
		this.disc = disc;
	}
	
	public Disc getDisc() {
		return disc;
	}
	
	public int getQuantity(Disc d){
		return catalog.getQuantity(d);
	}
}
