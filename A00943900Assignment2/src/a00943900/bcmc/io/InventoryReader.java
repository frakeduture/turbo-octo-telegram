/**
 * Project: A00943900Assignment1
 * File: InventoryReader.java
 * Date: Feb 15, 2017
 * Time: 7:29:09 PM
 */
package a00943900.bcmc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00943900.bcmc.ApplicationException;
import a00943900.bcmc.DataFiles;
import a00943900.bcmc.data.Inventory;

/**
 * Reads inventory file and splits it into inventory data
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class InventoryReader {

	private static final Logger LOG = LogManager.getLogger();

	public static final String FILENAME = DataFiles.INVENTORY.getFileName();
	public static final String ELEMENT_DELIMITER = "\\|";

	/**
	 * Default constructor. Private to prevent instantiation
	 */
	private InventoryReader() {

	}

	/**
	 * Reads inputed inventory data file and stores the elements in a collection of inventory objects,
	 * 
	 * @return a list containing the split inventory data
	 * @throws ApplicationException
	 *             if inventory data is invalid or file could not be found
	 */
	public static List<Inventory> read() throws ApplicationException {
		File inventoryDataFile = new File(FILENAME);
		BufferedReader reader = null;
		List<Inventory> inventory = new ArrayList<>();
		LOG.debug("Reading " + FILENAME + " at " + inventoryDataFile.getAbsolutePath());

		try {
			reader = new BufferedReader(new FileReader(inventoryDataFile));
			reader.readLine();
			String line = reader.readLine();
			while (line != null) {
				Inventory item = readInventoryString(line);
				inventory.add(item);
				LOG.debug("Added " + item.toString() + "to collection");
				line = reader.readLine();
			}
		} catch (IOException e) {
			throw new ApplicationException(e.getMessage(), e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				throw new ApplicationException(e.getMessage(), e);
			}
		}
		return inventory;
	}

	/*
	 * Split a string of a single inventory item's data into its elements and stores them in a inventory object
	 * @param row the inventory item's data elements
	 * @return an inventory object
	 * @throws ApplicationException if inventory data is invalid
	 */
	private static Inventory readInventoryString(String row) throws ApplicationException {
		String[] inventoryData = row.split(ELEMENT_DELIMITER);
		if (inventoryData.length != Inventory.ATTRIBUTE_COUNT) {
			String message = String.format("Expected %d but got %d: %s", Inventory.ATTRIBUTE_COUNT, inventoryData.length,
					Arrays.toString(inventoryData));
			throw new ApplicationException(message);
		}
		LOG.debug(Arrays.toString(inventoryData));
		int index = 0;
		String motorcycleId = inventoryData[index++];
		String description = inventoryData[index++];
		String partNumber = inventoryData[index++];
		long price = Integer.parseInt(inventoryData[index++]);
		long quantity = Integer.parseInt(inventoryData[index++]);

		Inventory item = new Inventory.Builder(motorcycleId, partNumber).setDescription(description).setPrice(price).setQuantity(quantity).build();
		return item;
	}

}
