package View;

import Model.Catalog;
import Model.Disc;
import Model.ModelAdminAddNewDisc;

import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewAdminAddNewDisc extends Observable implements Observer, ActionListener {
	private ModelAdminAddNewDisc model;
	private JPanel panel;
	
	private JTextField txtTitle;
	private JTextField txtSongs;
	private JTextField txtPrice;
	private JTextField txtDescription;
	private JTextField txtGenre;
	private JDialog chooseDialog;
	private String band = null;
	private String musician = null;
	private JLabel lblImage;
	private JRadioButton rdbtnBand;
	private JRadioButton rdbtnMusician;
	
	private JTextField txtQuantity;
	private JTextField txtOwner;
	
	public ViewAdminAddNewDisc(ModelAdminAddNewDisc model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();

		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel titlePanel = new JPanel();
		centerPanel.add(titlePanel);
		
		JLabel lblTitle = new JLabel("Title");
		titlePanel.add(lblTitle);
		
		txtTitle = new JTextField();
		titlePanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JPanel songsPanel = new JPanel();
		centerPanel.add(songsPanel);
		
		JLabel lblSongs = new JLabel("Songs");
		songsPanel.add(lblSongs);
		
		txtSongs = new JTextField();
		txtSongs.setColumns(10);
		songsPanel.add(txtSongs);
		
		JPanel imagePanel = new JPanel();
		centerPanel.add(imagePanel);
		
		JButton btnImage = new JButton("Image");
		btnImage.addActionListener(this);
		btnImage.setActionCommand("btnImage");
		imagePanel.add(btnImage);
		
		lblImage = new JLabel("no image selected");
		imagePanel.add(lblImage);
		
		JPanel pricePanel = new JPanel();
		centerPanel.add(pricePanel);
		
		JLabel lblPrice = new JLabel("Price");
		pricePanel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		pricePanel.add(txtPrice);
		
		JPanel descriptionPanel = new JPanel();
		centerPanel.add(descriptionPanel);
		
		JLabel lblDescription = new JLabel("Description");
		descriptionPanel.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		descriptionPanel.add(txtDescription);
		
		JPanel genrePanel = new JPanel();
		centerPanel.add(genrePanel);
		
		JLabel lblGenre = new JLabel("Genre");
		genrePanel.add(lblGenre);
		
		txtGenre = new JTextField();
		txtGenre.setColumns(10);
		genrePanel.add(txtGenre);
		
		JPanel quantityPanel = new JPanel();
		centerPanel.add(quantityPanel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		quantityPanel.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		quantityPanel.add(txtQuantity);
		
		JPanel ownerPanel = new JPanel();
		centerPanel.add(ownerPanel);
		
		JLabel lblOwner = new JLabel("Owner");
		ownerPanel.add(lblOwner);
		
		txtOwner = new JTextField();
		ownerPanel.add(txtOwner);
		txtOwner.setColumns(10);
		
		rdbtnBand = new JRadioButton("Band");
		rdbtnBand.setActionCommand("rdbtnBand");
		rdbtnBand.addActionListener(this);
		rdbtnBand.setSelected(true);
		ownerPanel.add(rdbtnBand);
		
		rdbtnMusician = new JRadioButton("Musician");
		rdbtnMusician.setActionCommand("rdbtnMusician");
		rdbtnMusician.addActionListener(this);
		ownerPanel.add(rdbtnMusician);
		
		JPanel southPanel = new JPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("btnCancel");
		btnCancel.addActionListener(this);
		southPanel.add(btnCancel);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setActionCommand("btnProceed");
		btnProceed.addActionListener(this);
		southPanel.add(btnProceed);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public boolean isBand() {
		return rdbtnBand.isSelected();
	}
	
	public boolean isMusician() {
		return rdbtnMusician.isSelected();
	}
	
	public void setBand() {
		rdbtnMusician.setSelected(false);
		rdbtnBand.setSelected(true);	
		
		band = txtOwner.getText();
		musician = null;
	}
	
	public void setMusician() {
		rdbtnMusician.setSelected(true);
		rdbtnBand.setSelected(false);	
		
		band = null;
		musician = txtOwner.getText();
	}

	public String getTitle() {
		return txtTitle.getText();
	}
	
	public String getSongs() {
		return txtSongs.getText();
	}
	
	public String getImage() {
		return model.saveImage(lblImage.getText(),getTitle());
	}
	
	public void setImage(String image) {
		lblImage.setText(image);
	}
	
	public Double getPrice() {
		return Double.valueOf(txtPrice.getText());
	}
	
	public String getDescription() {
		return txtDescription.getText();
	}
	
	public String getGenre() {
		return txtGenre.getText();
	}
	
	public int getQuantity() {
		return Integer.valueOf(txtQuantity.getText());
	}
	
	public String getOwner() {
		return txtOwner.getText();
	}
	
	public String getMusician() {
		if(isMusician()) {
			musician = txtOwner.getText();
		}
		return musician;
	}
	
	public String getBand() {
		if(isBand()) {
			band = txtOwner.getText();
		}
		return band;
	}
	
	public void bandDialog() {
		chooseDialog = new BandDialog(this.getOwner());
		chooseDialog.setVisible(true);
	}
	
	public void musicianDialog() {
		chooseDialog = new MusicianDialog(this.getOwner());
		chooseDialog.setVisible(true);
	}
	
	public void removeDialog() {
		chooseDialog.setVisible(false);
		chooseDialog.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCancel".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cancel");
		} else if("btnProceed".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("proceed");
		} else if("rdbtnBand".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("band");
		} else if("rdbtnMusician".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("musician");
		} else if("btnImage".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("image");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
