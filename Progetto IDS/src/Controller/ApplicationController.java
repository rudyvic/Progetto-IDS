package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import Model.Catalog;
import Model.Disc;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class ApplicationController {
	private static ApplicationController applicationController = null;

	private JFrame frame;
	private JPanel mainPanel;
	public ControllerTopbar controllerTopbar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationController window = ApplicationController.getInstance();
					window.initialize();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Application controller è un singleton
	 */
	private ApplicationController() {
		
	}
	
	public static ApplicationController getInstance(){
		if(applicationController == null){
			applicationController = new ApplicationController();
		}
		return applicationController;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setTitle("ApplicationController");
		frame.setBounds(100, 100, 900, 600);
		frame.setMinimumSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		controllerTopbar = new ControllerTopbar();
		frame.getContentPane().add(controllerTopbar.getPanel(), BorderLayout.NORTH);

		ControllerHome controllerHome = new ControllerHome();
		mainPanel = controllerHome.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	public void showHome() {
		ControllerHome controllerHome = new ControllerHome();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerHome.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void showCart() {
		ControllerCart controllerCart = ControllerCart.getInstance();
		controllerCart.setList();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerCart.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void showLogin() {
		ControllerLogin controllerLogin = new ControllerLogin();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerLogin.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showDiscPage(Disc disc) {
		ControllerDiscPage controllerDiscPage = new ControllerDiscPage(disc);
	}
	
	public void showSignin() {
		ControllerSignin controllerSignin = new ControllerSignin();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerSignin.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showAdminHome() {
		ControllerAdminHome controllerAdminHome = new ControllerAdminHome();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminHome.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showAdminAddNewDisc() {
		ControllerAdminAddNewDisc controllerAdminAddNewDisc = new ControllerAdminAddNewDisc();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminAddNewDisc.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showAdminEditDiscQuantity() {
		this.showAdminEditDiscQuantity(-1);
	}
	
	public void showAdminEditDiscQuantity(int discCode) {
		ControllerAdminEditDiscQuantity controllerAdminEditDiscQuantity = new ControllerAdminEditDiscQuantity(discCode);
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminEditDiscQuantity.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showPayment() {
		ControllerPayment controllerPayment = new ControllerPayment();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerPayment.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void login(String username, boolean isAdmin, boolean isSuper) {
		ControllerCart c = ControllerCart.getInstance();
		c.login(username);
		controllerTopbar.setDiscsInCart(c.cartLength());
		controllerTopbar.login(username,isAdmin,isSuper);
	}
	
	public void logout() {
		controllerTopbar.logout();
		ControllerCart c = ControllerCart.getInstance();
		c.logout();
	}

	public void showCatalog(String title, String genre, Double minPrice, Double maxPrice) {
		ControllerCatalog controllerCatalog = new ControllerCatalog(title,genre,minPrice,maxPrice);
		frame.getContentPane().remove(mainPanel);
		controllerCatalog.showCatalogWith(title, genre, minPrice, maxPrice);
		mainPanel = controllerCatalog.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void discInTheCart(Disc d) {
		ControllerCart controllerCart = ControllerCart.getInstance();
		controllerCart.add(d);
		controllerTopbar.addDiscToCart();
	}
	
	public boolean cartContains(Disc d){
		ControllerCart controllerCart = ControllerCart.getInstance();
		return controllerCart.contains(d);
	}
	
	public int getQuantity(Disc d){
		ControllerCatalog controllerCatalog = new ControllerCatalog();
		return controllerCatalog.getQuantity(d);
	}
}
