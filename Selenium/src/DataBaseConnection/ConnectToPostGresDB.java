package DataBaseConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ConnectToPostGresDB {
	
	public static Logger log = LogManager.getLogger(ConnectToPostGresDB.class.getClass());
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public List<String> ConnectToDB(String query, String colName) throws SQLException, IllegalAccessException {
		boolean result = false;
		int attempts = 0;
		List<String> values = new ArrayList<String>();
		while(attempts < 2)
		{
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
				resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					values.add(resultSet.getString(colName));
				}
				result = true;
				break;
			}
			catch(SQLException | ClassNotFoundException | IllegalArgumentException | MalformedURLException | InstantiationException e)
			{
				log.info("Something Wrong" +e.getMessage());
				e.printStackTrace();
				++attempts;
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
		return values;
		
	}
	public String getQuery(String sqlQuery, int rowNum, String colName) throws SQLException, IllegalAccessException {
		List<String> connection = ConnectToDB(sqlQuery, colName);
		String company;

		for (int i = 0; i < connection.size(); i++) {
			connection.get(i);
		}
		company = connection.get(rowNum);
		System.out.println(company);
		return company;
	}

	@Test
	public void testDB() throws SQLException, IllegalAccessException {
		getQuery("sqlQuery", 1, "colName");

	}
}
