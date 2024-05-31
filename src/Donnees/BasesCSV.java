package Donnees;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Donnees.Commande;
import methodes.LectureTxt;
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
					+"id_entrepot INT,"
					+ "id_site INT,"
					+ "couts_fixes INT,"
					+ "stock INT,"
					+ "PRIMARY KEY(id_entrepot));";
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(requete);
			}

			for (int i = 0; i < entrepots.size(); i++) {
				requete = "INSERT INTO entrepots (id_site, id_entrepot, couts_fixes, stock) VALUES ("
						+ entrepots.get(i).getId_entrepot() + ", "
						+ entrepots.get(i).getId_site() + ", "
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Integer> RequeteStock() {
		List<Integer> stockEntrepotDispo = new ArrayList<>();
		List<Integer> entrepotDispo = new ArrayList<>();
		entrepotDispo= LectureTxt.lectureCommandeEntrepot("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			for (int i = 0; i < entrepotDispo.size(); i++) {
				String requete = "SELECT * FROM entrepots WHERE id_entrepot = "
						+ entrepotDispo.get(i);
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(requete)) {
						while (resultSet.next()) {
							int stock = resultSet.getInt("stock");
							stockEntrepotDispo.add(stock);


						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockEntrepotDispo; 
	}

	public static List<Integer> RequeteCout_fixe() {
		List<Integer> cout_fixesEntrepotDispo = new ArrayList<>();
		List<Integer> entrepotDispo = new ArrayList<>();
		entrepotDispo= LectureTxt.lectureCommandeEntrepot("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			entrepotDispo= LectureTxt.lectureCommandeEntrepot("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
			for (int i = 0; i < entrepotDispo.size(); i++) {
				String requete = "SELECT * FROM entrepots WHERE id_entrepot = "
						+ entrepotDispo.get(i);
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(requete)) {
						while (resultSet.next()) {
							int couts_fixes=resultSet.getInt("couts_fixes");
							cout_fixesEntrepotDispo.add(couts_fixes);

						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return cout_fixesEntrepotDispo;
	}


	public static int RequeteEntrepot() {
		List <Integer> NbEntrepotDispo = new ArrayList<>();
		List<Integer> entrepotDispo = new ArrayList<>();
		entrepotDispo= LectureTxt.lectureCommandeEntrepot("Jeux_de_donnees" + File.separator + "petit", "init-bordereau-commande-2021-12-25.txt");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			for (int i = 0; i < entrepotDispo.size(); i++) {
				String requete = "SELECT * FROM entrepots WHERE id_entrepot = "
						+ entrepotDispo.get(i);
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(requete)) {
						while (resultSet.next()) {
							int idEntrepot = resultSet.getInt("id_entrepot");
							NbEntrepotDispo.add(idEntrepot);


						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NbEntrepotDispo.size();
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
