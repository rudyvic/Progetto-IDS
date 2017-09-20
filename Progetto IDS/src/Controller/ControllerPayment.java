package Controller;

import Model.DatabaseQuery;
import Model.ModelPayment;
import View.ViewPayment;

import java.util.*;

import javax.swing.JPanel;

public class ControllerPayment implements Observer {
	private ModelPayment model;
	private ViewPayment view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerPayment() {
		this.model = new ModelPayment();
		this.view = new ViewPayment(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer) {

		}
	}

}
