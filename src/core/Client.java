package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.exceptions.WithdrawException;

public abstract class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double balance;
	private List<Account> accounts;
	protected double commissionRate;
	protected double interestRate;

	public Client(int id, String name, double balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
		if (accounts == null) {
			accounts = new ArrayList<>();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public boolean addAccount(Account account) throws NullPointerException {
		if (account != null) {
			accounts.add(account);

			Log log = new Log(java.lang.System.currentTimeMillis(), this.id, "add account", account.getBalance());
			Logger.log(log);

			return true;
		} else {
			throw new NullPointerException("The account was not added: no details were given");
		}
	}

	public Account getAccount(int id) {
		for (Account account : accounts) {
			if (account.getId() == id)
				return account;
		}
		return null;
	}

	public void removeAccount(Account accountToRemove) {
		for (Account account : accounts) {
			if (account.equals(accountToRemove)) {
				Log log = new Log(java.lang.System.currentTimeMillis(), account.getId(), "close aacount",
						accountToRemove.getBalance());
				Logger.log(log);
				this.balance += accountToRemove.getBalance();
				accounts.remove(accountToRemove);
			}
		}
		System.out.println("account " + accountToRemove.getId() + " not found");
	}

	public void deposit(double ammount) {
		double commission = ammount / 100 * commissionRate;
		this.balance += (ammount - commission);
	}

	public void withdraw(double ammount) throws WithdrawException {
		double commission = ammount / 100 * commissionRate;

		try {
			if (this.balance >= (ammount + commission)) {
				this.balance -= (ammount + commission);

				// update bank total commission
				Bank.updateTotalCommission(commission);
			}
		} catch (Exception e) {
			throw new WithdrawException("there is not enough balance", this.id, this.balance, ammount);
		}
	}

	public void autoUpdateAccounts() {
		System.out.println(">>> client: update");
		for (Account account : accounts) {
			if (account != null) {
				double interest = account.getBalance() * this.interestRate;
				account.setBalance(account.getBalance() + interest);
				Log log = new Log(java.lang.System.currentTimeMillis(), account.getId(), "update balance",
						account.getBalance());
				Logger.log(log);
			}
		}
	}

	public double getFortune() {
		double fortune = this.balance;
		for (Account account : accounts) {
			if (account != null)
				fortune += account.getBalance();
		}
		return fortune;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 3;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
