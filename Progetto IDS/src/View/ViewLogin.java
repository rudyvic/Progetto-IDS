package View;

import Model.ModelLogin;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	private ModelLogin model;
	private JPanel panel;
	
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	public ViewLogin(ModelLogin model) {
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JLabel lblLogin = new JLabel("Log In");
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		northPanel.add(lblLogin);
		
		JPanel southPanel = new JPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("btnCancel");
		southPanel.add(btnCancel);
		
		Component horizontalStrut = Box.createHorizontalStrut(130);
		southPanel.add(horizontalStrut);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.addActionListener(this);
		btnLogin.setActionCommand("btnLogin");
		southPanel.add(btnLogin);
		
		JPanel westPanel = new JPanel();
		panel.add(westPanel, BorderLayout.WEST);
		
		JPanel eastPanel = new JPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{94, 327, 0};
		gbl_panelCenter.rowHeights = new int[] {0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0};
		centerPanel.setLayout(gbl_panelCenter);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		centerPanel.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.fill = GridBagConstraints.BOTH;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 0;
		centerPanel.add(txtUsername, gbc_textFieldUsername);
		txtUsername.setColumns(40);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		centerPanel.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.fill = GridBagConstraints.BOTH;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 1;
		centerPanel.add(txtPassword, gbc_textFieldPassword);
		txtPassword.setColumns(40);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public String getUsername() {
		return txtUsername.getText();
	}
	
	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCancel".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cancel");
		} else if("btnLogin".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("login");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
