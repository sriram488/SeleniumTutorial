package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.Driver;

public class DriverShim extends Driver {
	private Driver postgresDriver;

	public DriverShim(Driver postgresDriver) {
		this.postgresDriver = postgresDriver;
	}

	public boolean acceptsURL(String url) throws SQLException {
		return this.postgresDriver.acceptsURL(url);
	}

	public Connection connect(String url, Properties p) throws SQLException {
		return this.postgresDriver.connect(url, p);
	}

	public int getMajorVersion() {
		return this.postgresDriver.getMajorVersion();
	}

	public int getMinorVersion() {
		return this.postgresDriver.getMinorVersion();
	}

	public DriverPropertyInfo[] getPropertyInfo(String url, Properties p) throws SQLException {
		return this.postgresDriver.getPropertyInfo(url, p);
	}

	public boolean jdbcCompliant() {
		return this.postgresDriver.jdbcCompliant();
	}

}
