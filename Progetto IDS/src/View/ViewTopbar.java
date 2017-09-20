package View;

import Model.ModelTopbar;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ViewTopbar extends Observable implements Observer, ActionListener {
	private ModelTopbar model;
	private JPanel panel;
	
	private final JPanel userPanel = new JPanel();
	private final JPanel adminPanel = new JPanel();
	private final JPanel logSignPanel = new JPanel();
	private JLabel lblUserUsername = new JLabel("Username");
	private JLabel lblAdminUsername = new JLabel("Username");
	private JPanel eastPanel;
	private JButton btnCart;
	
	public ViewTopbar(ModelTopbar model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();

		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(500, 100));
		
		// costruisco il panel personale
		adminPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		adminPanel.add(lblAdminUsername);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		adminPanel.add(horizontalStrut);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(this);
		btnAdmin.setActionCommand("btnAdmin");
		adminPanel.add(btnAdmin);
		
		JButton btnAdminLogout = new JButton("Log Out");
		btnAdminLogout.addActionListener(this);
		btnAdminLogout.setActionCommand("btnLogout");
		adminPanel.add(btnAdminLogout);
		
		// costruisco il panel utente
		userPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		userPanel.add(lblUserUsername);
		
		JButton btnUserLogout = new JButton("Log Out");
		userPanel.add(btnUserLogout);
		btnUserLogout.addActionListener(this);
		btnUserLogout.setActionCommand("btnLogout");
		
		// costruisco il panel del carrello
		JPanel cartPanel = new JPanel();
		panel.add(cartPanel, BorderLayout.WEST);
		cartPanel.setLayout(new BorderLayout(0, 0));
		
		btnCart = new JButton("Cart (" + Integer.toString(model.getDiscsInCart()) + ")");
		btnCart.addActionListener(this);
		btnCart.setActionCommand("btnCart");
		cartPanel.add(btnCart);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		cartPanel.add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		cartPanel.add(verticalStrut_1, BorderLayout.NORTH);
		
		// costruisco il panel centrale
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 255, 255));
		panel.add(titlePanel, BorderLayout.CENTER);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnHome = new JButton("Store");
		btnHome.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		btnHome.setBorder(null);
		btnHome.addActionListener(this);
		btnHome.setActionCommand("btnHome");
		titlePanel.add(btnHome, BorderLayout.CENTER);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		titlePanel.add(verticalStrut_2, BorderLayout.NORTH);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		titlePanel.add(verticalStrut_3, BorderLayout.SOUTH);
		
		// costruisco il panel Login/Signin
		logSignPanel.setLayout(new GridLayout(0, 1, 0, 0));
		eastPanel = logSignPanel;
		
		JPanel loginPanel = new JPanel();
		logSignPanel.add(loginPanel);
		loginPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(this);
		btnLogin.setActionCommand("btnLogin");
		loginPanel.add(btnLogin);
		
		JPanel signinPanel = new JPanel();
		logSignPanel.add(signinPanel);
		signinPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSignin = new JButton("Sign In");
		btnSignin.addActionListener(this);
		btnSignin.setActionCommand("btnSignin");
		signinPanel.add(btnSignin);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void updateCart() {
		btnCart.setText("Cart (" + Integer.toString(model.getDiscsInCart()) + ")");
	}
	
	public void isCartEmpty(boolean isEmpty) {
		updateCart();
		btnCart.setEnabled(!isEmpty);
	}

	public void showLogSignPanel() {
		panel.remove(eastPanel);
		panel.add(logSignPanel, BorderLayout.EAST);
		eastPanel = logSignPanel;
		eastPanel.repaint();
	}
	
	public void showAdminPanel() {
		lblAdminUsername.setText(model.getUsername());
		panel.remove(eastPanel);
		panel.add(adminPanel, BorderLayout.EAST);
		eastPanel = adminPanel;
		eastPanel.repaint();
	}
	
	public void showUserPanel() {
		lblUserUsername.setText(model.getUsername());
		panel.remove(eastPanel);
		panel.add(userPanel, BorderLayout.EAST);
		eastPanel = userPanel;
		eastPanel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCart".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cart");
		} else if("btnHome".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("home");
		} else if("btnLogin".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("login");
		} else if("btnLogout".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("logout");
		} else if("btnSignin".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("signin");
		} else if("btnAdmin".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("admin");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
