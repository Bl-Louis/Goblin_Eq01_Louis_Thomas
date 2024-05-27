package Donnees;

import java.util.*;

public class Graph {
    public Map<Integer, Site> sites = new HashMap<>();
    Map<Integer, List<Route>> adjacencyList = new HashMap<>();

    public Graph(Map<Integer, Site> sites, Map<Integer, List<Route>> adjacencyList) {
        this.sites = sites;
        this.adjacencyList = adjacencyList;
    }

    public Site getSite(int id) {
        return sites.get(id);
    }

    public List<Route> getRoutes(int id) {
        return adjacencyList.get(id);
    }
}
