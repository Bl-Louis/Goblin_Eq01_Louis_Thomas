package methodes;

import java.util.*;

import Donnees.Graph;
import Donnees.Route;
import Donnees.Site;

public class Dijkstra {
    public static void dijkstra(Graph graph, int startId) {
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        PriorityQueue<SiteDistance> queue = new PriorityQueue<>(Comparator.comparingDouble(sd -> sd.distance));

        for (int id : graph.sites.keySet()) {
            distances.put(id, Double.MAX_VALUE);
        }
        distances.put(startId, 0.0);
        queue.add(new SiteDistance(startId, 0.0));

        while (!queue.isEmpty()) {
            SiteDistance current = queue.poll();
            if (current.distance > distances.get(current.siteId)) continue;

            List<Route> routesFromCurrent = graph.getRoutes(current.siteId);
            if (routesFromCurrent != null) {
                for (Route route : routesFromCurrent) {
                    int neighborId = route.getDestination();
                    if(current.siteId== route.getOrigine()) {
                        double newDist = distances.get(current.siteId) ;
//                                Math.sqrt(Math.pow(sites.getX() - sites.getX(), 2) + Math.pow(site.getY() - site.getY(), 2));
//;								Math.sqrt(Math.pow(site1.getX() - site2.getX(), 2) + Math.pow(site1.getY() - site2.getY(), 2));


                    }
                    
                    double newDist = distances.get(current.siteId);

                    if (newDist < distances.get(neighborId)) {
                        distances.put(neighborId, newDist);
                        predecessors.put(neighborId, current.siteId);
                        queue.add(new SiteDistance(neighborId, newDist));
                    }
                }
            }
        }

        // Affichage des résultats
        System.out.println("Distances depuis le site " + startId + ":");
        for (int id : distances.keySet()) {
            System.out.println("Site " + id + " - Distance: " + distances.get(id) + " - Prédécesseur: " + (predecessors.containsKey(id) ? predecessors.get(id) : "null"));
        }
    }

    static class SiteDistance {
        int siteId;
        double distance;

        public SiteDistance(int siteId, double distance) {
            this.siteId = siteId;
            this.distance = distance;
        }
    }
}
