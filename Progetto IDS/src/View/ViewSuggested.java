package View;

import Model.Disc;
import Model.ModelSuggested;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Image;
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
		
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(500, 200));
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 5, 0, 0));
		
		for(Disc disc : model.getSuggested().getCatalog()) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			centerPanel.add(p);

			JButton btnImage = new JButton();
			ImageIcon imageIcon = new ImageIcon(disc.getImage());
			Image image = imageIcon.getImage(); // transform it 
			image = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(image);
			btnImage.setIcon(imageIcon);
			btnImage.addActionListener(this);
			btnImage.setActionCommand("btn" + disc.getCode());
			p.add(btnImage);
		}
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
