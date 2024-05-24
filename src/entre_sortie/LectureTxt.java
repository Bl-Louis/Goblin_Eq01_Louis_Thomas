package entre_sortie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LectureTxt {
    
    public static void lectureCommande(String chemin, String nomFichier) {

        List<String> date = new ArrayList<>();
        List<Integer> nombreClient = new ArrayList<>();
        List<String> clientLivre = new ArrayList<>();
        List<Integer> quantite = new ArrayList<>();
        List<Integer> entrepotDispo = new ArrayList<>();
        
        String nomComplet = chemin + File.separator + nomFichier;

        try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {

            String ligne;
            aLire.readLine(); // On saute l'entête

            // Lire la date
            ligne = aLire.readLine();
            if (ligne != null) {
                date.add(ligne);
            }
            
            // Lire le nombre de clients
            ligne = aLire.readLine();
            if (ligne != null) {
                int nombreClients = Integer.parseInt(ligne);
                nombreClient.add(nombreClients);
                
                // Lire les informations des clients
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
            }
            
            // Lire la dernière ligne pour les entrepôts disponibles
            ligne = aLire.readLine();
            if (ligne != null) {
                String[] entrepots = ligne.split(",");
                for (String entrepot : entrepots) {
                    entrepotDispo.add(Integer.parseInt(entrepot));
                }
            }
            
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }
        
        // Afficher les résultats pour vérifier
        System.out.println("Date: " + date);
        System.out.println("Nombre de clients: " + nombreClient);
        System.out.println("Clients livrés: " + clientLivre);
        System.out.println("Quantité: " + quantite);
        System.out.println("Entrepôts disponibles: " + entrepotDispo);
    }   
    
    public static void main(String[] args) {
        lectureCommande("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
    }
}

