package entre_sortie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Donnees.BasesCSV;
import Donnees.Client;
import Donnees.Entrepot;
import Donnees.Route;
import Donnees.Site;

public class LectureCsv {
    
    public static List<Client> lectureClient(String chemin, String nomFichier) {
       
    	List<Client> clients = new ArrayList<>();
        List<String> noms = new ArrayList<>();
        List<String> mails = new ArrayList<>();
        List<Integer> id_site = new ArrayList<>();
        String nomComplet = chemin + File.separator + nomFichier;
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {
            
            String ligne;
            aLire.readLine();
            while ((ligne = aLire.readLine()) != null) { // Tant que le fichier n'est pas complétement visité
                String[] parts = ligne.split(";");
                if (parts.length == 3) {
                    noms.add(parts[0]);
                    mails.add(parts[1]);
                    id_site.add(Integer.parseInt(parts[2]));
                    Client client = new Client(parts[0],parts[1],Integer.parseInt(parts[2]));
                    clients.add(client);
                    
                }
            }
            
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
        } 
        
        return clients;
    }
    
    public static List<Entrepot> lectureEntrepot(String chemin, String nomFichier) {
    	
    	List<Entrepot> entrepots = new ArrayList<>();
        List<Integer> id_site = new ArrayList<>();
        List<Integer> id_entrepot = new ArrayList<>();
        List<Integer> couts_fixes = new ArrayList<>();
        List<Integer> stock = new ArrayList<>();

        String nomComplet = chemin + File.separator + nomFichier;
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {
            
            String ligne;
            aLire.readLine();
            while ((ligne = aLire.readLine()) != null) { // Tant que le fichier n'est pas complétement visité
                String[] parts = ligne.split(";");
                if (parts.length == 4) {
                	id_site.add(Integer.parseInt(parts[0]));
                	id_entrepot.add(Integer.parseInt(parts[1]));
                    couts_fixes.add(Integer.parseInt(parts[2]));
                    stock.add(Integer.parseInt(parts[3]));
                    
                    Entrepot entrepot = new Entrepot(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
                    entrepots.add(entrepot);
                    
                }
            }
            
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
        } 

        return entrepots;
    }
    
public static List<Route> lectureRoute(String chemin, String nomFichier) {
    	
    	List<Route> routes = new ArrayList<>();
        List<Integer> origine = new ArrayList<>();
        List<Integer> destination = new ArrayList<>();


        String nomComplet = chemin + File.separator + nomFichier;
        
        try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {
            
            String ligne;
            aLire.readLine();
            while ((ligne = aLire.readLine()) != null) { // Tant que le fichier n'est pas complétement visité
                String[] parts = ligne.split(";");
                if (parts.length == 2) {
                	origine.add(Integer.parseInt(parts[0]));
                	destination.add(Integer.parseInt(parts[1]));

                    
                    Route route = new Route(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
                    routes.add(route);
                    
                }
            }
            
        } catch (IOException e) {
            System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
        } 

        return routes;
    }

public static List<Site> lectureSite(String chemin, String nomFichier) {
	
	List<Site> sites = new ArrayList<>();
    List<Integer> id_site = new ArrayList<>();
    List<Integer> x = new ArrayList<>();
    List<Integer> y = new ArrayList<>();


    String nomComplet = chemin + File.separator + nomFichier;
    
    try (BufferedReader aLire = new BufferedReader(new FileReader(nomComplet))) {
        
        String ligne;
        aLire.readLine();
        while ((ligne = aLire.readLine()) != null) { // Tant que le fichier n'est pas complétement visité
            String[] parts = ligne.split(";");
            if (parts.length == 3) {
            	id_site.add(Integer.parseInt(parts[0]));
            	x.add(Integer.parseInt(parts[1]));
            	y.add(Integer.parseInt(parts[2]));


                
                Site site = new Site(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                sites.add(site);
                
            }
        }
        
    } catch (IOException e) {
        System.out.println("Une opération sur le fichier " + nomFichier + " a levé l’exception " + e);
    } 

    return sites;
}
    
    public static void main(String[] args) {
    	List<Client> clients =lectureClient("Jeux_de_donnees" + File.separator + "petit", "init-clients-30-10-Carre.csv");
    	System.out.println("Clients : "+clients);
    	
    	List<Entrepot> entrepots =lectureEntrepot("Jeux_de_donnees" + File.separator + "petit", "init-entrepots-30-5-Carre.csv");
    	System.out.println("Entrepots : "+entrepots);
    	
    	List<Route> routes =lectureRoute("Jeux_de_donnees" + File.separator + "petit", "init-routes-30-45-Carre.csv");
    	System.out.println("Routes : "+routes);
    	
    	List<Site> sites =lectureSite("Jeux_de_donnees" + File.separator + "petit", "init-sites-30-Carre.csv");
    	System.out.println("Sites : "+sites);
    	
    	BasesCSV.BDD(clients, entrepots, routes, sites);
    	
    }
}




