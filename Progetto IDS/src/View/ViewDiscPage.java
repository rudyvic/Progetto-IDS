package View;

import Model.Disc;
import Model.ModelDiscPage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.*;

public class ViewDiscPage extends Observable implements Observer, ActionListener {
	private ModelDiscPage model;
	private JPanel panel;
	
	private JFrame frame;
	
	private ViewDiscPage(ModelDiscPage model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		frame = new JFrame(model.getDisc().getTitle());
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 529, 313);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(panel);
		
		JPanel centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel(model.getDisc().getTitle());
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblTitle.setBounds(6, 6, 332, 29);
		centerPanel.add(lblTitle);
		
		JLabel ltlSongs = new JLabel("<html>Songs:<br>" + model.getDisc().getSongs());
		ltlSongs.setBounds(277, 77, 204, 122);
		centerPanel.add(ltlSongs);
		
		JLabel lblImage = new JLabel();
		//lblCopertina.setIcon(new ImageIcon(FramePaginaProdotto.class.getResource("/provaSwing/" + disco.getFotoCopertina())));
		lblImage.setBounds(16, 36, 230, 230);
		centerPanel.add(lblImage);
		
		JLabel lblDate = new JLabel("Date: " + model.getDisc().getDate());
		lblDate.setBounds(277, 250, 204, 16);
		centerPanel.add(lblDate);
		
		JLabel lblGenre = new JLabel("Genre: " + model.getDisc().getGenre());
		lblGenre.setBounds(277, 194, 204, 16);
		centerPanel.add(lblGenre);
		
		JLabel lblOwner = new JLabel("Owner: " + model.getDisc().getOwner());
		lblOwner.setBounds(277, 36, 204, 16);
		centerPanel.add(lblOwner);
		
		JLabel lblDescription = new JLabel("<html>Description: " + model.getDisc().getDescription());
		lblDescription.setBounds(277, 63, 204, 48);
		centerPanel.add(lblDescription);
		
		JLabel lblPrice = new JLabel(Double.toString(model.getDisc().getPrice()) + "€");
		lblPrice.setBounds(391, 227, 122, 48);
		centerPanel.add(lblPrice);
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setForeground(Color.BLUE);
		lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		frame.setVisible(true);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}

