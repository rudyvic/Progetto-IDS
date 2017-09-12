package Model;

import java.util.*;

public class ModelLogin extends Observable {
	
	DatabaseQuery db = DatabaseQuery.getInstance();
	
	private boolean client = false;
	private boolean admin = false;
	
	public boolean checkUserPass(String user, String pass) {
		// se è valido
		if(db.loginClient(user, pass)) {
			// E' loggato un cliente
			setClient();
			return true;
			
		} else if(db.loginAdmin(user, pass)) {
			// E' loggato un personale
			setAdmin();
			return true;
		}
		return false;
	}
	
	public boolean isClient(){
		return client;
	}
	
	public boolean isAdmin(){
		return admin;
	}
	
	public void setClient(){
		client = true;
	}
	
	public void setAdmin(){
		admin = true;
	}
}
