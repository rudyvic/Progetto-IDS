package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Disc;
import Model.ModelCartEntry;
import View.ViewCartEntry;

public class ControllerCartEntry implements Observer{
	
	private ModelCartEntry model;
	private ViewCartEntry view;
	
	public ControllerCartEntry(Disc d, int quantita){
		this.model = new ModelCartEntry();
		this.view = new ViewCartEntry(model, d, quantita);
		view.addObserver(this);
	}
	
	public JPanel getPanel(){
		return view.getPanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
