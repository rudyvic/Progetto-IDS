package Controller;

import java.util.*;

import javax.swing.JPanel;

import Model.Disc;
import Model.ModelCatalog;
import View.ViewCatalog;

public class ControllerCatalog implements Observer{

	private ModelCatalog model;
	private ViewCatalog view;
	private ApplicationController controller;
	
	public ControllerCatalog(){
		this.model = new ModelCatalog();
		this.view = new ViewCatalog(model,null,null,null,null);
		view.addObserver(this);
	}
	
	public void showCatalogWith(){	
		List<JPanel> list = new ArrayList<JPanel>();
		for(Disc disc : model.searchCatalog(view.getProductName(), view.getGenre(), view.getMinPrice(),view.getMaxPrice())) {
			ControllerCatalogEntry e = new ControllerCatalogEntry(disc);
			list.add(e.getPanel());
		}
		view.setList(list);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("filtro".equals(arg)) {
				controller = ApplicationController.getInstance();
				controller.showCatalog();
			}
		}
	}
}
