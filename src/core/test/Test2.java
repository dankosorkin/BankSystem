package core.test;

import core.Bank;

public class Test2 {
	public static void main(String[] args) {

		Bank bank = Bank.getInstance();

		System.out.println(bank.getClients().toString());

	}
}
