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
	
	public ControllerCatalogEntry(Disc disc){
		model = new ModelCatalogEntry(disc);
		view = new ViewCatalogEntry(model);
		view.addObserver(this);
		
/*
		if(controller.isProdottoInCarrello(disco)) {
			panel.setGiaNelCarrello();
		} else if(catalogo.getQuantita(disco) <= 0) {
			panel.prodottoTerminato(true);
		}
		*/
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
				controller.showCartButton();
			}
		}
	}

}
