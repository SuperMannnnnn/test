package entity;

/**
 * ÃèÊöÒ»¸öº½°à
 */
public class Flight {
	private String number;
	private String from;
	private String to;
	private double price;
	private String date;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String number, String from, String to, double price,
			String date) {
		super();
		this.number = number;
		this.from = from;
		this.to = to;
		this.price = price;
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
