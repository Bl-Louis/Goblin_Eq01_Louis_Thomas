package Donnees;

import java.io.File;
import java.util.List;

import methodes.FloydWarshall;
import methodes.LectureCsv;

public class Data {
	private List<Integer> capacity_facility;
	private List<Integer> fixed_cost_facility;
	private List<Integer> demand_customer;
	private int[][] cost_matrix;
	private int num_facility_locations;
	private int num_customers;
	
	public Data(List<Integer> capacity_facility, List<Integer> fixed_cost_facility, List<Integer> demand_customer, int[][] cost_matrix,
			int num_facility_locations, int num_customers) {
		super();
		this.capacity_facility = capacity_facility;
		this.fixed_cost_facility = fixed_cost_facility;
		this.demand_customer = demand_customer;
		this.cost_matrix = cost_matrix;
		this.num_facility_locations = num_facility_locations;
		this.num_customers = num_customers;
	}
	
	
	

}
