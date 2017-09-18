package View;

import Model.Disc;
import Model.ModelCart;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ViewCart extends Observable implements Observer, ActionListener {
	private ModelCart model;
	private JPanel panel;
	private JScrollPane scrollPane;

	public ViewCart(ModelCart model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
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
		
		if(model.isSuper(model.getUsername()))
			lblTotalPrice.setText(Double.toString(model.getTotalPrice()*0.7) + "€");
		
		else
			lblTotalPrice.setText(Double.toString(model.getTotalPrice()) + "€");
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setList(List<JPanel> list) {
		JPanel listPanel = new JPanel();
		scrollPane.setViewportView(listPanel);
		listPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		for(JPanel p : list) {
			listPanel.add(p);
		}
		
		panel.revalidate();
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
