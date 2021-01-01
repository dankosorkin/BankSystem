package core;

public class ClientRegular extends Client {

	public ClientRegular(int id, String name, double balance) {
		super(id, name, balance);
		commissionRate = 3;
		interestRate = 0.1;
	}

	@Override
	public String toString() {
		return "ClientRegular [id = " + getId() + "]";
	}

}
