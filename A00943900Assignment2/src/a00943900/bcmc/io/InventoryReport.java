/**
 * Project: A00943900Assignment1
 * File: InventoryReport.java
 * Date: Jan 20, 2017
 * Time: 8:56:34 PM
 */
package a00943900.bcmc.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import a00943900.bcmc.DataFiles;
import a00943900.bcmc.data.Inventory;

/**
 * Creates formatted inventory reports.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class InventoryReport {

	public static final String DASHES = "-------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%-30s %-30s %-12s %6s %9s";
	public static final String INVENTORY_FORMAT = "%-30s %-30s %-12s %6.2f %9d";

	/**
	 * Default Constructor. Private to prevent instantiation
	 */
	private InventoryReport() {

	}

	/**
	 * Prints a formatted report of the inventory data to the console.
	 * 
	 * @param customers
	 *            a List of the customer data
	 */
	public static void printInventoryReport(List<Inventory> inventory) {
		System.out.print(getReportHeader());
		for (Inventory item : inventory) {
			System.out.println(getFormattedRow(item));
		}
	}

	/**
	 * Writes a formatted report of the inventory data to a text file
	 * 
	 * @param inventory
	 *            a List of the inventory data
	 * @throws IOException
	 *             if output file could not be generated
	 */
	public static void writeInventoryReport(List<Inventory> inventory) throws IOException {
		BufferedWriter outputStream = null;

		try {
			outputStream = new BufferedWriter(new FileWriter(DataFiles.INVENTORY.getReportName()));
			StringBuilder header = new StringBuilder();
			header.append("Inventory Report").append(System.lineSeparator());
			header.append(DASHES).append(System.lineSeparator());
			header.append(String.format(HEADER_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity")).append(System.lineSeparator());
			header.append(DASHES).append(System.lineSeparator());
			outputStream.write(header.toString());
			for (Inventory item : inventory) {
				outputStream.write(getFormattedRow(item));
				outputStream.newLine();
			}
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

	/*
	 * Gets the header for the formatted inventory report
	 * @return the report header string
	 */
	private static String getReportHeader() {
		StringBuilder header = new StringBuilder();
		header.append("Inventory Report").append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		header.append(String.format(HEADER_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity")).append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		return header.toString();
	}

	/*
	 * Gets the inventory data as a formatted string
	 * @param item the inventory item data to be formatted
	 * @return the formatted inventory data
	 */
	private static String getFormattedRow(Inventory item) {

		return String.format(INVENTORY_FORMAT, item.getMotorcycleId(), item.getDescription(), item.getPartNumber(), item.getPrice() / 100.0,
				item.getQuantity());
	}

	// private static String getInventoryValue(List<Inventory> inventory) {
	// long totalInCents = 0;
	// for (Inventory item : inventory) {
	// totalInCents += (item.getPrice() * item.getQuantity());
	// }
	// return NumberFormat.getCurrencyInstance().format(totalInCents / 100.0);
	// }
	//
}
