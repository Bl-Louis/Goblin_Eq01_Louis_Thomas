package methodes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import Donnees.Client;
import Donnees.Entrepot;
import Donnees.Route;
import Donnees.Site;

public class MethodesBDD {

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
	public static void BDDToutesLesRoutes(List<Integer> idOrigineList, List<Integer> idDestinationList, List<Integer> distanceList) {
		String requete;
		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			requete = "DROP TABLE IF EXISTS toutesLesRoutes;";
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(requete);
			}

			requete = "CREATE TABLE toutesLesRoutes ("
					+ "idOrigine INT,"
					+ "idDestination INT,"
					+ "distance INT,"
					+ "PRIMARY KEY(idOrigine,idDestination));";
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(requete);
			}

			for (int i = 0; i < idOrigineList.size(); i++) {
				int idOrigine = idOrigineList.get(i);
				int idDestination = idDestinationList.get(i);
				int distance = distanceList.get(i);

				requete = "INSERT INTO toutesLesRoutes (idOrigine, idDestination, distance) VALUES ("
						+ idOrigine + ", "
						+ idDestination + ", "
						+ distance + ");";
				try (Statement statement = connection.createStatement()) {
					statement.executeUpdate(requete);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static int[][] extractDistanceMatrixForIDs() {
		List<Integer> entrepotDispo = LectureBordereauCommandeTxt.lectureCommandeEntrepot();
		Collections.sort(entrepotDispo); // Trier la liste ordre croissanr      

		// Création d'une map pour mapper les ID à des indices dans la matrice
		Map<Integer, Integer> idToIndex = new HashMap<>();
		for (int i = 0; i < entrepotDispo.size(); i++) {
			idToIndex.put(entrepotDispo.get(i), i);
		}

		// Création d'une matrice pour stocker les distances
		int n = entrepotDispo.size();
		int[][] distanceMatrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distanceMatrix[i], Integer.MAX_VALUE / 2); // Initialiser avec des distances maximales
		}

		// Mettre les distances de la diagonale à 0
		for (int i = 0; i < n; i++) {
			distanceMatrix[i][i] = 0;
		}

		// Connexion à la base de données
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true", "sa", "")) {
			// Préparation de la requête SQL
			StringJoiner idList = new StringJoiner(",", "(", ")");
			for (Integer id : entrepotDispo) {
				idList.add(id.toString());
			}
			String requete = "SELECT * FROM ToutesLesRoutes WHERE idOrigine IN " + idList.toString() + " AND idDestination IN " + idList.toString();

			// Exécution de la requête
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(requete)) {
				while (resultSet.next()) {
					int idOrigine = resultSet.getInt("idOrigine");
					int idDestination = resultSet.getInt("idDestination");
					int distance = resultSet.getInt("distance");
					// Vérifier que les ID sont dans la liste spécifiée et les ajouter à la matrice
					if (idToIndex.containsKey(idOrigine) && idToIndex.containsKey(idDestination)) {
						int indexOrigine = idToIndex.get(idOrigine);
						int indexDestination = idToIndex.get(idDestination);
						distanceMatrix[indexOrigine][indexDestination] = distance;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return distanceMatrix;
	}


	public static List<Integer> RequeteStock() {
		List<Integer> stockEntrepotDispo = new ArrayList<>();
		List<Integer> entrepotDispo = new ArrayList<>();
		entrepotDispo= LectureBordereauCommandeTxt.lectureCommandeEntrepot();
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
		entrepotDispo= LectureBordereauCommandeTxt.lectureCommandeEntrepot();
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			entrepotDispo= LectureBordereauCommandeTxt.lectureCommandeEntrepot();
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
		entrepotDispo= LectureBordereauCommandeTxt.lectureCommandeEntrepot();
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


	public static List<Route> extractRoutes() {
		List<Route> routes = new ArrayList<>();
		
		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";
		
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String requete = "SELECT* FROM routes";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(requete)) {

				while (resultSet.next()) {
					int origine = resultSet.getInt("origine");
					int destination = resultSet.getInt("destination");
					routes.add(new Route(origine, destination));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return routes;
	}
	
	public static List<Site> extractSite() {
		List<Site> sites = new ArrayList<>();
		
		String url = "jdbc:hsqldb:file:database" + File.separator + "basic;shutdown=true";
		String login = "sa";
		String password = "";
		
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String requete = "SELECT* FROM sites";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(requete)) {

				while (resultSet.next()) {
					int id_site = resultSet.getInt("id_site");
					int x = resultSet.getInt("x");
					int y = resultSet.getInt("y");

					sites.add(new Site(id_site, x,y));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sites;
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

		String[] tables = {"clients", "entrepots", "routes", "sites","toutesLesRoutes"};
		for (String table : tables) {
			afficherTable(table);
		}
		//List<Route> routes = extractRoutes();
		//System.out.println(routes);
		
		//List<Site> sites = extractSite();
		//System.out.println(sites);

		// Appel de la méthode pour extraire la matrice de distances
		int[][] distanceMatrix = extractDistanceMatrixForIDs();

		// Affichage de la matrice de distances
		System.out.println("Matrice de distances:");
		for (int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix[i].length; j++) {
				System.out.print(distanceMatrix[i][j] + "\t");
			}
			System.out.println();
		}

	}
}