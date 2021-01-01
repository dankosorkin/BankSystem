package core;

public class ClientPlatinum extends Client {

	public ClientPlatinum(int id, String name, double balance) {
		super(id, name, balance);
		commissionRate = 1;
		interestRate = 0.5;
	}

	@Override
	public String toString() {
		return "ClientPlatinum [id = " + getId() + "]";
	}

}
