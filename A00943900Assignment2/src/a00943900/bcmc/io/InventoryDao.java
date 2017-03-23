/**
 * Project: A00943900Assignment2
 * File: InventoryDao.java
 * Date: Mar 17, 2017
 * Time: 3:56:08 PM
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
import a00943900.bcmc.data.Inventory;

/**
 * Data access object for Inventory items.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class InventoryDao extends Dao {

	public static final String TABLE_NAME = Database.INVENTORY_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Inventory data access object constructor
	 * 
	 * @param database
	 *            the database object to set
	 */
	public InventoryDao(Database database) {
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
						+ "%s %s, " // 1 motorcycleId
						+ "%s %s(%d), " // 2 description
						+ "%s %s(%d), " // 3 partNumber
						+ "%s %s, " // 4 price
						+ "%s %s, " // 5 quantity
						+ "PRIMARY KEY (%s, %s) )", //
				tableName, //
				Fields.MOTORCYCLE_ID.name, Fields.MOTORCYCLE_ID.type, // 1
				Fields.DESCRIPTION.name, Fields.DESCRIPTION.type, Fields.DESCRIPTION.length, // 2
				Fields.PART_NUMBER.name, Fields.PART_NUMBER.type, Fields.PART_NUMBER.length, // 3
				Fields.PRICE.name, Fields.PRICE.type, Fields.PRICE.length, // 4
				Fields.QUANTITY.name, Fields.QUANTITY.type, Fields.QUANTITY.length, // 5
				Fields.MOTORCYCLE_ID.name, Fields.PART_NUMBER.name);
		super.create(sql);
	}

	/**
	 * Insert an item into the inventory table.
	 * 
	 * @param item
	 *            the item to insert
	 * @throws SQLException
	 */
	public void add(Inventory item) throws SQLException {
		String sql = String.format(
				"INSERT INTO %s VALUES(" // tableName
						+ "?, " // 1 motorcycleId
						+ "?, " // 2 description
						+ "?, " // 3 partNumber
						+ "?, " // 4 price
						+ "?)", // 5 quantity
				tableName);
		int result = executeUpdate(sql, //
				item.getMotorcycleId(), // 1
				item.getDescription(), // 2
				item.getPartNumber(), // 3
				item.getPrice(), // 4
				item.getQuantity()); // 5
		LOG.debug(String.format("Adding %s was %s", item, result != 0 ? "successful" : "unsuccessful"));
	}

	/**
	 * Retrieve all rows from the inventory table as a list of inventory objects
	 * 
	 * @return a list of Inventory items
	 * @throws SQLException
	 */
	public List<Inventory> getAll() throws SQLException {
		Statement statement = null;
		List<Inventory> inventory = new ArrayList<>();
		String sql = String.format("SELECT * FROM %s", tableName);
		LOG.debug(sql);
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Inventory item = new Inventory.Builder(resultSet.getString(Fields.MOTORCYCLE_ID.name), resultSet.getString(Fields.PART_NUMBER.name)) //
						.setDescription(resultSet.getString(Fields.DESCRIPTION.name)) //
						.setPrice(resultSet.getLong(Fields.PRICE.name)) //
						.setQuantity(resultSet.getLong(Fields.QUANTITY.name)) //
						.build();
				inventory.add(item);
			}
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d inventory items from %s", inventory.size(), tableName));
		return inventory;
	}

	/**
	 * Update row in inventory table with given inventory item.
	 * 
	 * @param inventory
	 *            the item to be updated
	 * @throws SQLException
	 */
	public void update(Inventory item) throws SQLException {
		String sql = String.format(
				"UPDATE %s set " // tableName
						+ "%s=?, " // 1 motorcycleId
						+ "%s=?, " // 2 description
						+ "%s=?, " // 3 partNumber
						+ "%s=?, " // 4 price
						+ "%s=?, " // 5 quantity
						+ "WHERE (%s=?) AND (%s=?)", //
				tableName, //
				Fields.MOTORCYCLE_ID.name, // 1
				Fields.DESCRIPTION.name, // 2
				Fields.PART_NUMBER.name, // 3
				Fields.PRICE.name, // 4
				Fields.QUANTITY.name, // 5
				Fields.MOTORCYCLE_ID.name, Fields.PART_NUMBER.name); //
		int result = executeUpdate(sql, //
				item.getMotorcycleId(), // 1
				item.getDescription(), // 2
				item.getPartNumber(), // 3
				item.getPrice(), // 4
				item.getQuantity(), // 5
				item.getMotorcycleId(), item.getPartNumber()); //
		LOG.debug(String.format("Updating %s was %s", item, result != 0 ? "successful" : "unsuccessful"));
	}

	/**
	 * Delete row from inventory table containing given inventory item.
	 * 
	 * @param item
	 *            the inventory item to be deleted
	 * @throws SQLException
	 */
	public void delete(Inventory item) throws SQLException {
		String sql = String.format(
				"DELETE FROM %s " // tabelName
						+ "WHERE (%s=?) " // motorcyleId
						+ "AND (%s=?)", // partNumber
				tableName, // tableName
				Fields.MOTORCYCLE_ID.name, Fields.PART_NUMBER.name);
		int result = executeUpdate(sql, //
				item.getMotorcycleId(), //
				item.getPartNumber()); //
		LOG.debug(String.format("Deleting %s was %s", item, result != 0 ? "successful" : "unsuccessful"));
	}

	public enum Fields {

		MOTORCYCLE_ID("motorcycleId", "VARCHAR", 40), //
		DESCRIPTION("description", "VARCHAR", 40), //
		PART_NUMBER("partNumber", "VARCHAR", 15), //
		PRICE("price", "BIGINT", -1), //
		QUANTITY("quantity", "BIGINT", -1); //

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
