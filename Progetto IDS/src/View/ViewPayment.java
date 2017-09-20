package View;

import Model.Disc;
import Model.ModelPayment;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewPayment extends Observable implements Observer, ActionListener {
	private ModelPayment model;
	private JPanel panel;
	
	public ViewPayment(ModelPayment model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		
	}
	
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("btn")) {
			this.setChanged();
			this.notifyObservers();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
