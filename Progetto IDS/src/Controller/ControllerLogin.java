package Controller;

import Model.ModelLogin;
import View.ViewLogin;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ControllerLogin implements Observer {
	private ModelLogin model;
	private ViewLogin view;
	private ControllerHome controllerHome;
	
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
			// Richiamo il controller per visualizzare l'home page
			view.getPanel();
		}
		
	}

}
