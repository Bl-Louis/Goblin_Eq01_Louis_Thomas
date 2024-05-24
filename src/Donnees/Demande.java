package Donnees;

public class Demande {
	private int Id_client;
	private int quantite;
	
	public Demande(int id_client, int quantite) {
		super();
		Id_client = id_client;
		this.quantite = quantite;
	}

	public String toString() {
		return Id_client+" "+quantite;
	}

	public int getId_client() {
		return Id_client;
	}

	public int getQuantite() {
		return quantite;
	}
	
	
	
	
	

}
