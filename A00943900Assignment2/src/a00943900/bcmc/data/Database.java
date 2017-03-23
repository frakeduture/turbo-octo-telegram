/**
 * Project: examples8
 * File: Database.java
 * Date: 2012-10-28
 * Time: 12:26:04 PM
 */

package a00943900.bcmc.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author scirka
 * 
 */
public class Database {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";
	public static final String DB_PROPERTIES_FILENAME = "db.properties";
	public static final String TABLE_ROOT = "S900_";
	public static final String CUSTOMER_TABLE_NAME = TABLE_ROOT + "Customer";
	public static final String INVENTORY_TABLE_NAME = TABLE_ROOT + "Inventory";
	public static final String MOTORCYCLE_TABLE_NAME = TABLE_ROOT + "Motorcycle";

	private static final Logger LOG = LogManager.getLogger();

	private static Connection connection;
	private final Properties properties;

	/**
	 * Database constructor.
	 * 
	 * @param properties
	 *            the database properties file
	 */
	public Database(Properties properties) {
		LOG.debug("Loading database properties from db.properties");
		this.properties = properties;
	}

	/**
	 * Get the database connection object.
	 * 
	 * @return the database connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	/**
	 * Connect to the database.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DB_DRIVER_KEY));
		LOG.debug("DB Driver loaded");
		connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY),
				properties.getProperty(DB_PASSWORD_KEY));
		LOG.debug("Database connected");
	}

	/**
	 * Close the database connection.
	 */
	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			LOG.debug("Database disconnected");
		}
	}

	/**
	 * Check whether or not the specified table name exists on the database.
	 * 
	 * @param connection
	 *            the database connection
	 * @param tableName
	 *            the table name
	 * @return whether or not the table exists
	 * @throws SQLException
	 */
	public static boolean tableExists(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}

		return false;
	}

}
