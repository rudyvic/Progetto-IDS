package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.DatabaseQuery;

import java.awt.GridLayout;

public class BandDialog extends JDialog implements ActionListener {

	private String band;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtGenre;
	private JTextField txtYear;

	public BandDialog(String band) {
		this.band = band;
		
		this.setBounds(100, 100, 450, 300);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setTitle("Insert the new band");
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel namePanel = new JPanel();
			contentPanel.add(namePanel);
			{
				JLabel lblName = new JLabel(band);
				namePanel.add(lblName);
			}
		}
		{
			JPanel genrePanel = new JPanel();
			contentPanel.add(genrePanel);
			{
				JLabel lblGenre = new JLabel("Genre");
				genrePanel.add(lblGenre);
			}
			{
				txtGenre = new JTextField();
				genrePanel.add(txtGenre);
				txtGenre.setColumns(10);
			}
		}
		{
			JPanel yearPanel = new JPanel();
			contentPanel.add(yearPanel);
			{
				JLabel lblYear = new JLabel("Year");
				yearPanel.add(lblYear);
			}
			{
				txtYear = new JTextField();
				yearPanel.add(txtYear);
				txtYear.setColumns(10);
			}
		}
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				JButton btnProceed = new JButton("Proceed");
				btnProceed.addActionListener(this);
				btnProceed.setActionCommand("btnProceed");
				buttonPanel.add(btnProceed);
				this.getRootPane().setDefaultButton(btnProceed);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(this);
				btnCancel.setActionCommand("btnCancel");
				buttonPanel.add(btnCancel);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCancel".equals(e.getActionCommand())) {
			this.setVisible(false);
			this.dispose();
		} else if("btnProceed".equals(e.getActionCommand())) {
			DatabaseQuery db = DatabaseQuery.getInstance();
			db.addBand(band, txtGenre.getText(), Integer.valueOf(txtYear.getText()));
			this.setVisible(false);
			this.dispose();
		}
	}

}
