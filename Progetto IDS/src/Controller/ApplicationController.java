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
	
	/*
	public void showSignin() {
		frame.getContentPane().remove(panel);
		panel = new PanelSignin(controller);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showPersonale() {
		frame.getContentPane().remove(panel);
		panel = new PanelPersonaleHome(controller);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showPersonaleAggiuntaDisco() {
		frame.getContentPane().remove(panel);
		panel = new PanelPersonaleAggiuntaDisco(controller);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showPersonaleModificaQuantitaDisco() {
		this.showPersonaleModificaQuantitaDisco(-1);
	}
	
	public void showPersonaleModificaQuantitaDisco(int codiceDisco) {
		frame.getContentPane().remove(panel);
		panel = new PanelPersonaleModificaQuantitaDisco(controller,codiceDisco);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void showPagamento(Double prezzoTot) {
		frame.getContentPane().remove(panel);
		panel = new PanelPagamento(controller,controller.getCarrello(),prezzoTot);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.revalidate();
	}
	*/
	
	public void login(String username, boolean isAdmin, boolean isSuper) {
		controllerTopbar.login(username,isAdmin,isSuper);
	}
	
	public void logout() {
		controllerTopbar.logout();
	}

	public void showCatalog(String title, String genre, Double minPrice, Double maxPrice) {
		ControllerCatalog controllerCatalog = new ControllerCatalog();
		frame.getContentPane().remove(mainPanel);
		controllerCatalog.showCatalogWith(title, genre, minPrice, maxPrice);
		mainPanel = controllerCatalog.getPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
}
