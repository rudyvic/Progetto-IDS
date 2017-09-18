package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Disc;
import Model.ModelCatalogEntry;

public class ViewCatalogEntry extends Observable implements Observer, ActionListener {

	private ModelCatalogEntry model;
	private JPanel panel;
	
	private JLabel lblPrice;
	private JButton btnTitle, btnAddToCart;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	public ViewCatalogEntry(ModelCatalogEntry model){
		
		Disc disc = model.getDisc();
		
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 40));
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		leftPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) leftPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel.add(leftPanel);
		
		btnTitle = new JButton(disc.getTitle());
		btnTitle.setHorizontalAlignment(SwingConstants.LEFT);
		leftPanel.add(btnTitle);
		btnTitle.addActionListener(this);
		btnTitle.setActionCommand("btnDiscPage");
		btnTitle.setPreferredSize(new Dimension(300, 30));
		btnTitle.setBorder(null);
		
		rightPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) rightPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(rightPanel);
		
		lblPrice = new JLabel(Double.toString(disc.getPrice()) + "€");
		rightPanel.add(lblPrice);
		
		btnAddToCart = new JButton("Add to cart");
		rightPanel.add(btnAddToCart);
		btnAddToCart.addActionListener(this);
		btnAddToCart.setActionCommand("btnAddToCart");
		btnAddToCart.setForeground(Color.BLUE);
		btnAddToCart.setPreferredSize(new Dimension(135, 30));
		btnAddToCart.setBorder(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnDiscPage".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("disc page");
		} else if("btnAddToCart".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("add to cart");
		}
	}
	
	public void inTheCart() {
		btnAddToCart.setEnabled(false);
		btnAddToCart.setText("In the cart");
		btnAddToCart.setBorder(null);
	}

	@Override
	public void update(Observable o, Object arg) {	
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void soldOutProduct(boolean b) {
		if(b) {
			btnAddToCart.setEnabled(false);
			btnAddToCart.setText("Sold out");
			btnAddToCart.setBorder(null);
		} else {
			btnAddToCart.setEnabled(true);
			btnAddToCart.setText("Add to cart");
			btnAddToCart.setBorder(null);
		}
		
	}

}
