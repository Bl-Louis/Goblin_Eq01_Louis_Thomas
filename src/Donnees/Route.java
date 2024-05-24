package Donnees;

public class Route {
	private int origine;
	private int destination;
	
	public Route(int origine, int destination) {
		super();
		this.origine = origine;
		this.destination = destination;
	}

	public String toString() {
		return origine +" "+ destination;
	}

	public int getOrigine() {
		return origine;
	}

	public int getDestination() {
		return destination;
	}
	
	
	
	

}
