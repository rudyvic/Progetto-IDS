package View;

import Model.ModelLogin;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JLabel;


import java.awt.Component;

import javax.swing.Box;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewLogin extends Observable implements Observer, ActionListener {
	private JTextField txtUsername;
	private JTextField txtPassword;
	private ModelLogin model;
	private JPanel panel;
	
	public ViewLogin(ModelLogin model) {
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panel.add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panelNorth.add(lblLogIn);
		
		JPanel panelSouth = new JPanel();
		panel.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(this);
		btnAnnulla.setActionCommand("btnAnnulla");
		panelSouth.add(btnAnnulla);
		
		Component horizontalStrut = Box.createHorizontalStrut(130);
		panelSouth.add(horizontalStrut);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(this);
		btnAccedi.setActionCommand("btnAccedi");
		panelSouth.add(btnAccedi);
		
		JPanel panelWest = new JPanel();
		panel.add(panelWest, BorderLayout.WEST);
		
		JPanel panelEast = new JPanel();
		panel.add(panelEast, BorderLayout.EAST);
		
		JPanel panelCenter = new JPanel();
		panel.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{94, 327, 0};
		gbl_panelCenter.rowHeights = new int[] {0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0};
		panelCenter.setLayout(gbl_panelCenter);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panelCenter.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.fill = GridBagConstraints.BOTH;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 0;
		panelCenter.add(txtUsername, gbc_textFieldUsername);
		txtUsername.setColumns(40);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenter.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.fill = GridBagConstraints.BOTH;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 1;
		panelCenter.add(txtPassword, gbc_textFieldPassword);
		txtPassword.setColumns(40);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnAnnulla".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("home");
		} else if("btnAccedi".equals(e.getActionCommand())) {
			model = new ModelLogin();
			if (model.checkUserPass(txtUsername.getText(),txtPassword.getText())){
				if (model.isAdmin())
					System.out.println("E' admin");
			
				else if(model.isClient())
					System.out.println("E' cliente");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
