package Model;
public class Person {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String city;
	private String phone;
	private String cellphone;
	private String address;
	private String idCode;
	
	public Person(String idCode, String username, String password, String name, String surname, String address, String city, String phone, String cellphone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.cellphone = cellphone;
		this.phone = phone;
		this.idCode = idCode;
		this.address = address;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}

	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}

	public String getCity(){
		return city;
	}

	public String getPhone(){
		return phone;
	}

	public String getCellphone(){
		return cellphone;
	}
	
	public String getIdCode(){
		return idCode;
	}
	
	public String getAddress(){
		return address;
	}
}