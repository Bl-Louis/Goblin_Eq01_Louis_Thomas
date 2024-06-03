package methodes;

import java.util.*;
import Donnees.Route;
import Donnees.Site;

public class AlgoCheminLePlusCourt {

    public static void main(String[] args) {
        // Charger les routes et les sites à partir des fichiers CSV
    	List<Route> routes = MethodesBDD.extractRoutes();        
    	List<Site> sites = MethodesBDD.extractSite();   
    	//System.out.println("Routes : " + routes); // Affiche les routes chargées depuis le fichier CSV
        //System.out.println("Sites : " + sites); // Affiche les sites chargés depuis le fichier CSV
        
        // Initialisation de listes pour stocker les identifiants d'origine, de destination et les distances
        List<Integer> idOrigine = new ArrayList<>();
        List<Integer> idDestination = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        
        // Comparateur pour trier les sites par leur identifiant
        Comparator<Site> c = new Comparator<Site>() {
            public int compare(Site o1, Site o2) {
                return o1.getId_site() - o2.getId_site();
            }
        };
        Collections.sort(sites, c); // Trie les sites par identifiant

        // Précalculer les distances et les voisins

        // Exécution de l'algorithme de Floyd-Warshall pour calculer les distances
        int[][] distanceMatrix = floydWarshall(sites, routes);

        // Affichage de la matrice de distances
        //System.out.println("Matrice de distances:");
        for (int i = 0; i < sites.size(); i++) {
            for (int j = 0; j < sites.size(); j++) {
                if (i != j && distanceMatrix[i][j] < Integer.MAX_VALUE / 2) {
                    //System.out.println("Distance de " + sites.get(i).getId_site() + " à " + sites.get(j).getId_site() + ": " + distanceMatrix[i][j]);
                    // Ajout des identifiants d'origine, de destination et des distances à leurs listes respectives
                    idOrigine.add(sites.get(i).getId_site());
                    idDestination.add(sites.get(j).getId_site());
                    distance.add(distanceMatrix[i][j]);
                }
            }
        }
        // Appel à une méthode pour enregistrer toutes les routes dans une base de données
        MethodesBDD.BDDToutesLesRoutes(idOrigine, idDestination, distance);
    }

    // Méthode pour exécuter l'algorithme de Floyd-Warshall
    public static int[][] floydWarshall(List<Site> sites, List<Route> routes) {
        int n = sites.size();
        MethodesGraphe.CalculDistances(sites, routes); // Calcul des distances entre les sites

        Map<Integer, Integer> idToIndex = new HashMap<>();
        int[][] dist = new int[n][n];

        // Initialiser les distances avec des valeurs "infinies"
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }

        // Créer la map des ID vers les indices
        for (int i = 0; i < n; i++) {
            idToIndex.put(sites.get(i).getId_site(), i);
        }

        // Mettre à jour les distances avec les routes disponibles
        for (Route route : routes) {
            int u = idToIndex.get(route.getOrigine()); // Indice de l'origine
            int v = idToIndex.get(route.getDestination()); // Indice de la destination
            int d = route.getDistance();
            // Mettre à jour la distance si une route directe existe
            dist[u][v] = d;
            dist[v][u] = d; // Ajouter également la distance dans l'autre sens
        }

        // Mettre les distances à 0 pour les distances du site à lui-même
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        // Appliquer l'algorithme de Floyd-Warshall pour calculer les plus courts chemins entre tous les paires de sites
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        return dist; // Retourne la matrice de distances
    }
}
