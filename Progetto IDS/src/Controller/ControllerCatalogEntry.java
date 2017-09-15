package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Disc;
import Model.ModelCatalogEntry;
import View.ViewCatalogEntry;

public class ControllerCatalogEntry implements Observer{
	private ModelCatalogEntry model;
	private ViewCatalogEntry view;
	private ApplicationController controller = ApplicationController.getInstance();
	private Disc disc;
	
	public ControllerCatalogEntry(Disc disc){
		this.disc = disc;
		model = new ModelCatalogEntry(disc);
		view = new ViewCatalogEntry(model);
		view.addObserver(this);
		
		if(controller.cartContains(disc)) {
			view.inTheCart();
		}else if(controller.getQuantity(disc) <= 0) {
			view.terminatedProduct(true);
		}
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("disc page".equals((String)arg)){
				controller.showDiscPage(model.getDisc());
			} else if("add to cart".equals((String)arg)){
				System.out.println("add to cart");
				view.inTheCart();
				System.out.println(disc.getTitle());
				controller.discInTheCart(disc);
			}
		}
	}

}
