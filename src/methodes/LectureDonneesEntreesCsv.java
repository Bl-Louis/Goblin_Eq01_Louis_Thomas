package methodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Donnees.Client;
import Donnees.Entrepot;
import Donnees.Route;
import Donnees.Site;

public class LectureDonneesEntreesCsv {
	private static String clients_fichier ="Jeux_de_donnees" + File.separator + "grand" + File.separator + "init-clients-500-200-Carre.csv"; 
	private static String entrepots_fichier ="Jeux_de_donnees" + File.separator + "grand" + File.separator + "init-entrepots-500-100-Carre.csv"; 
	private static String routes_fichier ="Jeux_de_donnees" + File.separator + "grand" + File.separator + "init-routes-500-750-Carre.csv"; 
	private static String sites_fichier ="Jeux_de_donnees" + File.separator + "grand" + File.separator + "init-sites-500-Carre.csv"; 

    
    // Méthode pour lire les données des clients à partir d'un fichier CSV
    public static List<Client> lectureClient() {
        List<Client> clients = new ArrayList<>();
        List<String> noms = new ArrayList<>();
        List<String> mails = new ArrayList<>();
        List<Integer> id_site = new ArrayList<>();
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(clients_fichier))) {
            String ligne;
            aLire.readLine(); // On saute l'entête
            // Tant que le fichier n'est pas complètement visité
            while ((ligne = aLire.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 3) {
                    // Extraction des informations sur les clients
                    noms.add(parts[0]);
                    mails.add(parts[1]);
                    id_site.add(Integer.parseInt(parts[2]));
                    // Création d'un nouvel objet Client et ajout à la liste
                    Client client = new Client(parts[0], parts[1], Integer.parseInt(parts[2]));
                    clients.add(client);
                }
            }
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + clients_fichier + " a levé l’exception " + e);
        } 
        return clients;
    }
    
    // Méthode pour lire les données des entrepôts à partir d'un fichier CSV
    public static List<Entrepot> lectureEntrepot() {
        List<Entrepot> entrepots = new ArrayList<>();
        List<Integer> id_site = new ArrayList<>();
        List<Integer> id_entrepot = new ArrayList<>();
        List<Integer> couts_fixes = new ArrayList<>();
        List<Integer> stock = new ArrayList<>();
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(entrepots_fichier))) {
            String ligne;
            aLire.readLine(); // On saute l'entête
            // Tant que le fichier n'est pas complètement visité
            while ((ligne = aLire.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 4) {
                    // Extraction des informations sur les entrepôts
                    id_site.add(Integer.parseInt(parts[0]));
                    id_entrepot.add(Integer.parseInt(parts[1]));
                    couts_fixes.add(Integer.parseInt(parts[2]));
                    stock.add(Integer.parseInt(parts[3]));
                    // Création d'un nouvel objet Entrepot et ajout à la liste
                    Entrepot entrepot = new Entrepot(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    entrepots.add(entrepot);
                }
            }
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + entrepots_fichier + " a levé l’exception " + e);
        } 
        return entrepots;
    }
    
    // Méthode pour lire les données des routes à partir d'un fichier CSV
    public static List<Route> lectureRoute() {
        List<Route> routes = new ArrayList<>();
        List<Integer> origine = new ArrayList<>();
        List<Integer> destination = new ArrayList<>();
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(routes_fichier))) {
            String ligne;
            aLire.readLine(); // On saute l'entête
            // Tant que le fichier n'est pas complètement visité
            while ((ligne = aLire.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 2) {
                    // Extraction des informations sur les routes
                    origine.add(Integer.parseInt(parts[0]));
                    destination.add(Integer.parseInt(parts[1]));
                    // Création d'un nouvel objet Route et ajout à la liste
                    Route route = new Route(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                    routes.add(route);
                }
            }
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + routes_fichier + " a levé l’exception " + e);
        } 
        return routes;
    }

    // Méthode pour lire les données des sites à partir d'un fichier CSV
    public static List<Site> lectureSite() {
        List<Site> sites = new ArrayList<>();
        List<Integer> id_site = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(sites_fichier))) {
            String ligne;
            aLire.readLine(); // On saute l'entête
            // Tant que le fichier n'est pas complètement visité
            while ((ligne = aLire.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 3) {
                    // Extraction des informations sur les sites
                    id_site.add(Integer.parseInt(parts[0]));
                    x.add(Integer.parseInt(parts[1]));
                    y.add(Integer.parseInt(parts[2]));
                    // Création d'un nouvel objet Site et ajout à la liste
                    Site site = new Site(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    sites.add(site);
                }
            }
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + sites_fichier + " a levé l’exception " + e);
        } 
        return sites;
    }
    
    // Méthode principale pour tester les méthodes de lecture et interagir avec la base de données
    public static void main(String[] args) {
        // Lecture des données à partir des fichiers CSV
        List<Client> clients = lectureClient();
        //System.out.println("Clients : " + clients);
        
        List<Entrepot> entrepots = lectureEntrepot();
        //System.out.println("Entrepots : " + entrepots);
        
        List<Route> routes = lectureRoute();
        //System.out.println("Routes : " + routes);
        
        List<Site> sites = lectureSite();
        //System.out.println("Sites : " + sites);
        
        // Interaction avec la base de données en utilisant les méthodes de la classe MethodesBDD
        MethodesBDD.BDD(clients, entrepots, routes, sites);
        int entrepotID = MethodesBDD.RequeteEntrepot();
        //System.out.println("Entrepôt : " + entrepotID);
        List<Integer> stock = MethodesBDD.RequeteStock();
        //System.out.println("Stock : " + stock);
        List<Integer> coutsFixes = MethodesBDD.RequeteCout_fixe();
        //System.out.println("Coûts fixes : " + coutsFixes);
    }
}

