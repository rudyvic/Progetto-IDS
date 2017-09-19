package Model;

import java.util.*;

public class ModelCart extends Observable {
	private Catalog cart;
	private DatabaseQuery database;
	private String username = null;
	private boolean isLogin = false;
	
	public ModelCart () {
		this.database = DatabaseQuery.getInstance();
		this.cart = new Catalog();
	}
	
	private void synchCart() {
		if(isLogin) {
			this.cart = database.getCart(username);
		}
	}
	
	public void addNotSynchedDiscs() {
		for(Disc d : cart.getCatalog()) {
			database.insertCart(d.getCode(), username, cart.getQuantity(d));
		}
		synchCart();
	}
	
	public void emptyCart() {
		if(isLogin) {
			List<Disc> cat = cart.getCatalog();
			for(Disc disc : cat) {
				database.removeFromCart(disc.getCode(),username);
			}
			synchCart();
		} else {
			cart.empty();
		}
	}
	
	public void login(String user) {
		this.username = user;
		isLogin = true;
		addNotSynchedDiscs();
	}
	
	public void logout() {
		this.username = null;
		isLogin = false;
	}
	
	public ArrayList<Disc> getCart() {
		synchCart();
		return cart.getCatalog();
	}
	
	public void add(Disc disc, int quantity) {
		if(isLogin) {
			database.insertCart(disc.getCode(),username, quantity);
			synchCart();
		} else {
			cart.add(disc, quantity);
		}
	}
	
	public void remove(Disc disc) {
		if(isLogin) {
			database.removeFromCart(disc.getCode(),username);
			synchCart();
		} else {
			cart.remove(disc);
		}
	}

	public void editDiscQuantity(Disc disc, int quantity) {
		if(isLogin) {
			database.editCartQuantity(username, disc.getCode(), quantity);
			synchCart();
		} else {
			cart.add(disc, quantity);
		}
	}
	
	public boolean contains(Disc disc) {
		synchCart();
		return cart.contains(disc);
	}
	
	public int getQuantity(Disc disc) {
		return cart.getQuantity(disc);
	}
	
	public double getTotalPrice() {
		double price = 0.0;
		
		for(Disc disc : cart.getCatalog()) {
			price = price + (cart.getQuantity(disc) * disc.getPrice());
		}
		
		return price;
	}
	
	public boolean isSuper(String username){
		return database.isSuper(username);
	}
	
	public String getUsername(){
		return username;
	}
	
	public boolean isEmpty(){
		return (cart.length() == 0);
	}
	
	public int length() {
		return cart.length();
	}
}
