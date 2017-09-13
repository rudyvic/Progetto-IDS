package Model;

import java.util.*;

public class ModelLogin extends Observable {
	
	private DatabaseQuery db = DatabaseQuery.getInstance();
	
	private boolean client;
	private boolean admin;
	
	public ModelLogin(){
		client = false;
		admin = false;
	}
	
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
		admin = false;
	}
	
	public void setAdmin(){
		admin = true;
		client = false;
	}
}
