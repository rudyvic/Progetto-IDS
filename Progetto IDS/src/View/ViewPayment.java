package View;

import Model.Disc;
import Model.ModelPayment;

import java.awt.*;

import javax.swing.*;

import Controller.ApplicationController;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewPayment extends Observable implements Observer, ActionListener {
	private ModelPayment model;
	private JPanel panel;
	
	private JComboBox<String> cbxPaymentType;
	
	public ViewPayment(ModelPayment model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		cbxPaymentType = new JComboBox<String>();
		cbxPaymentType.setModel(new DefaultComboBoxModel(new String[] {"Carta di credito", "Bonifico", "PayPal"}));
		centerPanel.add(cbxPaymentType);
		
		JLabel lblTotalPrice = new JLabel(model.getPrice() + "€");
		centerPanel.add(lblTotalPrice);
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		northPanel.add(lblPayment);
		
		JPanel southPanel = new JPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("btnCancel");
		southPanel.add(btnCancel);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(this);
		btnBuy.setActionCommand("btnBuy");
		southPanel.add(btnBuy);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public String getPaymentType() {
		System.out.println(cbxPaymentType.getSelectedItem());
		return (String)cbxPaymentType.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCancel".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cancel");
		} else if("btnBuy".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("buy");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
