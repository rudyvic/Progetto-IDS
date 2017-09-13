package Controller;

import Model.ModelCart;
import View.ViewCart;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ControllerCart implements Observer {
	private ModelCart model;
	private ViewCart view;
	private ApplicationController controller = ApplicationController.getInstance();
	
	public ControllerCart(){
		this.model = new ModelCart();
		this.view = new ViewCart(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("empty cart".equals((String)arg)){
				model.emptyCart();
				controller.controllerTopbar.emptyCart();
				controller.showHome();
			} else if("payment".equals((String)arg)){
				System.out.println("pagamento");
				controller.showHome();
			}
		}
	}

}
