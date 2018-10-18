package DataBaseConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.postgresql.Driver;

public class UpdateQuery {
	
	public static Logger log = LogManager.getLogger(UpdateQuery.class.getClass());
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void updateQuery(String query) throws SQLException, IllegalAccessException {
			try {
				URL url = new URL("posgres jar path");
				String classname = "org.postgresql.Driver";
				URLClassLoader ucl = new URLClassLoader(new URL[] {url});
				Driver d = (Driver) Class.forName(classname, true, ucl).newInstance();
				DriverManager.registerDriver(new DriverShim(d));
				Class.forName(classname);
				log.info("check if driver loaded");
				connection = DriverManager.getConnection("dburl", "user", "password");
				log.info("check database connected successfully");
				statement = connection.createStatement();
				int count = statement.executeUpdate(query);
				log.info("query updated: "+count + "times");
			}
			catch(SQLException | ClassNotFoundException | IllegalArgumentException | MalformedURLException | InstantiationException e)
			{
				log.info("Something Wrong" +e.getMessage());
				e.printStackTrace();
			} finally {
				if(statement != null)
				{
					try
					{
						resultSet.close();
						log.info("Result set has been closed successfully");
						statement.close();
						log.info("create statement has been closed successfully");
						connection.close();
						log.info("db connection has been closed successfully");
					} catch(NullPointerException e)
					{
						e.getStackTrace();
						e.printStackTrace();
						log.info("Something went wrong with closing" +e.getMessage());
					}
					
				}
				else {
					log.info("statement is null");
				}
			}
		}
	@Test
	public void testDB() throws SQLException, IllegalAccessException {
		updateQuery("sqlQuery");

	}
}
