package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Disc;
import Model.ModelCatalog;
import Model.ModelHome;
import View.ViewCatalog;
import View.ViewHome;

public class ControllerCatalog implements Observer{
	private ModelCatalog model;
	private ViewCatalog view;
	private ApplicationController controller = ApplicationController.getInstance();
	
	public ControllerCatalog(){
		this.model = new ModelCatalog();
		this.view = new ViewCatalog(model,null,null,null,null);
		view.addObserver(this);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	public void showCatalogWith(String title, String genre, Double minPrice, Double maxPrice){	
		List<JPanel> list = new ArrayList<JPanel>();
		for(Disc disc : model.searchCatalog(title, genre, minPrice, maxPrice)) {
			ControllerCatalogEntry e = new ControllerCatalogEntry(disc);
			
			list.add(e.getPanel());
		}
		view.setList(list);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("filtro".equals(arg)) {
				controller.showCatalog(view.getProductName(), view.getGenre(), view.getMinPrice(), view.getMaxPrice());
			}
		}
	}
}
