package Controller;

import Model.ModelTopbar;
import View.ViewTopbar;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ControllerTopbar implements Observer {
	private ModelTopbar model;
	private ViewTopbar view;
	private ApplicationController controller = ApplicationController.getInstance();
	
	public ControllerTopbar(){
		this.model = new ModelTopbar();
		this.view = new ViewTopbar(model);
		view.addObserver(this);
		
		updateView();
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}

	public void addDiscToCart() {
		model.addDiscToCart();
		updateCart();
	}
	
	public void removeFromCart() {
		model.removeDiscToCart();
		updateCart();
		
		if(model.getDiscsInCart()==0) {
			controller.showHome();
		} else {
			controller.showCart();
		}
	}
	
	public void emptyCart() {
		model.setDiscsInCart(0);
		updateCart();
	}

	public void updateView() {
		if(model.isLogin()==false) {
			view.showLogSignPanel();
		} else if(model.isAdmin()==false) {
			view.showUserPanel();
		} else {
			view.showAdminPanel();
		}
		
		updateCart();
	}
	
	private void updateCart() {
		if(model.getDiscsInCart() == 0) {
			view.isCartEmpty(true);
		} else {
			view.isCartEmpty(false);
		}
		view.updateCart();
	}
	
	public void login(String username, boolean isAdmin, boolean isSuper) {
		model.login(username, isAdmin, isSuper);
		updateView();
	}
	
	public void logout() {
		model.logout();
		updateView();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("home".equals((String)arg)){
				controller.showHome();
			} else if("login".equals((String)arg)){
				controller.showLogin();
			} else if("logout".equals((String)arg)){
				model.logout();
				view.showLogSignPanel();
				controller.showHome();
			} else if("update".equals((String)arg)){
				updateView();
				controller.showHome();
			} else if("signin".equals((String)arg)){
				model.logout();
				view.showLogSignPanel();
				controller.showSignin();
			} else if("admin".equals((String)arg)){
				updateView();
				controller.showAdminHome();
			} else if("cart".equals((String)arg)){
				updateView();
				controller.showCart();
			}
		}
	}

}
