package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Disc;
import Model.ModelCartEntry;
import View.ViewCartEntry;

public class ControllerCartEntry implements Observer{
	
	private ModelCartEntry model;
	private ViewCartEntry view;
	
	public ControllerCartEntry(Disc d, int quantita){
		this.model = new ModelCartEntry(d);
		this.view = new ViewCartEntry(model, d, quantita);
		view.addObserver(this);
		
		ControllerCatalog controllerCatalog = new ControllerCatalog();
		model.setMaxQuantity(controllerCatalog.getQuantity(model.getDisc()));
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			if("minus".equals(arg)){
				if(model.getQuantity()>0) {
					model.setQuantity(model.getQuantity()-1,"minus");
				}
			}
			
			else if("plus".equals(arg)){
				ControllerCatalog controllerCatalog = new ControllerCatalog();
				if(controllerCatalog.checkDiscQuantity(model.getDisc(), model.getQuantity()+1)) {
					model.setQuantity(model.getQuantity()+1,"plus");
				}
			}
			
			else if("remove".equals(arg)){
				ControllerCart controllerCart = ControllerCart.getInstance();
				ApplicationController controller = ApplicationController.getInstance();
				controllerCart.remove(model.getDisc());
				controller.controllerTopbar.removeFromCart();
			}
		}
	}

}
