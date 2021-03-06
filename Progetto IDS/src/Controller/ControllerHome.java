package Controller;

import Model.DatabaseQuery;
import Model.ModelHome;
import View.ViewHome;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;

import javax.swing.JPanel;

public class ControllerHome implements Observer {
	private ModelHome model;
	private ViewHome view;
	
	private DatabaseQuery db = DatabaseQuery.getInstance();
	
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerHome() {
		this.model = new ModelHome();
		this.view = new ViewHome(model);
		view.addObserver(this);
		
		if(controller.controllerTopbar.isLogin()) {
			view.showSuggested(controller.controllerTopbar.getUsername());
		}
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("catalog".equals(arg)) {
  				controller.showCatalog(null,null,Double.valueOf("0.00"),db.getMaxPrice());
			} else if("catalogWith".equals(arg)) {
  				controller.showCatalog(view.getFindText(),null,Double.valueOf("0.00"),db.getMaxPrice());
			}
		}
	}

}
