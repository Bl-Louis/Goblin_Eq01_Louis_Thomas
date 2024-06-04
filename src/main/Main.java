package main;

import methodes.AlgoCheminLePlusCourt;
import methodes.EcritureBordereauLivraisonTxt;
import methodes.EcritureFichierPulpJson;
import methodes.LectureDonneesEntreesCsv;
import methodes.MethodesBDD;
import methodes.SolverPython; 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("+------------------------------+");
        System.out.println("|            GOBLIN            |");
        System.out.println("+------------------------------+");
        
        //LectureBordereauCommandeTxt.main(args); // Lecture données d'entrées commandes..
        
        System.out.println("+---------------LectureDonneesEntreesCsv--------------+");
        LectureDonneesEntreesCsv.main(args); // Lecture données d'entrées site, route, entrepots, clients ... + écriture des données dans BDD
        
        //System.out.println("+---------------LectureBordereauCommandeTxt--------------+");
        //System.out.println("+---------------MethodesGraphe--------------+");
        //MethodesGraphe.main(args); //Pas forcément utile car déjà appelé dans AlgoCheminPlusCourt
        
        System.out.println("+---------------AlgoCheminLePlusCourt--------------+");
        AlgoCheminLePlusCourt.main(args); // On calcul tous les plus courts chemins + écriture dans la BDD
        
        //System.out.println("+---------------MethodesBDD--------------+");
        //MethodesBDD.main(args); // On extract de la BDD le données plus court chemin et on fait la matrice de côut;
        
        System.out.println("+---------------EcritureFichierPulpJson--------------+");
        EcritureFichierPulpJson.main(args); // Ecriture du fichier Json qui va être envoyé dans le solver
        
        System.out.println("+---------------SolverPython--------------+");
        SolverPython.main(args);
        
        System.out.println("+---------------EcritureBordereauLivraisonTxt--------------+");
        EcritureBordereauLivraisonTxt.main(args); // On lit le fichier en sortie du solver et on créer un fichier txt bordereau de livraison
        System.out.println("Voulez-vous mettre à jour le stock ? (oui/non)");
        
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("oui")) {
            MethodesBDD.MajStock();
        }
        else {
            System.out.println("Les stocks des entrepots n'ont pas été mis à jour");
        }
    
        System.out.println("+----------------------------------+");
        System.out.println("|            FIN GOBLIN            |");
        System.out.println("+----------------------------------+");
    }
}