package core.exceptions;

public class WithdrawException extends BankException {

	private static final long serialVersionUID = 1L;
	private int id;
	private double currentBalance;
	private double withdrawAmmount;

	public WithdrawException(String message, int id, double currentBalance, double withdrawAmmount) {
		super(message);
		this.id = id;
		this.currentBalance = currentBalance;
		this.withdrawAmmount = withdrawAmmount;
	}

	public int getId() {
		return id;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public double getWithdrawAmmount() {
		return withdrawAmmount;
	}

}
