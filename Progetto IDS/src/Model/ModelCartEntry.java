package Model;

import java.util.Observable;

public class ModelCartEntry extends Observable{

	private int quantity;
	
	public void setQuantity(int quantity,String action) {
		this.quantity = quantity;
		this.setChanged();
		this.notifyObservers(action);
	}
	
	public int getQuantity(){
		return quantity;
	}
}
