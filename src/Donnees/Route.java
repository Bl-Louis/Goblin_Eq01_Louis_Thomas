package Donnees;

public class Route {
	private int origine;
	private int destination;
	private int distance;
	
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	
	
	
	

}
