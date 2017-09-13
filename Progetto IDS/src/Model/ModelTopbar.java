package Model;

import java.util.Observable;

public class ModelTopbar extends Observable {
	
	private boolean isLogin = false;
	private boolean isAdmin = false;
	private boolean isSuper = false;
	private String username = null;
	private int discsInCart = 0;
	
	public ModelTopbar() {
		
	}
	
	public void login(String username, boolean isAdmin, boolean isSuper) {
		this.username = username;		
		this.isAdmin = isAdmin;
		this.isSuper = isSuper;
		isLogin = true;
		
		setChanged();
		notifyObservers();
	}
	
	public void logout() {
		username = null;
		isLogin = false;
		isAdmin = false;
		isSuper = false;
		
		setChanged();
		notifyObservers();
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public boolean isSuper() {
		return isSuper;
	}
	
	public boolean isLogin() {
		return isLogin;
	}
	
	public int getDiscsInCart() {
		return discsInCart;
	}
	
	public void setDiscsInCart(int value) {
		discsInCart = value;
	}
	
	public void addDiscToCart() {
		discsInCart++;
	}
	
	public void removeDiscToCart() {
		discsInCart--;
	}
}
