package methodes;

import java.io.File;
import java.util.*;

import Donnees.Route;
import Donnees.Site;

public class CalculGraph {
    public static void CalculDistances(List<Site> sites, List<Route> routes) {

        // Parcourir chaque route
        for (Route route : routes) {
            // Trouver les sites d'origine et de destination pour cette route
            Site origine = RechercheSiteId(sites, route.getOrigine());
            Site destination = RechercheSiteId(sites, route.getDestination());

            // Calculer la distance entre les sites d'origine et de destination
            double distance = Math.sqrt(Math.pow(origine.getX() - destination.getX(), 2) + Math.pow(origine.getY() - destination.getY(), 2));
            int dist=(int) Math.ceil(distance);
            
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
    
    public static void CalculVoisins(List<Site> sites, List<Route> routes) {
    	for(Route route : routes) {
            Site origine = RechercheSiteId(sites, route.getOrigine());
            Site destination = RechercheSiteId(sites, route.getDestination());
            
            origine.addSucc(destination);
            destination.addSucc(origine); 		
    	} 
    }
    
	
    public static void main(String[] args) {

    	List<Route> routes =LectureCsv.lectureRoute("Jeux_de_donnees" + File.separator + "petit", "init-routes-30-45-Carre.csv");
    	System.out.println("Routes : "+routes);

    	List<Site> sites =LectureCsv.lectureSite("Jeux_de_donnees" + File.separator + "petit", "init-sites-30-Carre.csv"); 
    	System.out.println("Sites : "+sites);
    	

    }
	 
}


