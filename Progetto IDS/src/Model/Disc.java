package Model;


import java.sql.Date;

public class Disc implements Comparable<Disc> {
	
	private int code;
	private String title;
	private String songs;
	private String image;
	private double price;
	private Date date;
	private String musician;
	private String band;
	private String description;
	private String genre;
	
	public Disc() {
		
	}

	public Disc(int code, String title, String songs, String image, double price, Date date, String musician, String band, String description, String genre)
	{
		this.code = code;
		this.title = title;
		this.songs = songs;
		this.image = image;
		this.price = price;
		this.date = date;
		this.musician = musician;
		this.band = band;
		this.description = description;
		this.genre = genre;
	}	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitolare() {
		if(musician==null) {
			return band;
		}
		return musician;
	}
	
	public String getMusician() {
		return musician;
	}

	public void setMusician(String musician) {
		this.musician = musician;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int compareTo(Disc o) {
		Disc d = (Disc)o;
		return (getCode() - d.getCode());
	}
}
