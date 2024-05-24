package Donnees;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BasesCSV {

    public static void BDD(List<Client> clients, List<Entrepot> entrepots, List<Route> routes, List<Site> sites) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
        String login = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            String requete = "DROP TABLE IF EXISTS clients;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            requete = "CREATE TABLE clients ("
                    + "nom VARCHAR(20),"
                    + "mail VARCHAR(256),"
                    + "id_site INT,"
                    + "PRIMARY KEY(id_site));";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            for (int i = 0; i < clients.size(); i++) {
                requete = "INSERT INTO clients (nom, mail, id_site) VALUES ('"
                        + clients.get(i).getNom() + "', '"
                        + clients.get(i).getMail() + "', "
                        + clients.get(i).getId_site() + ");";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(requete);
                }
            }

            requete = "DROP TABLE IF EXISTS entrepots;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            requete = "CREATE TABLE entrepots ("
                    + "id_site INT,"
                    + "id_entrepot INT,"
                    + "couts_fixes INT,"
                    + "stock INT,"
                    + "PRIMARY KEY(id_entrepot));";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            for (int i = 0; i < entrepots.size(); i++) {
                requete = "INSERT INTO entrepots (id_site, id_entrepot, couts_fixes, stock) VALUES ("
                        + entrepots.get(i).getId_site() + ", "
                        + entrepots.get(i).getId_entrepot() + ", "
                        + entrepots.get(i).getCouts_fixes() + ", "
                        + entrepots.get(i).getStock() + ");";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(requete);
                }
            }

            requete = "DROP TABLE IF EXISTS routes;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            requete = "CREATE TABLE routes ("
                    + "origine INT,"
                    + "destination INT,"
                    + "PRIMARY KEY(origine, destination));";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            for (int i = 0; i < routes.size(); i++) {
                requete = "INSERT INTO routes (origine, destination) VALUES ("
                        + routes.get(i).getOrigine() + ", "
                        + routes.get(i).getDestination() + ");";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(requete);
                }
            }

            requete = "DROP TABLE IF EXISTS sites;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            requete = "CREATE TABLE sites ("
                    + "id_site INT,"
                    + "x INT,"
                    + "y INT,"
                    + "PRIMARY KEY(id_site));";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(requete);
            }

            // Insert sites
            for (int i = 0; i < sites.size(); i++) {
                requete = "INSERT INTO sites (id_site, x, y) VALUES ("
                        + sites.get(i).getId_site() + ", "
                        + sites.get(i).getX() + ", "
                        + sites.get(i).getY() + ");";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(requete);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void afficherTable(String nomTable) {
        String url = "jdbc:hsqldb:file:" + "database" + File.separator + "basic" + ";shutdown=true";
        String login = "sa";
        String password = "";

        try (Connection connexion = DriverManager.getConnection(url, login, password);
             Statement declaration = connexion.createStatement()) {

            try (ResultSet resultat = declaration.executeQuery("SELECT * FROM " + nomTable)) {
                System.out.println("Table: " + nomTable);
                int nombreColonnes = resultat.getMetaData().getColumnCount();
                for (int i = 1; i <= nombreColonnes; i++) {
                    System.out.print(resultat.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println();
                while (resultat.next()) {
                    for (int i = 1; i <= nombreColonnes; i++) {
                        System.out.print(resultat.getString(i) + "\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] tables = {"clients", "entrepots", "routes", "sites"};
        for (String table : tables) {
            afficherTable(table);
        }
    }
}
