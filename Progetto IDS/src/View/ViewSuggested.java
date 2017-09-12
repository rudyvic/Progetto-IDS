package View;

import Model.Disc;
import Model.ModelSuggested;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewSuggested extends Observable implements Observer, ActionListener {
	private ModelSuggested model;
	private JPanel panel;
	
	public ViewSuggested(ModelSuggested model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelGrid = new JPanel();
		panelGrid.setPreferredSize(new Dimension(500, 200));
		panel.add(panelGrid, BorderLayout.CENTER);
		panelGrid.setLayout(new GridLayout(1, 5, 0, 0));
		
		for(Disc disc : model.getSuggested().getCatalog()) {
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(2,1,0,0));
			panelGrid.add(p);
			
			JButton button = new JButton(disc.getTitle());
			button.addActionListener(this);
			button.setActionCommand("btn" + disc.getCode());
			button.setBorder(null);
			p.add(button);
		}
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
