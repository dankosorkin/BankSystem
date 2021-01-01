package core.test;

import core.Account;
import core.Bank;
import core.Client;
import core.ClientRegular;

public class Test1 {
	public static void main(String[] args) {

		Bank bank = Bank.getInstance();

		Account a1 = new Account(101, 10);
		Account a2 = new Account(102, 15);
		Account a3 = new Account(103, 12.1);

		System.out.println(a1.toString());
		System.out.println(a2.toString());
		System.out.println(a3.toString());

		Client c1 = new ClientRegular(111, "Adam", 100);
		Client c2 = new ClientRegular(112, "Moshe", 110);
		Client c3 = new ClientRegular(113, "David", 105);

		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());

		bank.addClient(c1);
		bank.addClient(c2);
		bank.addClient(c3);

		c1.addAccount(a1);
		c2.addAccount(a2);
		c3.addAccount(a3);

		System.out.println(bank.getClients().toString());

	}
}
