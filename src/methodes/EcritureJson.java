package methodes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Donnees.Data;
import Donnees.Route;
import Donnees.Site;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureJson {
    public static void EcrireJson(double[] capacity_facility,
    		double[] fixed_cost_facility,
    		double[] demand_customer,
    		int[][] cost_matrix,
			int num_facility_locations, 
			int num_customers) throws IOException {

        // Créer un objet de la classe Data pour contenir les données
    	Data data = new Data(capacity_facility,
    			fixed_cost_facility,
    			demand_customer,
    			cost_matrix,
    			num_facility_locations, 
    			num_customers);

        // Configurer Gson pour une meilleure lisibilité du JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Sérialiser l'objet Data en JSON
        String jsonString = gson.toJson(data);

        // Écrire le JSON dans un fichier
        try (FileWriter writer = new FileWriter("fichier2.json")) {
            writer.write(jsonString);
        }

        System.out.println("Fichier JSON généré avec succès !");
    }

    public static void main(String[] args) {
        try {
        	double[] capacity_facility = {15.0, 17.0, 12.0};
        	double[] fixed_cost_facility = {10.0, 9.0, 8.0};
        	double[] demand_customer = {5.0, 5.0, 5.0, 5.0, 5.0};
        	int[][] cost_matrix = FloydWarshall.floydWarshall(LectureCsv.lectureSite("Jeux_de_donnees" + File.separator + "grand", "init-sites-500-Carre.csv"), LectureCsv.lectureRoute("Jeux_de_donnees" + File.separator + "grand", "init-routes-500-750-Carre.csv"));
        	int num_facility_locations = 3;
        	int num_customers = 5;
        	
            EcrireJson(capacity_facility,
        			fixed_cost_facility,
        			demand_customer,
        			cost_matrix,
        			num_facility_locations, 
        			num_customers);
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier a levé l'exception " + e);
        }
    }
}

