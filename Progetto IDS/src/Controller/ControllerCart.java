package Controller;

import Model.Disc;
import Model.ModelCart;
import View.ViewCart;

import java.util.Observable;
import java.util.Observer;

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
		
		return controllerCart;
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
