package Model;

import java.util.*;

public class ModelDiscPage extends Observable {
	private Disc disc;
	
	public ModelDiscPage (Disc disc) {
		this.disc = disc;
	}
	
	public Disc getDisc() {
		return disc;
	}
}
