package methodes;
import java.io.File;
import java.util.*;

import Donnees.Route;
import Donnees.Site;

public class FloydWarshall {
    public static void main(String[] args) {
        // Charger les routes et les sites à partir des fichiers CSV
        List<Route> routes = LectureCsv.lectureRoute("Jeux_de_donnees" + File.separator + "grand", "init-routes-500-750-Carre.csv");
        List<Site> sites = LectureCsv.lectureSite("Jeux_de_donnees" + File.separator + "grand", "init-sites-500-Carre.csv");
        System.out.println("Routes : " + routes);
        System.out.println("Sites : " + sites);

        Comparator<Site> c = new Comparator<Site>() {
            public int compare(Site o1, Site o2) {
                return o1.getId_site() - o2.getId_site();
            }
        };
        Collections.sort(sites, c);

        // Précalculer les distances et les voisins

        // Exécuter l'algorithme de Floyd-Warshall
        int[][] distanceMatrix = floydWarshall(sites, routes);

        // Afficher la matrice de distances
        System.out.println("Matrice de distances:");
        for (int i = 0; i < sites.size(); i++) {
            for (int j = 0; j < sites.size(); j++) {
                if (i != j && distanceMatrix[i][j] < Integer.MAX_VALUE / 2) {
                    System.out.println(distanceMatrix[i][j]);
                }
            }
        }
    }

    public static int[][] floydWarshall(List<Site> sites, List<Route> routes) {
        CalculGraph.CalculDistances(sites, routes);
        int n = sites.size();
        int[][] dist = new int[n][n];

        // Initialiser les matrices de distances
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }

        // Mettre à jour les distances avec les routes disponibles
        for (Route route : routes) {
            int u = route.getOrigine() - 1; // Origine du site (-1 car les identifiants commencent à 1)
            int v = route.getDestination() - 1; // Destination du site (-1 car les identifiants commencent à 1)
            int d = route.getDistance();
            // Mettre à jour la distance si une route directe existe
            dist[u][v] = d;
            dist[v][u] = d; // Ajouter également la distance dans l'autre sens
        }

        // Appliquer l'algorithme de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < Integer.MAX_VALUE / 2 && dist[k][j] < Integer.MAX_VALUE / 2) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Retourner la matrice des distances
        return dist;
    }
}
