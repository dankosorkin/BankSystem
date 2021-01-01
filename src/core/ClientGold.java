package core;

public class ClientGold extends Client {

	public ClientGold(int id, String name, double balance) {
		super(id, name, balance);
		commissionRate = 2;
		interestRate = 0.3;
	}

	@Override
	public String toString() {
		return "ClientGold [id = " + getId() + "]";
	}

}
