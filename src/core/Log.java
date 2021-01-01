package core;

import java.sql.Date;

public class Log {

	private long timestamp;
	private int id;
	private String description;
	private double amount;

	public Log(long timestamp, int id, String description, double amount) {
		this.timestamp = timestamp;
		this.id = id;
		this.description = description;
		this.amount = amount;
	}

	@Override
	public String toString() {
		Date date = new Date(timestamp);
		return "Log [date=" + date + ", id=" + id + ", description=" + description + ", amount=" + amount + "]";
	}

}
