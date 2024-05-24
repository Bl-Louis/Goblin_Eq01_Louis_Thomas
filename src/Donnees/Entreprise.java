package Donnees;

public class Entreprise {
	private String client;
	private String entrepot;
	
	public Entreprise(String client, String entrepot) {
		super();
		this.client = client;
		this.entrepot = entrepot;
	}

	public String toString() {
		return client+" "+entrepot;
	}

	public String getClient() {
		return client;
	}

	public String getEntrepot() {
		return entrepot;
	}
	
	
	
	

	
}
