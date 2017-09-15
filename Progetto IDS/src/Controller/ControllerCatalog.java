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
	
	public ControllerCatalog() {
		this(null,null,null,null);
	}
	
	public ControllerCatalog(String title, String genre, Double minPrice, Double maxPrice){
		this.model = new ModelCatalog();
		this.view = new ViewCatalog(model,title,genre,String.valueOf(minPrice),String.valueOf(maxPrice));
		view.addObserver(this);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	public int getQuantity(Disc d){
		return model.getQuantityDisc(d);
	}
	
	public void showCatalogWith(String title, String genre, Double minPrice, Double maxPrice){
		if(genre == "-") {
			genre = null;
		}
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
