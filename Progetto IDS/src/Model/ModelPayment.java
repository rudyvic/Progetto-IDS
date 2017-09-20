package Model;

import java.util.Observable;

public class ModelPayment extends Observable {
	private Catalog cart;
	
	public ModelPayment() {
		cart = new Catalog();
	}
	
	public void setCart(Catalog cart) {
		this.cart = cart;
	}
	
	public Catalog getCart() {
		return cart;
	}
}
