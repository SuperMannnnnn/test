package com.dabao.xml;

public class Book {
	private String cat;
	private String title;
	private double price;
	private String author;
	public Book() {
		super();
	}
	public Book(String cat, String title, double price, String author) {
		super();
		this.cat = cat;
		this.title = title;
		this.price = price;
		this.author = author;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}
