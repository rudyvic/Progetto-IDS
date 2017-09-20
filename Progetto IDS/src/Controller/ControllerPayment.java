package Controller;

import Model.Buy;
import Model.Catalog;
import Model.DatabaseQuery;
import Model.Disc;
import Model.ModelPayment;
import View.ViewPayment;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControllerPayment implements Observer {
	private ModelPayment model;
	private ViewPayment view;
	private ApplicationController controller = ApplicationController.getInstance();
	private DatabaseQuery db = DatabaseQuery.getInstance();

	public ControllerPayment() {
		this.model = new ModelPayment(controller.controllerTopbar.getUsername(),db.getCart(controller.controllerTopbar.getUsername()));
		this.view = new ViewPayment(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	private boolean checkCart() {
		for(Disc disc : model.getCart().getCatalog()) {
			if(db.getCatalog().checkDiscQuantity(disc, model.getCart().getQuantity(disc)) == false) {
				return false;
			}
		}
		return true;
	}
	
	private void buy() {
		ControllerCart c = ControllerCart.getInstance();
		Catalog temp = model.getCart();
		for(Disc disc : temp.getCatalog()) {
			Buy b = new Buy(model.getUsername(), model.getPrice(), view.getPaymentType(), "Corriere");
			db.editStoreQuantity(disc.getCode(), db.getCatalog().getQuantity(disc) - temp.getQuantity(disc));
			db.buyCD(b);
			db.boughtCD(b.getCode(), disc.getCode());
			c.remove(disc);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("cancel".equals((String)arg)) {
				controller.showCart();
			} else if("buy".equals((String)arg)) {
				if(checkCart()) {
					buy();
					JOptionPane.showMessageDialog(null, "Thanks for purchase.", "OK", JOptionPane.INFORMATION_MESSAGE, null);
					controller.controllerTopbar.emptyCart();
					controller.showHome();
				} else {
					JOptionPane.showMessageDialog(null, "Not all discs in your cart are available.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
					controller.showCart();
				}
			}
		}
	}

}
