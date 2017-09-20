package View;

import Model.ModelSignin;
import Model.Person;

import java.awt.*;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewSignin extends Observable implements Observer, ActionListener {
	private ModelSignin model;
	private JPanel panel;
	
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAddress;
	private JTextField txtIdCode;
	private JTextField txtCity;
	private JTextField txtPhone;
	private JTextField txtCellphone;
	
	
	public ViewSignin(ModelSignin model) {
		this.model = model;
		model.addObserver(this);
		
		panel = new JPanel();

		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel usernamePanel = new JPanel();
		centerPanel.add(usernamePanel);
		
		JLabel lblUsername = new JLabel("Username");
		usernamePanel.add(lblUsername);
		
		txtUsername = new JTextField();
		usernamePanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		centerPanel.add(passwordPanel);
		
		JLabel lblPassword = new JLabel("Password (at least 7 chars)");
		passwordPanel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		passwordPanel.add(txtPassword);
		
		JPanel namePanel = new JPanel();
		centerPanel.add(namePanel);
		
		JLabel lblName = new JLabel("Name");
		namePanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		namePanel.add(txtName);
		
		JPanel surnamePanel = new JPanel();
		centerPanel.add(surnamePanel);
		
		JLabel lblSurname = new JLabel("Surname");
		surnamePanel.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		surnamePanel.add(txtSurname);
		
		JPanel idCodePanel = new JPanel();
		centerPanel.add(idCodePanel);
		
		JLabel lblIdCode = new JLabel("ID Code");
		idCodePanel.add(lblIdCode);
		
		txtIdCode = new JTextField();
		txtIdCode.setColumns(10);
		idCodePanel.add(txtIdCode);
		
		JPanel addressPanel = new JPanel();
		centerPanel.add(addressPanel);
		
		JLabel lblAddress = new JLabel("Address");
		addressPanel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		addressPanel.add(txtAddress);
		
		
		JPanel CityPanel = new JPanel();
		centerPanel.add(CityPanel);
		
		JLabel lblCity = new JLabel("City");
		CityPanel.add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		CityPanel.add(txtCity);
		
		JPanel phonePanel = new JPanel();
		centerPanel.add(phonePanel);
		
		JLabel lblPhone = new JLabel("Phone");
		phonePanel.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		phonePanel.add(txtPhone);
		
		JPanel cellphonePanel = new JPanel();
		centerPanel.add(cellphonePanel);
		
		JLabel lblCellphone = new JLabel("Cellphone (optional)");
		cellphonePanel.add(lblCellphone);
		
		txtCellphone = new JTextField();
		cellphonePanel.add(txtCellphone);
		txtCellphone.setColumns(10);
		
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
	
	public Person getPerson() {
		if(txtIdCode.getText().trim().equals("") || txtUsername.getText().trim().equals("") || txtPassword.getText().trim().equals("") || txtName.getText().trim().equals("") || txtSurname.getText().trim().equals("") || txtAddress.getText().trim().equals("") || txtCity.getText().trim().equals("") || txtPhone.getText().trim().equals("")) {
			return null;
		} else {
			return new Person(txtIdCode.getText().trim(), txtUsername.getText().trim(), txtPassword.getText().trim(), txtName.getText().trim(), txtSurname.getText().trim(), txtAddress.getText().trim(), txtCity.getText().trim(), txtPhone.getText().trim(), txtCellphone.getText().trim());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("btnCancel".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("cancel");
		} else if("btnProceed".equals(e.getActionCommand())) {
			this.setChanged();
			this.notifyObservers("proceed");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
