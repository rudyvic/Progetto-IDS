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
	private JPanel eastPanel;
	private JButton btnCart;
	
	public ViewTopbar(ModelTopbar model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();

		panel.setBackground(Color.RED);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(500, 100));
		
		// costruisco il panel personale
		adminPanel.setBackground(Color.ORANGE);
		adminPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblAdminUsername = new JLabel("Username");
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
		userPanel.setBackground(Color.MAGENTA);
		userPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblUser = new JLabel("Username");
		userPanel.add(lblUser);
		
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
		btnCart.setEnabled(!isEmpty);
	}

	public void showLogSignPanel() {
		panel.remove(eastPanel);
		panel.add(logSignPanel, BorderLayout.EAST);
		eastPanel = logSignPanel;
	}
	
	public void showAdminPanel() {
		panel.remove(eastPanel);
		panel.add(adminPanel, BorderLayout.EAST);
		eastPanel = adminPanel;
	}
	
	public void showUserPanel() {
		panel.remove(eastPanel);
		panel.add(userPanel, BorderLayout.EAST);
		eastPanel = userPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCarrello".equals(e.getActionCommand())) {
			//controller.showCarrello();
		} else if("btnHome".equals(e.getActionCommand())) {
			//controller.showHome();
		} else if("btnLogin".equals(e.getActionCommand())) {
			//controller.showLogin();
		} else if("btnLogout".equals(e.getActionCommand())) {
			/*controller.setAutenticato(false);
			controller.setPersonale(false);
			controller.setSuperUtente(false);
			controller.showHome();*/
		} else if("btnSignin".equals(e.getActionCommand())) {
			//controller.showSignin();
		} else if("btnPersonale".equals(e.getActionCommand())) {
			//controller.showPersonale();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}


	
/*
	
	public void refreshEastPanel() {
		this.remove(panelEast);
		
		if(isAutenticato==false) {
			panelEast = panelLS;
		} else if(isPersonale==false) {
			panelEast = panelUtente;
			((JLabel)panelEast.getComponent(0)).setText(username);
			controller.showHome();
		} else {
			panelEast = panelPersonale;
			((JLabel)panelPersonale.getComponent(0)).setText(username);
			controller.showHome();
		}

		this.add(panelEast, BorderLayout.EAST);
		this.repaint();
		this.revalidate();
	}

	public void aggiungiAlCarrello() {
		oggettiNelCarrello++;
		btnCarrello.setText("Carrello (" + Integer.toString(oggettiNelCarrello) + ")");
		checkCarrelloVuoto();
	}
	
	public void rimuoviDalCarrello() {
		oggettiNelCarrello--;
		btnCarrello.setText("Carrello (" + Integer.toString(oggettiNelCarrello) + ")");
		checkCarrelloVuoto();
		
		if(oggettiNelCarrello==0) {
			controller.showHome();
		} else {
			controller.showCarrello();
		}
	}
	
	public void svuotaCarrello() {
		oggettiNelCarrello = 0;
		btnCarrello.setText("Carrello (0)");
		checkCarrelloVuoto();
		this.repaint();
	}
	
	*/
