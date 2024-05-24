package Donnees;

public class Client {
	private String nom;
	private String mail;
	private int id_site;
	
	
	public Client(String nom, String mail, int id_site) {
		super();
		this.nom = nom;
		this.mail = mail;
		this.id_site = id_site;
	}

	public String toString() {
		return nom+" "+mail+" "+id_site;
	}

	public String getNom() {
		return nom;
	}

	public String getMail() {
		return mail;
	}

	public int getId_site() {
		return id_site;
	}
	
	
	
	
	

}
