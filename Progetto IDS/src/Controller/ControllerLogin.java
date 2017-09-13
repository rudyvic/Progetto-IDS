package Controller;

import Model.ModelLogin;
import View.ViewLogin;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ControllerLogin implements Observer {
	private ModelLogin model;
	private ViewLogin view;
	private ApplicationController controller;			// Se metto il getInstance qui va in loop
	
	public ControllerLogin(){
		this.model = new ModelLogin();
		this.view = new ViewLogin(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if("home".equals((String)arg)){
			controller = ApplicationController.getInstance();
			controller.showHome();
		}
	}

}
