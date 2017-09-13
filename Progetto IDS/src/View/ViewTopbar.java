package View;

import Model.ModelTopbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ViewHome extends Observable implements Observer, ActionListener {
	private ModelHome model;
	private JPanel panel;
	
	private JTextField txtFind;
	
	public ViewHome(ModelHome model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();
		
		panel.setBackground(Color.GREEN);
		panel.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_4, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.GREEN);
		panel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelFind = new JPanel();
		panelCenter.add(panelFind);
		
		txtFind = new JTextField();
		panelFind.add(txtFind);
		txtFind.setToolTipText("");
		txtFind.setColumns(40);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(this);
		btnFind.setActionCommand("btnFind");
		panelFind.add(btnFind);
		
		JPanel panelCatalog = new JPanel();
		panelCenter.add(panelCatalog);
		
		JButton btnCatalog = new JButton("Mostra tutto il catalogo >>");
		btnCatalog.addActionListener(this);
		btnCatalog.setActionCommand("btnCatalog");
		btnCatalog.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnCatalog.setForeground(new Color(0, 0, 255));
		btnCatalog.setBorder(BorderFactory.createEmptyBorder());
		panelCatalog.add(btnCatalog);
		
		/*
		if(controller.isLogin()) {
			JPanel panelSouth = new PanelSuggeriti(controller);
			panel.add(panelSouth, BorderLayout.SOUTH);
		}*/
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getFindText() {
		return txtFind.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCatalog".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("catalog");
		} else if("btnFind".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("catalogWith");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
