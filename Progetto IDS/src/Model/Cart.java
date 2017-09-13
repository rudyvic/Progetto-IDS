package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cart extends Catalog {
	/*
	private DatabaseQuery database;
	private String user;
	private boolean isLogin;
	
	public Cart(DatabaseQuery database, String user) {
		this.database = database;
		this.user = user;
		
		if(user==null) {
			isLogin = false;
		} else {
			isLogin = true;
		}
	}
	
	private void synchCart() {
		if(isLogin) {
			Catalog cat = database.getCart(user);
			this.catalog = cat.catalog;
		}
	}
	
	public void addNotSynchedDiscs() {
		for(Disc d : catalog.keySet()) {
			database.insertCart(d.getCode(), user, catalog.get(d));
		}
		synchCart();
	}
	
	public void emptyCart() {
		if(isLogin) {
			Set<Disc> cat = catalog.keySet();
			for(Disc disc : cat) {
				database.removeFromCart(disc.getCode(),user);
			}
			synchCart();
		} else {
			catalog.clear();
		}
	}
	
	public void login(String user) {
		this.user = user;
		isLogin = true;
		addNotSynchedDiscs();
	}
	
	public void logout() {
		this.user = null;
		isLogin = false;
	}
	
	@Override
	public List<Disc> getCatalog() {
		synchCart();
		return new ArrayList<Disc>(catalog.keySet());
	}
	
	@Override
	public void add(Disc disc, int quantity) {
		if(isLogin) {
			database.insertCart(disc.getCode(),user, quantity);
			synchCart();
		} else {
			catalog.put(disc, quantity);
		}
	}
	
	@Override
	public void remove(Disc disc) {
		if(isLogin) {
			database.removeFromCart(disc.getCode(),user);
			synchCart();
		} else {
			catalog.remove(disc);
		}
	}

	@Override
	public void editDiscQuantity(Disc disc, int quantity) {
		if(isLogin) {
			database.editCartQuantity(user, disc.getCode(), quantity);
			synchCart();
		} else {
			catalog.put(disc, quantity);
		}
	}
	
	@Override
	public boolean contains(Disc disc) {
		synchCart();
		return catalog.containsKey(disc);
	}*/
}
