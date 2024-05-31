package methodes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Donnees.BasesCSV;
import Donnees.Data;
import Donnees.Route;
import Donnees.Site;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureJson {
    public static void EcrireJson(List<Integer> capacity_facility,
    		List<Integer> fixed_cost_facility,
    		List<Integer> demand_customer,
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
        	List<Integer> capacity_facility = BasesCSV.RequeteStock();
            List<Integer> fixed_cost_facility = BasesCSV.RequeteCout_fixe();
            List<Integer> demand_customer = LectureTxt.lectureCommandeStock("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
        	int[][] cost_matrix = FloydWarshall.floydWarshall(LectureCsv.lectureSite("Jeux_de_donnees" + File.separator + "petit", "init-sites-30-Carre.csv"), 
        													  LectureCsv.lectureRoute("Jeux_de_donnees" + File.separator + "petit", "init-routes-30-45-Carre.csv"));
            int num_facility_locations = BasesCSV.RequeteEntrepot();
            int num_customers = LectureTxt.lectureCommandeClient("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
        	
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

