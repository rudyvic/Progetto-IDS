package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Observable;


public class ModelAdminAddNewDisc extends Observable {
	private Disc disc;
	private int quantity = 0;
	
	public ModelAdminAddNewDisc() {

	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	
	public Disc getDisc() {
		return disc;
	}
	
	public boolean insertDisc() {
		DatabaseQuery db = DatabaseQuery.getInstance();
		if(db.addDisc(disc, quantity)==1) {
			return true;
		} else {
			return false;
		}
	}
	
	public String saveImage(String image) {
		String path = "../provaSwing/images/" + Math.round(Math.random()*100000) + disc.getTitle() + ".png";
		try {
			URI uri = new URI("file:" + image);
			URL url = uri.toURL();
			InputStream is = url.openStream();
			File f = new File(path);
			f.createNewFile();
			OutputStream os = new FileOutputStream(f);
	
			byte[] b = new byte[2048];
			int length;
	
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
	
			is.close();
			os.close();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		return path;
	}
}
