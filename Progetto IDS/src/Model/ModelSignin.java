package Model;

import java.util.Observable;


public class ModelSignin extends Observable {
	private Person person;
	
	public ModelSignin() {

	}

	public void setPerson(Person p) {
		this.person = p;
	}
	
	public Person getPerson() {
		return person;
	}
}
