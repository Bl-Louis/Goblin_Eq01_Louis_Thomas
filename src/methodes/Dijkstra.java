//package methodes;
//
//import AdjacencyList.AdjacencyListDirectedValuedGraph;
//import Nodes.DirectedNode;
//
//public class Dijkstra {
//	
//	public static void Dijkstra(AdjacencyListDirectedValuedGraph al, DirectedNode s, int order) {
//        int n = al.getNbNodes(); 
//        int[] value = new int[n];
//        boolean[] mark = new boolean[n];
//        DirectedNode[] pred = new DirectedNode[n];
//
//        // Initialisation des structures de données
//        for (int i = 0; i < n; i++) {
//            value[i] = Integer.MAX_VALUE / 2;
//            mark[i] = false;
//            pred[i] = null;
//        }
//
//        value[s.getLabel()] = 0; // Initialisation de la valeur du sommet source à 0
//
//        for (int i = 0; i < n; i++) {
//            // Recherche du sommet non marqué avec la valeur minimale
//            int min = Integer.MAX_VALUE / 2;
//            DirectedNode nodeMin = null;
//            for (int j = 0; j < n; j++) {
//                if (!mark[j] && value[j] < min) {
//                    min = value[j];
//                    nodeMin = al.getNodes().get(j);
//                }
//            }
//
//            if (nodeMin == null) {
//                break; // Tous les sommets atteignables ont été traités
//            }
//
//            mark[nodeMin.getLabel()] = true;
//
//            // Mise à jour des successeurs du sommet choisi
//            for (DirectedNode neighbor : nodeMin.getListSuccs()) {
//                if (!mark[neighbor.getLabel()] && value[nodeMin.getLabel()] + nodeMin.getSuccs().get(neighbor) < value[neighbor.getLabel()]) {
//                    value[neighbor.getLabel()] = value[nodeMin.getLabel()] + nodeMin.getSuccs().get(neighbor);
//                    pred[neighbor.getLabel()] = nodeMin;
//                }
//            }
//        }
//
//        // Affichage des résultats
//        System.out.println("Distances depuis le sommet " + s + ":");
//        for (int i = 0; i < n; i++) {
//            System.out.println("Sommet " + i + " - Distance: " + value[i] + " - Prédécesseur: " + (pred[i] != null ? pred[i].getLabel() : "null"));
//        }
//    }
//
//}
