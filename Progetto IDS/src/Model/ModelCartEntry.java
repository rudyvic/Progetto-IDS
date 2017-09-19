package Model;

import java.util.Observable;

public class ModelCartEntry extends Observable{

	private Disc disc;
	private int quantity;
	private int maxQuantity = 0;
	
	public ModelCartEntry(Disc disc){
		this.disc = disc;
	}
	
	public Disc getDisc() {
		return disc;
	}
	
	public void setMaxQuantity(int max) {
		this.maxQuantity = max;
		
		if(max<quantity) {
			quantity = max;
		}
		
		this.setChanged();
		this.notifyObservers("plus");
	}
	
	public int getMaxQuantity() {
		return maxQuantity;
	}
	
	public void setQuantity(int quantity,String action) {
		this.quantity = quantity;
		this.setChanged();
		this.notifyObservers(action);
	}
	
	public int getQuantity(){
		return quantity;
	}
}
