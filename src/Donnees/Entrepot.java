package Donnees;

public class Entrepot {
	private int id_site;
	private int id_entrepot;
	private int couts_fixes;
	private int stock;
	public Entrepot(int id_site, int id_entrepot, int couts_fixes, int stock) {
		super();
		this.id_site = id_site;
		this.id_entrepot = id_entrepot;
		this.couts_fixes = couts_fixes;
		this.stock = stock;
	}
	
	public String toString() {
		return id_site+" "+id_entrepot+" "+ couts_fixes+" "+ stock;
	}

	public int getId_site() {
		return id_site;
	}

	public int getId_entrepot() {
		return id_entrepot;
	}

	public int getCouts_fixes() {
		return couts_fixes;
	}

	public int getStock() {
		return stock;
	}
	
	

}
