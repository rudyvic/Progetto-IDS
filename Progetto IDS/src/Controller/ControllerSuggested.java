package Controller;

import Model.DatabaseQuery;
import Model.ModelSuggested;
import View.ViewSuggested;

import java.util.*;

import javax.swing.JPanel;

public class ControllerSuggested implements Observer {
	private ModelSuggested model;
	private ViewSuggested view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerSuggested() {
		this.model = new ModelSuggested("aa");
		this.view = new ViewSuggested(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer) {
			DatabaseQuery db = DatabaseQuery.getInstance();
			controller.showCatalog(model.getSuggested().getDisc((int)arg).getTitle(), null, Double.valueOf("0.00"), db.getMaxPrice());
		}
	}

}
