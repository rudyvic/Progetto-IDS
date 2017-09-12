package Model;

public class Musician {
	
	private String name;
	private String genre;
	private int year;
	private String instruments;
	private String band;
	
	public Musician(String name, String genre, int year, String instruments, String band){
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.instruments = instruments;
		this.band = band;
	}
	
	public String getName(){
		return name;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public int getYear(){
		return year;
	}
	
	public String getInstruments(){
		return instruments;
	}
	
	public String getBand(){
		return band;
	}
}
