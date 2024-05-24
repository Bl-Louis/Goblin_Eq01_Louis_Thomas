package methodes;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class EcritureJson {
    public static String EcrireJson() throws IOException {
        // Créer un objet de la classe Data pour contenir les données
        Data data = new Data();

        // Sérialiser l'objet Data en JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(data);

        // Écrire le JSON dans un fichier
        try (FileWriter writer = new FileWriter("fichier.json")) {
            writer.write(jsonString);
        }

        // Retourner le contenu JSON sous forme de chaîne
        return jsonString;
    }
    
    public static void main(String[] args) {
        try {
            String json = EcrireJson();
            System.out.println(json);
        } catch (IOException e) {
			System.out.println("Une operation sur le fichier a leve l’exception "+e) ;
        }
    }
}

class Data {
    double[] capacity_facility = {15.0, 17.0, 12.0};
    double[] fixed_cost_facility = {10.0, 9.0, 8.0};
    double[] demand_customer = {5.0, 5.0, 5.0, 5.0, 5.0};
    double[][] cost_matrix = {
        {2.0, 1.2, 3.0, 3.3, 4.0},
        {3.0, 3.5, 6.0, 2.0, 2.5},
        {1.3, 2.3, 3.2, 5.0, 3.0}
    };
    int num_facility_locations = 3;
    int num_customers = 5;
}