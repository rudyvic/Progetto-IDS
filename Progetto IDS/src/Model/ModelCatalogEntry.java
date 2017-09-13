package Model;

import java.util.Observable;

public class ModelCatalogEntry  extends Observable {
	private Disc disc;
	
	public ModelCatalogEntry(Disc disc){
		this.disc = disc;
	}
	
	public Disc getDisc() {
		return disc;
	}
}
