package Controller;

import Model.ModelSuggested;
import View.ViewSuggested;

import java.util.*;

import javax.swing.JPanel;

public class ControllerSuggested implements Observer {
	private ModelSuggested model;
	private ViewSuggested view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerSuggested() {
		this.model = new ModelSuggested();
		this.view = new ViewSuggested(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer) {
			System.out.println("suggested: disco " + arg);
		}
	}

}
