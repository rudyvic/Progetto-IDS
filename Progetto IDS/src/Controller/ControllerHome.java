package Controller;

import Model.ModelHome;
import View.ViewHome;

import java.util.*;

import javax.swing.JPanel;

public class ControllerHome implements Observer {
	private ModelHome model;
	private ViewHome view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerHome() {
		this.model = new ModelHome();
		this.view = new ViewHome(model);
		view.addObserver(this);
		
		/*
		if(controller.isLogin()) {
			view.showSuggested();
		}
		*/
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("catalogWith".equals(arg)) {
				System.out.println("Richiamare catalog with " + view.getFindText());
			}
		}
	}

}
