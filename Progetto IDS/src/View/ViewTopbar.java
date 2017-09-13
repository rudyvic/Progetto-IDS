package View;

import Model.ModelTopbar;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ViewTopbar extends Observable implements Observer, ActionListener {
	private ModelTopbar model;
	private JPanel panel;
	
	public ViewTopbar(ModelTopbar model) {
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
			String codiceDisco = e.getActionCommand().substring(3, e.getActionCommand().length());
			//controller.showCatalogoWith(suggeriti.getDisco(Integer.valueOf(codiceDisco)).getTitolo(), null, null, null);
			this.setChanged();
			this.notifyObservers(codiceDisco);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
