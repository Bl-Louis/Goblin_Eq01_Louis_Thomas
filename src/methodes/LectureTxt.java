package methodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Donnees.Commande;

public class LectureTxt {

    public static List<Commande> lectureCommande(String chemin, String nomFichier) {

        List<Commande> commandes = new ArrayList<>();
        List<String> clientLivre = new ArrayList<>();
        List<Integer> quantite = new ArrayList<>();
        List<Integer> entrepotDispo = new ArrayList<>();


        String nomComplet = chemin + File.separator + nomFichier;

        try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {

            String ligne;
            aLire.readLine(); // On saute l'entête

            // Lire la date
            String date = aLire.readLine();

            // Lire le nombre de clients
            ligne = aLire.readLine();
            int nombreClients = 0;
            if (ligne != null) {
                nombreClients = Integer.parseInt(ligne);
            }

            for (int i = 0; i < nombreClients; i++) {
                ligne = aLire.readLine();
                if (ligne != null) {
                    String[] parts = ligne.split(" : ");
                    if (parts.length == 2) {
                        clientLivre.add(parts[0]);
                        quantite.add(Integer.parseInt(parts[1]));
                    }
                }
            }

            ligne = aLire.readLine();
            if (ligne != null) {
                String[] entrepots = ligne.split(",");
                for (String entrepot : entrepots) {
                    entrepotDispo.add(Integer.parseInt(entrepot));
                }
            }

            Commande commande = new Commande(nombreClients, clientLivre, quantite, entrepotDispo);
            commandes.add(commande);

        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }

        // Afficher les résultats pour vérifier
        for (Commande commande : commandes) {
            System.out.println(commande);
        }

        return commandes;
    }

    public static void main(String[] args) {
        lectureCommande("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
      
    }
}
