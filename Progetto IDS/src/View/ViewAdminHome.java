package View;

import Model.Catalog;
import Model.Disc;
import Model.ModelAdminHome;

import java.awt.*;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewAdminHome extends Observable implements Observer, ActionListener {
	private ModelAdminHome model;
	private JPanel panel;
	
	private JPanel runningOutDiscsPanel;
	private JPanel listCatalogPanel;
	
	public ViewAdminHome(ModelAdminHome model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(600, 500));
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JLabel lblDescription = new JLabel("<html>Welcome in this admin homepage. Here you can see which discs are running out of stock and change their quantity, or you can add new discs to the catalog.");
		lblDescription.setPreferredSize(new Dimension(500, 100));
		northPanel.add(lblDescription);
		
		runningOutDiscsPanel = new JPanel();
		panel.add(runningOutDiscsPanel, BorderLayout.WEST);
		runningOutDiscsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northRunningOutDiscsPanel = new JPanel();
		runningOutDiscsPanel.add(northRunningOutDiscsPanel, BorderLayout.NORTH);
		
		JLabel lblRunningOutDiscs = new JLabel("Discs running out:");
		northRunningOutDiscsPanel.add(lblRunningOutDiscs);
		
		JPanel centerRunningOutDiscsPanel = new JPanel();
		runningOutDiscsPanel.add(centerRunningOutDiscsPanel, BorderLayout.CENTER);
		
		JPanel tableRunningOutDiscsPanel = new JPanel();
		centerRunningOutDiscsPanel.add(tableRunningOutDiscsPanel);
		tableRunningOutDiscsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel centerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) centerPanel.getLayout();
		flowLayout.setVgap(50);
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JButton btnAddDisc = new JButton("Add new disc");
		btnAddDisc.addActionListener(this);
		btnAddDisc.setActionCommand("btnAddDisc");
		btnAddDisc.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnAddDisc.setPreferredSize(new Dimension(300, 100));
		centerPanel.add(btnAddDisc);
		
		JButton btnEditDiscQuantity = new JButton("Edit discs quantity");
		btnEditDiscQuantity.addActionListener(this);
		btnEditDiscQuantity.setActionCommand("btnEditDiscQuantity");
		btnEditDiscQuantity.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnEditDiscQuantity.setPreferredSize(new Dimension(300, 100));
		centerPanel.add(btnEditDiscQuantity);
	}
	
	public void fillCatalogRunningOut() {
		runningOutDiscsPanel.removeAll();
		listCatalogPanel = new JPanel();
		runningOutDiscsPanel.add(listCatalogPanel, BorderLayout.CENTER);
		listCatalogPanel.setPreferredSize(new Dimension(500, 200));
		listCatalogPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
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
		panel.revalidate();
	}
	
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnAddDisc".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("add disc");
		} else if("btnEditDiscQuantity".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("edit discs quantity");
		} else if (e.getActionCommand().contains("btn")) {
			String discCode = e.getActionCommand().substring(3, e.getActionCommand().length());
			this.setChanged();
			this.notifyObservers(Integer.valueOf(discCode));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
