package Controller;

import Model.DatabaseQuery;
import Model.Disc;
import Model.ModelSuggested;
import View.ViewSuggested;

import java.util.*;

import javax.swing.JPanel;

public class ControllerSuggested implements Observer {
	private ModelSuggested model;
	private ViewSuggested view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerSuggested(String user) {
		this.model = new ModelSuggested(user);
		this.view = new ViewSuggested(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer) {
			Disc disc = model.getSuggested().getDisc((int)arg);
			controller.showCatalog(disc.getTitle(), disc.getGenre(), disc.getPrice(), disc.getPrice());
		}
	}

}
