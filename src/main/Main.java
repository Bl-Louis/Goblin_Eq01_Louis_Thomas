package main;

import methodes.AlgoCheminLePlusCourt;
import methodes.EcritureBordereauLivraisonTxt;
import methodes.EcritureFichierPulpJson;
import methodes.LectureBordereauCommandeTxt;
import methodes.LectureDonneesEntreesCsv;
import methodes.MethodesBDD;
import methodes.MethodesGraphe;
import methodes.SolverPython; 

public class Main {
    public static void main(String[] args) {
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
        
        System.out.println("+----------------------------------+");
        System.out.println("|            FIN GOBLIN            |");
        System.out.println("+----------------------------------+");
    }
}