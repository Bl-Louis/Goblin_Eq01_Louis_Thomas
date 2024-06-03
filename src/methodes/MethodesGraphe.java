package methodes;

import java.io.File;
import java.util.*;

import Donnees.Route;
import Donnees.Site;

public class MethodesGraphe {
    // Méthode pour calculer les distances entre les sites
    public static void CalculDistances(List<Site> sites, List<Route> routes) {
        // Parcourir chaque route
        for (Route route : routes) {
            // Trouver les sites d'origine et de destination pour cette route
            Site origine = RechercheSiteId(sites, route.getOrigine());
            Site destination = RechercheSiteId(sites, route.getDestination());

            // Calculer la distance euclidienne entre les sites d'origine et de destination
            double distance = Math.sqrt(Math.pow(origine.getX() - destination.getX(), 2) + Math.pow(origine.getY() - destination.getY(), 2));
            // Arrondir la distance à l'entier supérieur
            int dist = (int) Math.ceil(distance);
            // Mettre à jour la distance dans l'objet Route
            route.setDistance(dist);
        }
    }

    // Méthode pour trouver un site par son identifiant
    private static Site RechercheSiteId(List<Site> sites, int id) {
        for (Site site : sites) {
            if (site.getId_site() == id) {
                return site;
            }
        }
        return null; // Retourner null si le site n'est pas trouvé
    }
    
    // Méthode pour calculer les voisins de chaque site
    public static void CalculVoisins(List<Site> sites, List<Route> routes) {
        for (Route route : routes) {
            // Trouver les sites d'origine et de destination pour cette route
            Site origine = RechercheSiteId(sites, route.getOrigine());
            Site destination = RechercheSiteId(sites, route.getDestination());
            
            // Ajouter la destination comme voisin de l'origine et vice versa
            origine.addSucc(destination);
            destination.addSucc(origine); 		
        } 
    }
    
    public static void main(String[] args) {
    	List<Route> routes = MethodesBDD.extractRoutes();        
    	List<Site> sites = MethodesBDD.extractSite();  
        System.out.println("Routes : " + routes);
        System.out.println("Sites : " + sites);
    }
}
