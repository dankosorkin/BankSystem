package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import core.exceptions.BankLoadException;
import core.exceptions.BankStoreException;

/**
 * The class describes bank instance; its fields and methods. The class realized
 * singleton pattern in lazy initialization.
 */
public class Bank {

	public static Bank instance;
	private List<Client> clients;
	private double balance;
	private static double totalCommission;
	private File dataFile = new File("data/bank.data");

	{
		try {
			load();
		} catch (BankLoadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Private constructor initialize array of a clients
	 */
	private Bank() {
		if (clients == null)
			clients = new ArrayList<>();

		startAccountUpdater();
	}

	/**
	 * The method returns single instance of the Bank
	 * 
	 * @return Bank instance
	 */
	public static Bank getInstance() {
		// Lazy Initialization
		if (instance == null)
			instance = new Bank();
		return instance;
	}

	/**
	 * The method return bank balance. Bank balance includes balance of all bank
	 * clients on their main account and saving accounts
	 * 
	 * @return double balance
	 */
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double commission) {
		this.balance += commission;
	}

	/**
	 * The method add new client to the array of clients
	 * 
	 * @param Client client
	 */
	public void addClient(Client client) throws NullPointerException {
		if (client != null) {
			clients.add(client);

			Log log = new Log(System.currentTimeMillis(), client.getId(), "add client", client.getBalance());
			Logger.log(log);
		} else {
			throw new NullPointerException("The client was not added: no details were given");
		}
	}

	/**
	 * The method remove client from the array of clients
	 * 
	 * @param Client clientToRemove
	 */
	public boolean removeClient(Client clientToRemove) {
		int index = clients.indexOf(clientToRemove);

		if (index != -1) {
			Client client = clients.get(index);

			Log log = new Log(System.currentTimeMillis(), client.getId(), "remove client", client.getFortune());
			Logger.log(log);

			clients.remove(client);
			return true;
		} else {
			System.out.println("client was not found");
			return false;
		}
	}

	/**
	 * The method returns the total fortune of all bank clients. It is calculated by
	 * summing the total clients balance and the total accounts balance.
	 */
	public double getAllClientFortune() {
		double fortune = 0;
		for (Client client : clients) {
			if (client != null)
				fortune += client.getFortune();
		}
		return fortune;
	}

	/**
	 * The method returns array of a clients
	 * 
	 * @return Client[] clients
	 */
	public List<Client> getClients() {
		return this.clients;
	}

	public void viewLogs() {
		System.out.println("not supported yet");
	}

	public void startAccountUpdater() {
		AutoUpdater autoUpdater = new AutoUpdater(clients);
		Thread updater = new Thread(autoUpdater);
		updater.start();
	}

	/**
	 * Class method updates bank total commission
	 * 
	 * @param double commission
	 */
	public static void updateTotalCommission(double commission) {
		Bank.totalCommission += commission;
	}

	/***/
	public void printClientList() {
		for (Client client : clients) {
			System.out.println(client.toString());
		}
	}

	public void store() throws BankStoreException {

		try (ObjectOutputStream fos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)));) {

			if (!this.dataFile.exists())
				this.dataFile.createNewFile();

			fos.writeObject(clients);

			System.out.println("clients stored in: " + dataFile);

		} catch (IOException e) {
			throw new BankStoreException("bank store clients failed", e);
		}

	}

	public void load() throws BankLoadException {

		try (ObjectInputStream fis = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));) {

			clients = (List<Client>) fis.readObject();

		} catch (EOFException e) {
			System.out.println("no previous clients in file: " + dataFile);
		} catch (IOException | ClassNotFoundException e) {
			throw new BankLoadException("couldnt load stored clients", e);
		}

	}

}
