package methodes;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EcritureBordereauLivraisonTxt {
	private static String path = "/Users/louis/Desktop/GOBELIN/";
	private static String jsonFilePath = path + "solutionSolver.json";  // Chemin vers le fichier JSON d'entrée
	private static String outputFilePath = path + "output.txt";  // Chemin vers le fichier de sortie

	public static List<Integer> convertDoubleToInt(List<Double> l) {
		List<Integer> res = new ArrayList<>();
		for (Double d : l) {
			res.add(d.intValue());
		}
		return res;
	}

	public static List<Integer> generationBordereau() {
		List<Integer> entrepotDispo = LectureBordereauCommandeTxt.lectureCommandeEntrepot();
		List<String> ClientALivrer = LectureBordereauCommandeTxt.lectureCommandeClient();

		List<Integer> entrepotOuvert = new ArrayList<>();
		List<Integer> entrepotClient = new ArrayList<>();
		List<String> ClientLivre = new ArrayList<>();

		Gson gson = new Gson(); // Initialise l'objet Gson pour la lecture du fichier JSON
		try (FileReader reader = new FileReader(jsonFilePath); // Crée un FileReader pour lire le fichier JSON
		     FileWriter writer = new FileWriter(outputFilePath)) { // Crée un FileWriter pour écrire dans le fichier de sortie

			Type mapType = Map.class; // Type de la Map à partir du JSON
			Map<String, Object> data = gson.fromJson(reader, mapType); // Lit les données JSON et les stock dans une Map

			// Créer une chaîne de caractères pour stocker la lecture du json -> On appelle read les données de lecture
			StringBuilder read = new StringBuilder();
			//read.append("Status: ").append(data.get("status")).append("\n"); // Ajoute le statut à la lecture
			//read.append("Objective: ").append(data.get("objective")).append("\n\n"); // Ajoute l'objectif à la lecture

			List<Double> openFacilities = (List<Double>) data.get("openfacilities"); // Récupère la liste des installations ouvertes
			List<Double> deliveriesForCustomer = (List<Double>) data.get("deliveriesfor_customer"); // Récupère la liste des livraisons pour les clients

			List<Integer> openFacilitiesInt = convertDoubleToInt(openFacilities);
			List<Integer> deliveriesForCustomerInt = convertDoubleToInt(deliveriesForCustomer);

			for (int i = 0; i < openFacilitiesInt.size(); i++) {
				entrepotOuvert.add(entrepotDispo.get(openFacilitiesInt.get(i) - 1));
			}

			for (int i = 0; i < deliveriesForCustomerInt.size(); i++) {
				entrepotClient.add(entrepotDispo.get(deliveriesForCustomerInt.get(i) - 1));
			}

			for (int i = 0; i < deliveriesForCustomerInt.size(); i++) {
				ClientLivre.add(ClientALivrer.get(i));
			}
			
			read.append("\n").append("--       BORDEREAU DE LIVRAISON       --").append("\n\n"); 
			read.append("Entrepôts Ouverts: ").append(entrepotOuvert).append("\n\n");
			for (int i = 0; i < ClientLivre.size(); i++) {
				read.append("Client: ").append(ClientLivre.get(i)).append(" - Entrepôt: ").append(entrepotClient.get(i)).append("\n");
			}

			writer.write(read.toString()); // Écrit la lecture dans le fichier de sortie

		} catch (IOException e) { // Gère les exceptions d'E/S
			e.printStackTrace(); // Affiche la trace de la pile des exceptions
		}
		return entrepotClient;
	}

	public static void main(String[] args) {
		List<Integer> entrepotClient = generationBordereau(); // Appelle la méthode pour générer le rapport
		
	}
}
