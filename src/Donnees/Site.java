package Donnees;

import java.util.ArrayList;
import java.util.List;

public class Site {
	private int id_site;
	private int x;
	private int y;
	private List<Site> succs;
	private List<Site> neighbors; // Liste des voisins
    private List<Route> routes; // Liste des routes connectées à ce site

	

	
	public Site(int id_site, int x, int y) {
		super();
		this.id_site = id_site;
		this.x = x;
		this.y = y;
		this.succs=new ArrayList<>();
		this.neighbors = new ArrayList<>();
        this.routes = new ArrayList<>();

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

	public List<Site> getSuccs() {
		return succs;
	}

	public void setSuccs(List<Site> succs) {
		this.succs = succs;
	}
	
	public void addSucc(Site s) {
		succs.add(s);
	}
	
	// Méthode pour ajouter un voisin
    public void addNeighbor(Site neighbor) {
        neighbors.add(neighbor);
    }

    // Méthode pour ajouter une route connectée à ce site
    public void addRoute(Route route) {
        routes.add(route);
    }

    // Méthode pour obtenir la liste des voisins
    public List<Site> getNeighbors() {
        return neighbors;
    }

    // Méthode pour obtenir la liste des routes connectées à ce site
    public List<Route> getRoutes() {
        return routes;
    }

}
