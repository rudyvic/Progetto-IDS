package View;

import Model.Cart;
import Model.Disc;
import Model.ModelCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ViewCart extends Observable implements Observer, ActionListener {
	private ModelCart model;
	private JPanel panel;
	
	private final JPanel userPanel = new JPanel();
	private final JPanel adminPanel = new JPanel();
	private final JPanel logSignPanel = new JPanel();
	private JPanel eastPanel;
	private JButton btnCart;
	
	public ViewCart(ModelCart model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel();
		scrollPane.setViewportView(centerPanel);
		
		JPanel centerPanel_2 = new JPanel();
		centerPanel_2.setLayout(new GridLayout(0, 1, 0, 0));
		centerPanel.add(centerPanel_2);
		
		JPanel southPanel = new JPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		JButton btnEmptyCart = new JButton("Empty cart");
		btnEmptyCart.addActionListener(this);
		btnEmptyCart.setActionCommand("btnEmptyCart");
		southPanel.add(btnEmptyCart);
		
		JButton btnProceedToPayment = new JButton("Proceed to payment");
		btnProceedToPayment.addActionListener(this);
		btnProceedToPayment.setActionCommand("btnProceedToPayment");
		southPanel.add(btnProceedToPayment);
		
		JLabel lblTotalPrice = new JLabel("TotalPrice");
		southPanel.add(lblTotalPrice);
		
		
		for(Disc disc : model.getCatalog()) {
		    int quantita = model.getQuantity(disc);

			//JPanel p = new PanelCellaTabellaCarrello(controller,disco,quantita);
			//centerPanel_2.add(p);
			
			/*
			if(controller.isSuper(controller.getUsername()))
				prezzoTotale += (disco.getPrezzo()*quantita)*0.3;
				*/
		}
		
		lblTotalPrice.setText(Double.toString(prezzoTotale) + "€");
	}
	
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnEmptyCart".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("empty cart");
		} else if("btnProceedToPayment".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("payment");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers("update");
	}
}
