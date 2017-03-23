/**
 * Project: A00943900Assignment2
 * File: MotorcycleDao.java
 * Date: Mar 19, 2017
 * Time: 2:54:25 PM
 */
package a00943900.bcmc.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00943900.bcmc.dao.Dao;
import a00943900.bcmc.data.Database;
import a00943900.bcmc.data.Motorcycle;

/**
 * Data access object for motorcycles in for service
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class MotorcycleDao extends Dao {

	public static final String TABLE_NAME = Database.MOTORCYCLE_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Motorcycle data access object constructor.
	 * 
	 * @param database
	 *            the database object to set
	 */
	public MotorcycleDao(Database database) {
		super(database, TABLE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * @see a00943900.bcmc.dao.Dao#create()
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + tableName);
		String sql = String.format(
				"CREATE TABLE %s(" // tableName
						+ "%s %s, " // 1 id
						+ "%s %s(%d), " // 2 make
						+ "%s %s(%d), " // 3 model
						+ "%s %s, " // 4 year
						+ "%s %s(%d), " // 5 serialNumber
						+ "%s %s, " // 6 mileage
						+ "%s %s, " // 7 customerId
						+ "PRIMARY KEY (%s))", //
				tableName, //
				Fields.ID.name, Fields.ID.type, // 1 id
				Fields.MAKE.name, Fields.MAKE.type, Fields.MAKE.length, // 2 make
				Fields.MODEL.name, Fields.MODEL.type, Fields.MODEL.length, // 3 model
				Fields.YEAR.name, Fields.YEAR.type, // 4 year
				Fields.SERIAL_NUMBER.name, Fields.SERIAL_NUMBER.type, Fields.SERIAL_NUMBER.length, // 5 serialNumber
				Fields.MILEAGE.name, Fields.MILEAGE.type, // 6 mileage
				Fields.CUSTOMER_ID.name, Fields.CUSTOMER_ID.type, // 7 mileage
				Fields.ID.name);
		super.create(sql);
	}

	/**
	 * Insert a motorcycle into the motorcycle table.
	 * 
	 * @param motorcycle
	 *            the motorcycle to insert
	 * @throws SQLException
	 */
	public void add(Motorcycle motorcycle) throws SQLException {
		String sql = String.format(
				"INSERT INTO %s VALUES(" // tableName
						+ "?, " // 1 id
						+ "?, " // 2 make
						+ "?, " // 3 model
						+ "?, " // 4 year
						+ "?, " // 5 serialNumber
						+ "?, " // 6 mileage
						+ "?)", // 7 customerId
				tableName);
		int result = executeUpdate(sql, //
				motorcycle.getId(), // 1
				motorcycle.getMake(), // 2
				motorcycle.getModel(), // 3
				motorcycle.getYear(), // 4
				motorcycle.getSerialNumber(), // 5
				motorcycle.getMileage(), // 6
				motorcycle.getCustomerId()); // 7
		LOG.debug(String.format("Adding %s was %s", motorcycle, result != 0 ? "successful" : "unsuccessful"));
	}

	/**
	 * Retrieve all rows from the motorcycle table as a list of motorcycle objects.
	 * 
	 * @return a list of motorcycles
	 * @throws SQLException
	 */
	public List<Motorcycle> getAll() throws SQLException {
		Statement statement = null;
		List<Motorcycle> motorcycles = new ArrayList<>();
		String sql = String.format("SELECT * FROM %s", tableName);
		LOG.debug(sql);
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Motorcycle motorcycle = new Motorcycle.Builder() //
						.setId(resultSet.getLong(Fields.ID.name)) // 1
						.setMake(resultSet.getString(Fields.MAKE.name)) // 2
						.setModel(resultSet.getString(Fields.MODEL.name)) // 3
						.setYear(resultSet.getInt(Fields.YEAR.name)) // 4
						.setSerialNumber(resultSet.getString(Fields.SERIAL_NUMBER.name)) // 5
						.setMileage(resultSet.getLong(Fields.MILEAGE.name)) // 6
						.setCustomerId(resultSet.getLong(Fields.CUSTOMER_ID.name)) // 7
						.build();
				motorcycles.add(motorcycle);
			}
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d motorcycles from %s", motorcycles.size(), tableName));
		return motorcycles;
	}

	/**
	 * Update row in motorcycle table with given motorcycle.
	 * 
	 * @param motorcycle
	 *            the motorcycle to be updated
	 * @throws SQLException
	 */
	public void update(Motorcycle motorcycle) throws SQLException {
		String sql = String.format(
				"UPDATE %s SET " // tableName
						+ "%s=?, " // 1 id
						+ "%s=?, " // 2 make
						+ "%s=?, " // 3 model
						+ "%s=?, " // 4 year
						+ "%s=?, " // 5 serialNumber
						+ "%s=?, " // 6 mileage
						+ "%s=?, " // 7 customerId
						+ "WHERE (%s=?)", //
				tableName, //
				Fields.ID.name, // 1
				Fields.MAKE.name, // 2
				Fields.MODEL.name, // 3
				Fields.YEAR.name, // 4
				Fields.SERIAL_NUMBER.name, // 5
				Fields.MILEAGE.name, // 6
				Fields.CUSTOMER_ID.name); // 7
		int result = executeUpdate(sql, //
				motorcycle.getId(), // 1
				motorcycle.getMake(), // 2
				motorcycle.getModel(), // 3
				motorcycle.getYear(), // 4
				motorcycle.getSerialNumber(), // 5
				motorcycle.getMileage(), // 6
				motorcycle.getCustomerId(), // 7
				motorcycle.getId()); //
		LOG.debug(String.format("Updating %s was %s", motorcycle, result != 0 ? "successful" : "unsuccessful"));
	}

	/**
	 * Delete row from motorcycle table containing given motorcycle.
	 * 
	 * @param motorcycle
	 *            the motorcycle to be deleted
	 * @throws SQLException
	 */
	public void delete(Motorcycle motorcycle) throws SQLException {
		String sql = String.format(
				"DELETE FROM %s " // tableName
						+ "WHERE (%s=?)", // id
				tableName, // tableName
				Fields.ID.name); // id
		int result = executeUpdate(sql, //
				motorcycle.getId()); //
		LOG.debug(String.format("Deleting %s was %s", motorcycle, result != 0 ? "successful" : "unsuccessful"));
	}

	public enum Fields {

		ID("id", "BIGINT", -1), //
		MAKE("make", "VARCHAR", 25), //
		MODEL("model", "VARCHAR", 25), //
		YEAR("year", "INT", -1), //
		SERIAL_NUMBER("serialNumber", "VARCHAR", 10), //
		MILEAGE("mileage", "BIGINT", -1), //
		CUSTOMER_ID("customerId", "BIGINT", -1);

		private final String name;
		private final String type;
		private final int length;

		Fields(String name, String type, int length) {
			this.name = name;
			this.type = type;
			this.length = length;
		}
	}

}
