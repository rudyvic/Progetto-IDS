package Controller;

import Model.DatabaseQuery;
import Model.Disc;
import Model.ModelAdminAddNewDisc;
import View.ViewAdminAddNewDisc;

import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ControllerAdminAddNewDisc implements Observer {
	private ModelAdminAddNewDisc model;
	private ViewAdminAddNewDisc view;
	private ApplicationController controller = ApplicationController.getInstance();

	public ControllerAdminAddNewDisc() {
		this.model = new ModelAdminAddNewDisc();
		this.view = new ViewAdminAddNewDisc(model);
		view.addObserver(this);
	}
	
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	private boolean checkOwner() {
		if((view.isBand() && view.getBand().trim().equals("")) || (view.isMusician() && view.getMusician().trim().equals(""))) {
			JOptionPane.showMessageDialog(null, "<html>You must enter the name of a band or a musician.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		
		DatabaseQuery db = DatabaseQuery.getInstance();
		if(view.isMusician()) {
			if(db.existsMusician(view.getOwner()) == 1) {
				view.setMusician();
				return true;
			} else {
				view.musicianDialog();
				return false;
			}
		} else {
			if(db.existsBand(view.getOwner()) == 1) {
				view.setBand();
				return true;
			}  else {
				view.bandDialog();
				return false;
			}
		}
	}
	
	private boolean insertDisc() {
		Disc disc = new Disc();
		disc.setTitle(view.getTitle());
		disc.setSongs(view.getSongs());
		disc.setImage(view.getImage());
		disc.setPrice(view.getPrice());
		disc.setMusician(view.getMusician());
		disc.setBand(view.getBand());
		disc.setDescription(view.getDescription());
		disc.setGenre(view.getGenre());
		
		if(disc.getTitle().equals("") || disc.getSongs().equals("") || disc.getImage().equals("") || disc.getGenre().equals("")) {
			return false;
		}
		
		model.setQuantity(view.getQuantity());
		model.setDisc(disc);
		if(model.insertDisc()) {
			JOptionPane.showMessageDialog(null, "This disc is now in the store.", "OK", JOptionPane.INFORMATION_MESSAGE, null);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Impossible to insert the disc in the store.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if("cancel".equals((String)arg)){
				controller.showAdminHome();
			} else if("insert disc".equals((String)arg)){
				if(insertDisc()) {
					controller.showAdminHome();
				}
			} else if("band".equals((String)arg)){
				view.setBand();
			} else if("musician".equals((String)arg)){
				view.setMusician();
			} else if("image".equals((String)arg)) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select an image");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("png","png");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					view.setImage(jfc.getSelectedFile().getPath());
				}
			} else if("proceed".equals((String)arg)){
				if(checkOwner()) {
					try {
						if(insertDisc()) {
							controller.showAdminHome();
						} else {
							JOptionPane.showMessageDialog(null, "<html>Impossible to proceed.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
						}
					} catch(Exception e) {
						JOptionPane.showMessageDialog(null, "<html>Impossible to proceed.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			}
		}
	}

}
