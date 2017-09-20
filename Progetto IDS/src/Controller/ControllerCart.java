package Controller;

import Model.Disc;
import Model.ModelCart;
import View.ViewCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControllerCart implements Observer {
	private ModelCart model;
	private ViewCart view;
	private ApplicationController controller = ApplicationController.getInstance();
	private static ControllerCart controllerCart = null;	// Singleton
	
	private ControllerCart(){
		this.model = new ModelCart();
		this.view = new ViewCart(model);
		view.addObserver(this);
	}
	
	public static ControllerCart getInstance(){
		if(controllerCart == null){
			controllerCart = new ControllerCart();
		}
		
		controllerCart.setNewView();
		return controllerCart;
	}
	
	public void setList(){
		ArrayList<Disc> listDisc = new ArrayList<Disc>();
		List<JPanel> listPanel = new ArrayList<JPanel>();
		listDisc = model.getCart();
		for(Disc d:listDisc){
			ControllerCartEntry controllerCartEntry = new ControllerCartEntry(d,model.getQuantity(d));
			listPanel.add(controllerCartEntry.getPanel());
		}
		
		view.setList(listPanel);
	}
	
	private void setNewView() {
		view = new ViewCart(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	public boolean contains(Disc d){
		return model.contains(d);
	}
	
	public void add(Disc d){
		model.add(d, 1);
	}
	
	public void remove(Disc d){
		model.remove(d);
	}
	
	public boolean isEmptyCart(){
		return model.isEmpty();
	}
	
	public void login(String user) {
		model.login(user);
	}
	
	public void logout() {
		model.logout();
	}
	
	public int cartLength() {
		return model.length();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("empty cart".equals((String)arg)){
				model.emptyCart();
				controller.controllerTopbar.emptyCart();
				controller.showHome();
			} else if("payment".equals((String)arg)){
				if(controller.controllerTopbar.isLogin()) {
					controller.showPayment();
				} else {
					JOptionPane.showMessageDialog(null, "You must be logged in to proceed.", "LOGIN FIRST", JOptionPane.INFORMATION_MESSAGE, null);
					controller.showLogin();
				}
			}
		}
	}

}
