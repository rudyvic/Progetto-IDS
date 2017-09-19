package View;

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
import Model.ModelCartEntry;

public class ViewCartEntry extends Observable implements Observer, ActionListener{
	
	private ModelCartEntry model = new ModelCartEntry();
	
	private JLabel lblQuantita;
	private JLabel lblTitolo;
	private JButton btnMinus;
	private JPanel panel;
	
	public ViewCartEntry(ModelCartEntry model, Disc disc, int quantity) {
		
		this.model = model;
		model.setQuantity(quantity,null);
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(700, 40));
		
		JPanel panelTitolo = new JPanel();
		FlowLayout fl_panelTitolo = (FlowLayout) panelTitolo.getLayout();
		fl_panelTitolo.setAlignment(FlowLayout.LEFT);
		
		lblTitolo = new JLabel(disc.getTitle());
		lblTitolo.setPreferredSize(new Dimension(300, 30));
		panelTitolo.add(lblTitolo);
		
		JPanel panelPrezzo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPrezzo.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		
		JLabel lblPrezzo = new JLabel(disc.getPrice() + "€");
		lblPrezzo.setPreferredSize(new Dimension(80, 30));
		lblPrezzo.setHorizontalAlignment(SwingConstants.TRAILING);
		panelPrezzo.add(lblPrezzo);
		
		JPanel panelQuantita = new JPanel();
		
		btnMinus = new JButton("-");
		btnMinus.addActionListener(this);
		btnMinus.setActionCommand("btnMinus");
		btnMinus.setPreferredSize(new Dimension(29, 29));
		panelQuantita.add(btnMinus);
		
		lblQuantita = new JLabel(Integer.toString(quantity));
		panelQuantita.add(lblQuantita);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(this);
		btnPlus.setActionCommand("btnPlus");
		btnPlus.setPreferredSize(new Dimension(29, 29));
		panelQuantita.add(btnPlus);
		
		JPanel panelRimuovi = new JPanel();
		
		JButton btnRimuovi = new JButton("Rimuovi");
		panelRimuovi.add(btnRimuovi);
		btnRimuovi.addActionListener(this);
		btnRimuovi.setActionCommand("btnRimuovi");
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		panel.add(panelTitolo);
		panel.add(panelPrezzo);
		panel.add(panelQuantita);
		panel.add(panelRimuovi);
		
		if(quantity==0) {
			btnMinus.setEnabled(false);
		}
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			if("minus".equals((String)arg)){
				lblQuantita.setText(Integer.toString(model.getQuantity()));
				if(model.getQuantity()==0) {
					btnMinus.setEnabled(false);
				}
			}
			else if("plus".equals((String)arg)){
				lblQuantita.setText(Integer.toString(model.getQuantity()));
				if(model.getQuantity()==1) {
					btnMinus.setEnabled(true);
				}
			}
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnMinus".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("minus");
		} else if("btnPlus".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("plus");
		} else if("btnRimuovi".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("remove");
		}
	}
}
