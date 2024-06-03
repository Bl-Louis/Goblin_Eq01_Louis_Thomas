package methodes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Donnees.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcritureFichierPulpJson {
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

        // Spécifier le chemin complet du fichier de sortie
        String outputFilePath = "/Users/louis/Desktop/GOBELIN/fichier.json";

        // Écrire le JSON dans un fichier
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            writer.write(jsonString);
        }

        //System.out.println("Fichier JSON généré avec succès !");
    }

    public static void main(String[] args) {
        try {
            
            
            List<Integer> capacity_facility = MethodesBDD.RequeteStock();
            List<Integer> fixed_cost_facility = MethodesBDD.RequeteCout_fixe();
            List<Integer> demand_customer = LectureBordereauCommandeTxt.lectureCommandeQuantite();
            int[][] cost_matrix = MethodesBDD.extractDistanceMatrixForIDs(); // Attention, c'est transformé en Index et ce n'est pas les ID
            int num_facility_locations = MethodesBDD.RequeteEntrepot();
            int num_customers = demand_customer.size();
            
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
