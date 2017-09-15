package Controller;

import Model.ModelLogin;
import View.ViewLogin;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ControllerLogin implements Observer {
	private ModelLogin model;
	private ViewLogin view;
	private ApplicationController controller = ApplicationController.getInstance();
	
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
		if(arg instanceof String) {
			if("cancel".equals((String)arg)){
				controller.showHome();
			} else if("login".equals((String)arg)){
				if (model.checkUserPass(view.getUsername(),view.getPassword())){
					if (model.isAdmin()) {
						controller.login(view.getUsername(), true, false);
					} else if(model.isClient()) {
						controller.login(view.getUsername(), false, false);
					}
					controller.showHome();
				} else {
					JOptionPane.showMessageDialog(null, "Username or password wrong", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		}
	}

}
