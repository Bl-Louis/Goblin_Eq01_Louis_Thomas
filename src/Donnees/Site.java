package Donnees;

public class Site {
	private int id_site;
	private int x;
	private int y;
	
	public Site(int id_site, int x, int y) {
		super();
		this.id_site = id_site;
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return id_site+" "+x+" "+y;
	}
	
	public int getId_site() {
		return id_site;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	

}
