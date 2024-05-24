import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class BasesCSV {

	public static void main(String[] args) throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		String url = "jdbc:hsqldb:file:database"+File.separator+"basic;shutdown=true";
		String login = "sa";
		String password = "";
		try (Connection connection = DriverManager.getConnection( url, login, password )){
		String requete = "DROP TABLE clients IF EXISTS;";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		requete = "CREATE TABLE clients ("
				+"nom varchar(20),"
				+"mail varchar(256),"
				+"id_site int,"
				+"PRIMARY KEY(id_site))";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		/*for(int i=0;i<clients.size;i++)
		{
		requete = "INSERT INTO clients (nom, mail, id_site) VALUES"
				 +"(clients.getNom, clients.getMail, clients.getId_sites )";
		}
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}*/
		
		
		
		
		requete = "DROP TABLE entrepots IF EXISTS;";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		requete = "CREATE TABLE entrepots ("
				+"id_site int,"
				+"id_entrepot int,"
				+"cout_fixe int,"
				+"stock int,"
				+"PRIMARY KEY(id_entrepot))";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		/*for(int i=0;i<entrepots.size;i++)
		{
		requete = "INSERT INTO entrepots (id_site, id_entrepot, cout_fixe,stock) VALUES"
				 +"(entrepots.getId_site, entrepots.getId_entrepot, entrepots.getCout_fixe,entrepots.getStock )";
		}
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}*/
		
		
		
		requete = "DROP TABLE routes IF EXISTS;";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		requete = "CREATE TABLE routes ("
				+"origine int,"
				+"destination int,"
				+"PRIMARY KEY(origine,destination))";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		/*for(int i=0;i<routes.size;i++)
		{
		requete = "INSERT INTO routes (origine, destination) VALUES"
				 +"(routes.getOrigine, routes.getDestination )";
		}
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}*/
		
		
		
		
		requete = "DROP TABLE sites IF EXISTS;";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		requete = "CREATE TABLE sites ("
				+"id_site int,"
				+"x int,"
				+"y int,"
				+"PRIMARY KEY(id_site))";
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		/*for(int i=0;i<sites.size;i++)
		{
		requete = "INSERT INTO sites (id_site, x, y) VALUES"
				 +"(sites.id_site, sites.getX, sites.getY )";
		}
		try ( Statement statement = connection.createStatement() ) {
			statement.executeUpdate( requete );
			}
		}*/
		
	}
	}
	}
