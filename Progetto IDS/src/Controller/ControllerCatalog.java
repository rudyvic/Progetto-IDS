package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.ModelCatalog;
import Model.ModelHome;
import View.ViewCatalog;
import View.ViewHome;

public class ControllerCatalog implements Observer{
	
	private ModelCatalog model;
	private ViewCatalog view;
	private ApplicationController controller;
	
	public ControllerCatalog(){
		this.model = new ModelCatalog();
		this.view = new ViewCatalog(model,null,null,null,null);
		view.addObserver(this);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("filtro".equals(arg)) {
				System.out.println("Ho applicato un filtro...");
				//controller = ApplicationController.getInstance();
			}
		}
	}
}
