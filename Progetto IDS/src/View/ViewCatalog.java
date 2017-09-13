package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Model.Catalog;
import Model.Disc;
import Model.ModelCatalog;

public class ViewCatalog extends Observable implements Observer, ActionListener{
	
	private JTextField txtCerca;
	private JPanel panelFiltri;
	private JTextField txtPrezzoMin;
	private JTextField txtPrezzoMax;
	private JComboBox<String> cbxGenere;
	private JPanel panel ;
	private ModelCatalog model;
	
	public ViewCatalog(ModelCatalog model, String nomeProdotto, String genere, String prezzoMin, String prezzoMax) {		
		
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panel.add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblCatalogo = new JLabel("Catalogo");
		lblCatalogo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panelNorth.add(lblCatalogo);
		
		JPanel panelWest = new JPanel();
		panel.add(panelWest, BorderLayout.WEST);
		
		JPanel panelEast = new JPanel();
		panel.add(panelEast, BorderLayout.EAST);
		
		JPanel panelCenter = new JPanel();
		panel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCerca = new JPanel();
		panelCenter.add(panelCerca, BorderLayout.NORTH);
		
		txtCerca = new JTextField();
		panelCerca.add(txtCerca);
		txtCerca.setColumns(40);
		txtCerca.setText(nomeProdotto);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(this);
		btnCerca.setActionCommand("filtro");
		panelCerca.add(btnCerca);
		
		// pannello dei filtri
		panelFiltri = new JPanel();
		panelCenter.add(panelFiltri, BorderLayout.WEST);
		
		JPanel panelFiltriInterno = new JPanel();
		panelFiltri.add(panelFiltriInterno);
		panelFiltriInterno.setBackground(Color.LIGHT_GRAY);
		panelFiltriInterno.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblFiltri = new JLabel("Filtri:");
		lblFiltri.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panelFiltriInterno.add(lblFiltri);
		
		JPanel panelGenere = new JPanel();
		FlowLayout fl_panelGenere = (FlowLayout) panelGenere.getLayout();
		fl_panelGenere.setAlignment(FlowLayout.LEFT);
		panelFiltriInterno.add(panelGenere);
		
		JLabel lblGenere = new JLabel("Genere");
		panelGenere.add(lblGenere);
		
		cbxGenere = new JComboBox<String>();
		cbxGenere.addActionListener(this);
		cbxGenere.setActionCommand("filtro");
		panelGenere.add(cbxGenere);
		
		// Inserisco i generi presenti nella combo box
		comboGenere(genere);
		
		if(prezzoMin == null || prezzoMin.equals(""))
			prezzoMin = "0.00";
		if(prezzoMax == null || prezzoMax.equals(""))
			prezzoMax = Double.toString(model.getMaxPrice());
		
		JPanel panelPrezzoMin = new JPanel();
		panelFiltriInterno.add(panelPrezzoMin);
		
		JLabel lblPrezzoMin = new JLabel("Prezzo min");
		panelPrezzoMin.add(lblPrezzoMin);
		
		txtPrezzoMin = new JTextField(prezzoMin);
		panelPrezzoMin.add(txtPrezzoMin);
		txtPrezzoMin.setColumns(10);
		txtPrezzoMin.setText(prezzoMin);
		
		JPanel panelPrezzoMax = new JPanel();
		panelFiltriInterno.add(panelPrezzoMax);
		
		JLabel lblPrezzoMax = new JLabel("Prezzo max");
		panelPrezzoMax.add(lblPrezzoMax);
		
		txtPrezzoMax = new JTextField(prezzoMax);
		panelPrezzoMax.add(txtPrezzoMax);
		txtPrezzoMax.setColumns(10);
		txtPrezzoMax.setText(prezzoMax);
		
		JPanel panelCenterElenco = new JPanel();
		panelCenter.add(panelCenterElenco, BorderLayout.CENTER);
		panelCenterElenco.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPaneElenco = new JScrollPane();
		scrollPaneElenco.setPreferredSize(new Dimension(550, 350));
		scrollPaneElenco.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelCenterElenco.add(scrollPaneElenco);
		
		JPanel panelElenco = new JPanel();
		scrollPaneElenco.setViewportView(panelElenco);
		panelElenco.setLayout(new GridLayout(0, 1, 5, 0));
		
		/*
		for(Disc disco : model.searchCatalog(nomeProdotto, genere, Double.valueOf(prezzoMin), Double.valueOf(prezzoMax))) {
			PanelCellaTabellaCatalogo panel = new PanelCellaTabellaCatalogo(controller,disco);
			panelElenco.add(panel);
			if(controller.isProdottoInCarrello(disco)) {
				panel.setGiaNelCarrello();
			} else if(catalogo.getQuantita(disco) <= 0) {
				panel.prodottoTerminato(true);
			}
		}
		*/
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	private void comboGenere(String genere) {
		DefaultComboBoxModel<String> cbxModel = new DefaultComboBoxModel<String>();
		Catalog c = model.synchCatalog();
		ArrayList <String> gen = new ArrayList <String> ();
		for (Disc d : c.getCatalog()){
			System.out.println(d.getGenre());
			if(!gen.contains(d.getGenre())){
				gen.add(d.getGenre());
				cbxModel.addElement(d.getGenre());
			}
		}
		
		cbxGenere.setModel(cbxModel);
		
		cbxGenere.removeActionListener(this);
		cbxGenere.setSelectedItem(genere);
		cbxGenere.addActionListener(this);
		
		panel.repaint();
		panel.revalidate();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("filtro".equals(e.getActionCommand())){
			this.setChanged();
			this.notifyObservers("filtro");
		}	
	}
}