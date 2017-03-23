/**
 * Project: A00943900Lab6
 * File: CustomerDao.java
 * Date: Mar 1, 2017
 * Time: 10:22:05 AM
 */
package a00943900.bcmc.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00943900.bcmc.ApplicationException;
import a00943900.bcmc.dao.Dao;
import a00943900.bcmc.data.Customer;
import a00943900.bcmc.data.Database;

/**
 * Data access object for customer.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class CustomerDao extends Dao {

	public static final String TABLE_NAME = Database.CUSTOMER_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * @param database
	 *            the database object to set
	 * @param tableName
	 *            the table name to set
	 */
	public CustomerDao(Database database) {
		super(database, TABLE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * @see a00943900.dao.Dao#create()
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + tableName);
		String sql = String.format(
				"create table %s(" // tableName
						+ "%s %s, " // 1 id
						+ "%s %s(%d), " // 2 firstName
						+ "%s %s(%d), " // 3 lastName
						+ "%s %s(%d), " // 4 streetName
						+ "%s %s(%d), " // 5 city
						+ "%s %s(%d), " // 6 postalCode
						+ "%s %s(%d), " // 7 phoneNumber
						+ "%s %s(%d), " // 8 emailAddress
						+ "%s %s, " // 9 joinDate
						+ "primary key (%s) )", //
				tableName, //
				Fields.ID.name, Fields.ID.type, // 1
				Fields.FIRST_NAME.name, Fields.FIRST_NAME.type, Fields.FIRST_NAME.length, // 2
				Fields.LAST_NAME.name, Fields.LAST_NAME.type, Fields.LAST_NAME.length, // 3
				Fields.STREET_NAME.name, Fields.STREET_NAME.type, Fields.STREET_NAME.length, // 4
				Fields.CITY.name, Fields.CITY.type, Fields.CITY.length, // 5
				Fields.POSTAL_CODE.name, Fields.POSTAL_CODE.type, Fields.POSTAL_CODE.length, // 6
				Fields.PHONE_NUMBER.name, Fields.PHONE_NUMBER.type, Fields.PHONE_NUMBER.length, // 7
				Fields.EMAIL_ADDRESS.name, Fields.EMAIL_ADDRESS.type, Fields.EMAIL_ADDRESS.length, // 8
				Fields.JOIN_DATE.name, Fields.JOIN_DATE.type, // 9
				Fields.ID.name); //
		super.create(sql);
	}

	public void add(Customer customer) throws SQLException {
		String sql = String.format(
				"insert into %s values(" // tableName
						+ "?, " // 1 id
						+ "?, " // 2 firstName
						+ "?, " // 3 lastName
						+ "?, " // 4 streetName
						+ "?, " // 5 city
						+ "?, " // 6 postalCode
						+ "?, " // 7 phoneNumber
						+ "?, " // 8 emailAddress
						+ "?)", // 9 joinDate
				tableName);
		int result = executeUpdate(sql, //
				customer.getId(), // 1
				customer.getFirstName(), // 2
				customer.getLastName(), // 3
				customer.getStreetName(), // 4
				customer.getCity(), // 5
				customer.getPostalCode(), // 6
				customer.getPhoneNumber(), // 7
				customer.getEmailAddress(), // 8
				customer.getJoinDate()); // 9
		LOG.debug(String.format("Adding %s was %s", customer, result != 0 ? "successful" : "unsuccessful"));
	}

	public Customer getCustomer(String id) throws SQLException, ApplicationException {
		PreparedStatement statement = null;
		Connection connection;
		Customer customer = null;
		try {
			connection = database.getConnection();
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", tableName, Fields.ID.name());
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			LOG.debug(statement.toString());
			ResultSet resultSet = statement.executeQuery();

			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new ApplicationException(String.format("Expected one result, got %d", count));
				}
				customer = new Customer.Builder(resultSet.getLong(Fields.ID.name), resultSet.getString(Fields.PHONE_NUMBER.name)) //
						.setFirstName(resultSet.getString(Fields.FIRST_NAME.name)) //
						.setLastName(resultSet.getString(Fields.LAST_NAME.name)) //
						.setStreetName(resultSet.getString(Fields.STREET_NAME.name)) //
						.setCity(resultSet.getString(Fields.CITY.name)) //
						.setPostalCode(resultSet.getString(Fields.POSTAL_CODE.name)) //
						.setEmailAddress(resultSet.getString(Fields.EMAIL_ADDRESS.name)) //
						.setJoinDate(resultSet.getTimestamp(Fields.JOIN_DATE.name).toLocalDateTime().toLocalDate()).build();
			}
		} finally {
			close(statement);
			LOG.debug(customer.toString() + " retrieved.");
		}
		return customer;
	}

	public void update(Customer customer) throws SQLException {
		String sql = String.format(
				"UPDATE %s set " // tableName
						+ "%s=?, " // 1 id
						+ "%s=?, " // 2 firstName
						+ "%s=?, " // 3 lastName
						+ "%s=?, " // 4 streetName
						+ "%s=?, " // 5 city
						+ "%s=?, " // 6 postalCode
						+ "%s=?, " // 7 phoneNumber
						+ "%s=?, " // 8 emailAddress
						+ "%s=? " // 9 joinDate
						+ "WHERE %s=?",
				tableName, //
				Fields.ID.name, // 1
				Fields.FIRST_NAME.name, // 2
				Fields.LAST_NAME.name, // 3
				Fields.STREET_NAME.name, // 4
				Fields.CITY.name, // 5
				Fields.POSTAL_CODE.name, // 6
				Fields.PHONE_NUMBER.name, // 7
				Fields.EMAIL_ADDRESS.name, // 8
				Fields.JOIN_DATE.name, // 9
				Fields.ID.name); //
		int result = executeUpdate(sql, //
				customer.getId(), // 1
				customer.getFirstName(), // 2
				customer.getLastName(), // 3
				customer.getStreetName(), // 4
				customer.getCity(), // 5
				customer.getPostalCode(), // 6
				customer.getPhoneNumber(), // 7
				customer.getEmailAddress(), // 8
				customer.getJoinDate(), // 9
				customer.getId()); //
		LOG.debug(String.format("Updating %s was %s", customer, result != 0 ? "successful" : "unsuccessful"));
	}

	public void delete(Customer customer) throws SQLException {
		Connection connection;
		PreparedStatement statement = null;
		try {
			connection = database.getConnection();
			String sql = String.format("DELETE from %s WHERE %s=?", tableName, Fields.ID.name);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, customer.getId());
			LOG.debug(statement.toString());
			statement.executeUpdate();
		} finally {
			close(statement);
		}
		LOG.debug("Deleted " + customer.toString() + " from " + tableName);
	}

	public List<String> getIds() throws SQLException {
		Connection connection;
		Statement statement = null;
		List<String> ids = new ArrayList<>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT %s FROM %s", Fields.ID.name, tableName);
			LOG.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ids.add(resultSet.getString(Fields.ID.column));
			}

		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d customer IDs from the database", ids.size()));

		return ids;
	}

	public enum Fields {
		ID("id", "BIGINT", -1, 1), //
		FIRST_NAME("firstName", "VARCHAR", 20, 2), //
		LAST_NAME("lastName", "VARCHAR", 20, 3), //
		STREET_NAME("streetName", "VARCHAR", 30, 4), //
		CITY("city", "VARCHAR", 20, 5), //
		POSTAL_CODE("postalCode", "VARCHAR", 7, 6), //
		PHONE_NUMBER("phoneNumber", "VARCHAR", 15, 7), //
		EMAIL_ADDRESS("emailAddress", "VARCHAR", 30, 8), //
		JOIN_DATE("joinDate", "DATE", -1, 9);

		private final String name;
		private final String type;
		private final int length;
		private final int column;

		Fields(String name, String type, int length, int column) {
			this.name = name;
			this.type = type;
			this.length = length;
			this.column = column;
		}
	}
}
