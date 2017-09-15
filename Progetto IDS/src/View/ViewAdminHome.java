package View;

import Model.Catalog;
import Model.Disc;
import Model.ModelAdminHome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewAdminHome extends Observable implements Observer, ActionListener {
	private ModelAdminHome model;
	private JPanel panel;
	
	private JPanel listCatalogPanel;
	
	public ViewAdminHome(ModelAdminHome model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		listCatalogPanel = new JPanel();
		listCatalogPanel.setPreferredSize(new Dimension(500, 200));
		panel.add(listCatalogPanel, BorderLayout.CENTER);
		listCatalogPanel.setLayout(new GridLayout(1, 5, 0, 0));
	}
	
	public void fillCatalogRunningOut() {
		panel.remove(listCatalogPanel);
		listCatalogPanel = new JPanel();
		panel.add(listCatalogPanel);
		
		Catalog cat = model.getCatalogRunningOut();
		for(Disc disc : cat.getCatalog()) {
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2,1,0,0));
			listCatalogPanel.add(p);
			
			JButton button = new JButton(disc.getTitle());
			button.addActionListener(this);
			button.setActionCommand("btn" + disc.getCode());
			button.setBorder(null);
			p.add(button);
			
			if(cat.getQuantity(disc)==0) {
				button.setForeground(Color.RED);
			} else if(cat.getQuantity(disc)==0) {
				button.setForeground(Color.ORANGE);
			} else {
				button.setForeground(Color.YELLOW);
			}
		}
		
		panel.repaint();
	}
	
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("btn")) {
			String discCode = e.getActionCommand().substring(3, e.getActionCommand().length());
			this.setChanged();
			this.notifyObservers(Integer.valueOf(discCode));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
