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
		frame.setBounds(100, 100, 800, 600);
		frame.setMinimumSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		ControllerCart controllerCart = new ControllerCart();
		mainPanel = controllerCart.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		controllerTopbar = new ControllerTopbar();
		frame.getContentPane().add(controllerTopbar.getPanel(), BorderLayout.NORTH);
	}
	
	public void showHome() {
		ControllerHome controllerHome = new ControllerHome();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerHome.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void showCart() {
		ControllerCart controllerCart = new ControllerCart();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerCart.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
/*
	public void exitFromCarrello() {
		System.out.println("Uscita dal carrelo");
	}
	
	public void showCatalogo(CatalogoApplicationController catalogo) {
		frame.getContentPane().remove(panel);
		panel = new PanelCatalogo(catalogo,controller);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showCatalogoWith(CatalogoApplicationController catalogo, String nomeProdotto, String genere, String prezzoMin, String prezzoMax) {
		frame.getContentPane().remove(panel);
		panel = new PanelCatalogo(catalogo,controller,nomeProdotto, genere, prezzoMin, prezzoMax);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	*/
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
		/*
		ControllerSignin controllerSignin = new ControllerSignin();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerSignin.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();*/
	}
	
	public void showAdminHome() {
		ControllerAdminHome controllerAdminHome = new ControllerAdminHome();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminHome.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showAdminAddNewDisc() {
		/*
		ControllerAdminAddNewDisc controllerAdminAddNewDisc = new ControllerAdminAddNewDisc();
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminAddNewDisc.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();*/
	}
	
	public void showAdminEditDiscQuantity() {
		this.showAdminEditDiscQuantity(-1);
	}
	
	public void showAdminEditDiscQuantity(int discCode) {
		/*
		ControllerAdminEditDiscQuantity controllerAdminEditDiscQuantity = new ControllerAdminEditDiscQuantity(discCode);
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerAdminEditDiscQuantity.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();*/
	}
	
	public void showPayment(Double price) {
		/*
		ControllerPayment controllerPayment = new ControllerPayment(price);
		frame.getContentPane().remove(mainPanel);
		mainPanel = controllerPayment.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();*/
	}
	
	public void login(String username, boolean isAdmin, boolean isSuper) {
		controllerTopbar.login(username,isAdmin,isSuper);
	}
	
	public void logout() {
		controllerTopbar.logout();
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
		ControllerCart controllerCart = new ControllerCart();
		controllerCart.add(d);
		controllerTopbar.addDiscToCart();
	}
	
	public boolean cartContains(Disc d){
		ControllerCart c = new ControllerCart();
		return c.contains(d);
	}
}
