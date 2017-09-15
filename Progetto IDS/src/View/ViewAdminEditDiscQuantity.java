package View;

import Model.Disc;
import Model.ModelAdminEditDiscQuantity;

import java.awt.*;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewAdminEditDiscQuantity extends Observable implements Observer, ActionListener {
	private ModelAdminEditDiscQuantity model;
	private JPanel panel;
	
	private JTextField txtNewQuantity;
	private static JLabel lblOldQuantity;
	private JComboBox<Integer> cbxCodes;
	private static JLabel lblTitle;

	
	public ViewAdminEditDiscQuantity(ModelAdminEditDiscQuantity model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JLabel lblInsertCode = new JLabel("<html>Insert the disc code: ");
		northPanel.add(lblInsertCode);
		
		JPanel centerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) centerPanel.getLayout();
		flowLayout.setVgap(50);
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel tablePanel = new JPanel();
		centerPanel.add(tablePanel);
		tablePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel codePanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) codePanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		tablePanel.add(codePanel);
		
		JLabel lblCode = new JLabel("Code:");
		codePanel.add(lblCode);
		
		cbxCodes = new JComboBox<Integer>();
		cbxCodes.addActionListener(this);
		cbxCodes.setActionCommand("cbx");
		codePanel.add(cbxCodes);
		
		// riempio il combo box con i codici
		fillCodes(model.getDiscCode());
		
		JPanel titlePanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) titlePanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		tablePanel.add(titlePanel);
		
		JLabel lblTitlet = new JLabel("Title:");
		titlePanel.add(lblTitlet);
		
		Disc disc = model.getCatalog().getDisc((Integer)cbxCodes.getSelectedItem());
		lblTitle = new JLabel(disc.getTitle());
		titlePanel.add(lblTitle);
		
		JPanel oldQuantityPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) oldQuantityPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		tablePanel.add(oldQuantityPanel);
		
		lblOldQuantity = new JLabel("Acqual quantity: " + model.getCatalog().getQuantity(disc));
		oldQuantityPanel.add(lblOldQuantity);
		
		JPanel newQuantityPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) newQuantityPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		tablePanel.add(newQuantityPanel);
		
		JLabel lblNewQuantity = new JLabel("New quantity: ");
		newQuantityPanel.add(lblNewQuantity);
		
		txtNewQuantity = new JTextField();
		newQuantityPanel.add(txtNewQuantity);
		txtNewQuantity.setColumns(10);
		
		JPanel panelSouth = new JPanel();
		panel.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("btnCancel");
		panelSouth.add(btnCancel);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(this);
		btnApply.setActionCommand("btnApply");
		panelSouth.add(btnApply);

	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getSelectedDisc() {
		return (String)cbxCodes.getSelectedItem();
	}
	
	private void updateQuantity() {
		Disc disc = model.getCatalog().getDisc((Integer)cbxCodes.getSelectedItem());
		lblOldQuantity.setText("Old quantity: " + model.getCatalog().getQuantity(disc));
		lblTitle.setText(disc.getTitle());
		panel.revalidate();
	}
	
	private void fillCodes(int discCode) {
		DefaultComboBoxModel<Integer> cbxModel = new DefaultComboBoxModel<Integer>();
		for(Disc disc : model.getCatalog().getCatalog()) {
			cbxModel.addElement(disc.getCode());
		}
		
		cbxCodes.setModel(cbxModel);
		
		cbxCodes.setSelectedItem(discCode); 
		
		panel.repaint();
		panel.revalidate();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if("cbx".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("disc change");
		} else if("btnCancel".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cancel");
		} else if ("btnApply".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("apply");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
