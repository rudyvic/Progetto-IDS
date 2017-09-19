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
	
	private Disc d;
	
	public ControllerCartEntry(Disc d, int quantita){
		this.d = d;
		this.model = new ModelCartEntry();
		this.view = new ViewCartEntry(model, d, quantita);
		view.addObserver(this);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			if("minus".equals(arg)){
				int quantity = model.getQuantity();
				model.setQuantity(--quantity,"minus");
			}
			
			else if("plus".equals(arg)){
				ControllerCatalog controllerCatalog = new ControllerCatalog();
				int quantity = model.getQuantity();
				if(controllerCatalog.checkDiscQuantity(d, quantity+1)==true) {
					model.setQuantity(++quantity,"plus");
				}
			}
			
			else if("remove".equals(arg)){
				ControllerCart controllerCart = ControllerCart.getInstance();
				controllerCart.remove(d);
				ApplicationController controller = ApplicationController.getInstance();
				controller.showCart();
			}
		}
	}

}
