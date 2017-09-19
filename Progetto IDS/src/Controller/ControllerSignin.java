package Controller;

import Model.DatabaseQuery;
import Model.Disc;
import Model.ModelSignin;
import View.ViewSignin;

import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ControllerSignin implements Observer {
	private ModelSignin model;
	private ViewSignin view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerSignin() {
		this.model = new ModelSignin();
		this.view = new ViewSignin(model);
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
			} else if("proceed".equals((String)arg)){
				DatabaseQuery db = DatabaseQuery.getInstance();
				int esito = db.signin(view.getPerson());
				if(esito==1) {
					JOptionPane.showMessageDialog(null, "User signed in with success.", "OK", JOptionPane.INFORMATION_MESSAGE, null);
					controller.showHome();
				} else if(esito==-1) {
					JOptionPane.showMessageDialog(null, "Impossible create this user: username already exists.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null, "Impossible create this user.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		}
	}

}
