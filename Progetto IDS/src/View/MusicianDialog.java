package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.DatabaseQuery;
import Model.Musician;

import java.awt.GridLayout;

public class MusicianDialog extends JDialog implements ActionListener {

	private String musician;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtGenre;
	private JTextField txtYear;
	private JTextField txtInstruments;
	private JTextField txtBand;

	public MusicianDialog(String musician) {
		this.musician = musician;
		
		this.setBounds(100, 100, 450, 300);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setTitle("Inserisci un nuovo musicista");
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel namePane = new JPanel();
			contentPanel.add(namePane);
			{
				JLabel lblName = new JLabel(musician);
				namePane.add(lblName);
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
			JPanel instrumentsPanel = new JPanel();
			contentPanel.add(instrumentsPanel);
			{
				JLabel lblInstruments = new JLabel("Instruments");
				instrumentsPanel.add(lblInstruments);
			}
			{
				txtInstruments = new JTextField();
				instrumentsPanel.add(txtInstruments);
				txtInstruments.setColumns(10);
			}
		}
		{
			JPanel bandPanel = new JPanel();
			contentPanel.add(bandPanel);
			{
				JLabel lblBand = new JLabel("Band");
				bandPanel.add(lblBand);
			}
			{
				txtBand = new JTextField();
				bandPanel.add(txtBand);
				txtBand.setColumns(10);
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
			
			if(db.existsBand(txtBand.getText())==1) {
				Musician m = new Musician(musician, txtGenre.getText(), Integer.valueOf(txtYear.getText()), txtInstruments.getText(), txtBand.getText());
				db.addMusician(m);
				this.setVisible(false);
				this.dispose();
			} else {
				BandDialog dialog = new BandDialog(txtBand.getText());
				dialog.setVisible(true);
			}
		}
	}

}
