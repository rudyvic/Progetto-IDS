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
	
	private JLabel lblPrezzo;
	private JButton btnTitolo, btnAggiungiCarrello;
	private JPanel panelLeft;
	private JPanel panelRight;
	
	public ViewCatalogEntry(ModelCatalogEntry model){
		
		Disc disc = model.getDisc();
		
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 40));
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelLeft = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelLeft.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel.add(panelLeft);
		
		btnTitolo = new JButton(disc.getTitle());
		btnTitolo.setHorizontalAlignment(SwingConstants.LEFT);
		panelLeft.add(btnTitolo);
		btnTitolo.addActionListener(this);
		btnTitolo.setActionCommand("btnNomeprodotto");
		btnTitolo.setPreferredSize(new Dimension(300, 30));
		btnTitolo.setBorder(null);
		
		panelRight = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelRight.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panelRight);
		
		lblPrezzo = new JLabel(Double.toString(disc.getPrice()) + "€");
		panelRight.add(lblPrezzo);
		
		btnAggiungiCarrello = new JButton("Aggiungi al carrello");
		panelRight.add(btnAggiungiCarrello);
		btnAggiungiCarrello.addActionListener(this);
		btnAggiungiCarrello.setActionCommand("btnAggiungiCarrello");
		btnAggiungiCarrello.setForeground(Color.BLUE);
		btnAggiungiCarrello.setPreferredSize(new Dimension(135, 30));
		btnAggiungiCarrello.setBorder(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnNomeprodotto".equals(e.getActionCommand())) {
			System.out.println("Nome prodotto");
		} else if("btnAggiungiCarrello".equals(e.getActionCommand())) {
			System.out.println("Aggiungi al carrello");
		}
	}

	@Override
	public void update(Observable o, Object arg) {		
	}

	public JPanel getPanel() {
		return panel;
	}

}
