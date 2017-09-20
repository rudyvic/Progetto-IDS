package Model;

import java.util.Observable;

public class ModelPayment extends Observable {
	private Catalog cart;
	private String username;
	private DatabaseQuery db = DatabaseQuery.getInstance();
	
	public ModelPayment(String username, Catalog cart) {
		this.username = username;
		this.cart = cart;
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public Catalog getCart() {
		return cart;
	}
	
	public double getPrice() {
		double price = 0;
		for(Disc d : cart.getCatalog()) {
			if(db.isSuper(username)) {
				price = price + (cart.getQuantity(d) * d.getPrice() * 0.7);
			} else {
				price = price + (cart.getQuantity(d) * d.getPrice());
			}
		}
		return price;
	}
}
