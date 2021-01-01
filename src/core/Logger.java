package core;

import java.util.List;

public class Logger {

	private String driverName;

	public Logger(String driverName) {
		this.driverName = driverName;
	}

	public static void log(Log log) {
		System.out.println(log.toString());
	}

	public List<Log> getLogs() {
		return null;
	}

}
