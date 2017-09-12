package Controller;

import Model.ModelHome;
import View.ViewHome;

import java.util.*;

import javax.swing.JPanel;

public class ControllerHome implements Observer {
	private ModelHome model;
	private ViewHome view;

	public ControllerHome() {
		this.model = new ModelHome();
		this.view = new ViewHome(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof List) {
			List<String> l = (List<String>)arg;
			if("catalogWith".equals(l.get(0))) {
				System.out.println("Richiamare catalog with");
				
			}
		}
	}

}
