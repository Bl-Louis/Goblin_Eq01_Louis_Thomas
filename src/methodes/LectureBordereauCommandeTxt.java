package methodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LectureBordereauCommandeTxt {
	private static String cheminComplet ="Jeux_de_donnees" + File.separator + "grand"+ File.separator +"init-bordereau-commande-2021-01-18.txt"; 

    // Méthode pour lire les commandes des clients à partir d'un fichier texte
    public static  List<String> lectureCommandeClient() {
        // Listes pour stocker les clients, les quantités et les entrepôts disponibles
        List<String> clientLivre = new ArrayList<>();
        List<Integer> quantite = new ArrayList<>();
        List<Integer> entrepotDispo = new ArrayList<>();

        // Chemin complet du fichier
        //String nomComplet = chemin  nomFichier;

        // Lecture du fichier
        try (BufferedReader aLire = new BufferedReader(new FileReader(cheminComplet))) {
            String ligne;
            aLire.readLine(); // On saute l'entête

            // Lire la date
            String date = aLire.readLine();

            // Lire le nombre de clients
            ligne = aLire.readLine();
            int nombreClients = 0;
            if (ligne != null) {
                nombreClients = Integer.parseInt(ligne); // Convertir en entier
            }

            // Lire les détails de chaque client
            for (int i = 0; i < nombreClients; i++) {
                ligne = aLire.readLine();
                if (ligne != null) {
                    String[] parts = ligne.split(" : "); // Séparer le client et la quantité
                    if (parts.length == 2) {
                        clientLivre.add(parts[0]); // Ajouter le client à la liste
                        quantite.add(Integer.parseInt(parts[1])); // Ajouter la quantité à la liste
                    }
                }
            }

            // Lire les entrepôts disponibles
            ligne = aLire.readLine();
            if (ligne != null) {
                String[] entrepots = ligne.split(","); // Séparer les entrepôts
                for (String entrepot : entrepots) {
                    entrepotDispo.add(Integer.parseInt(entrepot)); // Ajouter l'entrepôt à la liste
                }
            }

        } catch (IOException e) {
            // Gérer les exceptions d'entrée/sortie
            System.out.println("Une opération sur le fichier " + cheminComplet + " a levé l’exception " + e);
        } catch (NumberFormatException e) {
            // Gérer les exceptions de format de nombre
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }
        //System.out.println(clientLivre);

        // Retourner le nombre de clients
        return clientLivre;
    }

    // Méthode pour lire les commandes des entrepôts à partir d'un fichier texte
    public static List<Integer> lectureCommandeEntrepot() {
        List<String> clientLivre = new ArrayList<>();
        List<Integer> quantite = new ArrayList<>();
        List<Integer> entrepotDispo = new ArrayList<>();

        // Lecture du fichier
        try (BufferedReader aLire = new BufferedReader(new FileReader(cheminComplet))) {
            String ligne;
            aLire.readLine(); // On saute l'entête

            // Lire la date
            String date = aLire.readLine();

            // Lire le nombre de clients
            ligne = aLire.readLine();
            int nombreClients = 0;
            if (ligne != null) {
                nombreClients = Integer.parseInt(ligne); // Convertir en entier
            }

            // Lire les détails de chaque client
            for (int i = 0; i < nombreClients; i++) {
                ligne = aLire.readLine();
                if (ligne != null) {
                    String[] parts = ligne.split(" : "); // Séparer le client et la quantité
                    if (parts.length == 2) {
                        clientLivre.add(parts[0]); // Ajouter le client à la liste
                        quantite.add(Integer.parseInt(parts[1])); // Ajouter la quantité à la liste
                    }
                }
            }

            // Lire les entrepôts disponibles
            ligne = aLire.readLine();
            if (ligne != null) {
                String[] entrepots = ligne.split(","); // Séparer les entrepôts
                for (String entrepot : entrepots) {
                    entrepotDispo.add(Integer.parseInt(entrepot)); // Ajouter l'entrepôt à la liste
                }
            }

        } catch (IOException e) {
            // Gérer les exceptions d'entrée/sortie
            System.out.println("Une opération sur le fichier " + cheminComplet + " a levé l’exception " + e);
        } catch (NumberFormatException e) {
            // Gérer les exceptions de format de nombre
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }
        // Retourner la liste des entrepôts disponibles
        //Collections.sort(entrepotDispo); Déjà fait dans cost matrix METHODEBDD
        
        //System.out.println(entrepotDispo);

        return entrepotDispo;
    }

    // Méthode pour lire les stocks à partir d'un fichier texte
    public static List<Integer> lectureCommandeQuantite() {
        List<String> clientLivre = new ArrayList<>();
        List<Integer> quantite = new ArrayList<>();
        List<Integer> entrepotDispo = new ArrayList<>();

        // Lecture du fichier
        try (BufferedReader aLire = new BufferedReader(new FileReader(cheminComplet))) {
            String ligne;
            aLire.readLine(); // On saute l'entête

            // Lire la date
            String date = aLire.readLine();

            // Lire le nombre de clients
            ligne = aLire.readLine();
            int nombreClients = 0;
            if (ligne != null) {
                nombreClients = Integer.parseInt(ligne); // Convertir en entier
            }

            // Lire les détails de chaque client
            for (int i = 0; i < nombreClients; i++) {
                ligne = aLire.readLine();
                if (ligne != null) {
                    String[] parts = ligne.split(" : "); // Séparer le client et la quantité
                    if (parts.length == 2) {
                        clientLivre.add(parts[0]); // Ajouter le client à la liste
                        quantite.add(Integer.parseInt(parts[1])); // Ajouter la quantité à la liste
                    }
                }
            }

            // Lire les entrepôts disponibles
            ligne = aLire.readLine();
            if (ligne != null) {
                String[] entrepots = ligne.split(","); // Séparer les entrepôts
                for (String entrepot : entrepots) {
                    entrepotDispo.add(Integer.parseInt(entrepot)); // Ajouter l'entrepôt à la liste
                }
            }

        } catch (IOException e) {
            // Gérer les exceptions d'entrée/sortie
            System.out.println("Une opération sur le fichier " + cheminComplet + " a levé l’exception " + e);
        } catch (NumberFormatException e) {
            // Gérer les exceptions de format de nombre
            System.out.println("Erreur de format de nombre : " + e.getMessage());
        }
        //System.out.println(quantite);
        // Retourner la liste des quantités
        return quantite;
    }

    // Méthode principale pour tester les méthodes de lecture
    public static void main(String[] args) {
        // Appel des méthodes de lecture pour tester
        //lectureCommandeClient();
        lectureCommandeEntrepot();
        //lectureCommandeQuantite();
    }
}
