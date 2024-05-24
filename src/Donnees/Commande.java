package Donnees;

import java.util.List;

public class Commande {
    private int nombreClient;
    private List<String> clientLivre;
    private List<Integer> quantite;
    private List<Integer> entrepotDispo;

    public Commande(int nombreClient, List<String> clientLivre, List<Integer> quantite, List<Integer> entrepotDispo) {
        this.nombreClient = nombreClient;
        this.clientLivre = clientLivre;
        this.quantite = quantite;
        this.entrepotDispo = entrepotDispo;
    }

    public int getNombreClient() {
        return nombreClient;
    }



    public List<String> getClientLivre() {
        return clientLivre;
    }

 
    public List<Integer> getQuantite() {
        return quantite;
    }


    public List<Integer> getEntrepotDispo() {
        return entrepotDispo;
    }

    
    public String toString() {
        return nombreClient + " " +clientLivre+ " " +quantite+ " " +entrepotDispo;
           
    }
}
