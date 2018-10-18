package DataBaseConnection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.apache.log4j.LogManager;

public class ConnectToOracleDB {
	public static Logger log = LogManager.getLogger(ConnectToOracleDB.class.getClass());
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	private static Statement statement = null;

	public List<String> connectDatabaseToOracle(String query, String colName) throws SQLException {
		boolean result = false;
		int attempts = 0;
		List<String> values = new ArrayList<String>();
		while (attempts < 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				log.info("-> check if driver loaded");
				connection = DriverManager.getConnection("db url", "username", "password");
				log.info("-> check if databases are connected successfully");
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					values.add(resultSet.getString(colName));
				}
				result = true;
				break;
			} catch (ClassNotFoundException e) {
				log.info("Something wrong" + e.getMessage());
				e.printStackTrace();
				++attempts;
			}
		}
		statement.close();
		log.info("create statement has been closed");
		connection.close();
		log.info("Database connection closed successsfully");
		return values;
	}

	public String getQuery(String sqlQuery, int rowNum, String colName) throws SQLException {
		List<String> connection = connectDatabaseToOracle(sqlQuery, colName);
		String company;

		for (int i = 0; i < connection.size(); i++) {
			connection.get(i);
		}
		company = connection.get(rowNum);
		System.out.println(company);
		return company;
	}

	@Test
	public void testDB() throws SQLException {
		getQuery("sqlquery", 1, "colName");

	}
}
