package Model;

import java.sql.Timestamp;

public class Buy {
	
	private int code;
	private String client;
	private double price;
	private Timestamp date;
	private String payment;
	private String deliver;
	
	
	public Buy(String client, double price, String payment, String deliver) {
		this.client = client;
		this.price = price;
		this.deliver = deliver;
		this.payment = payment;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getClient(){
		return client;
	}
	
	public String getDeliver(){
		return deliver;
	}
	
	public String getPayment(){
		return payment;
	}
	
	public Timestamp getDataAcquisto(){
		return date;
	}
	
	public double getPrice(){
		return price;
	}
}
