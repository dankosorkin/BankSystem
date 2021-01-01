package core;

import java.util.List;

public class AutoUpdater implements Runnable {

	private List<Client> clients;

	public AutoUpdater(List<Client> clients) {
		super();
		this.clients = clients;
	}

	@Override
	public void run() {

		while (true) {

			for (Client client : clients) {

				if (client != null) {
					client.autoUpdateAccounts();
				}
			}

			try {
				Thread.sleep(24 * 60 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
