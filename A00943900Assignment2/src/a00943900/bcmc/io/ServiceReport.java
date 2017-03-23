/**
 * Project: A00943900Assignment1
 * File: MotorcycleReport.java
 * Date: Jan 20, 2017
 * Time: 8:56:34 PM
 */
package a00943900.bcmc.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a00943900.bcmc.DataFiles;
import a00943900.bcmc.data.Customer;
import a00943900.bcmc.data.Motorcycle;

/**
 * Creates formatted service reports.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class ServiceReport {

	public static final String DASHES = "----------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%-12s %-12s %-20s %-20s %-6s %7s";
	public static final String SERVICE_FORMAT = "%-12s %-12s %-20s %-20s %-6d %,7d";

	/**
	 * Default Constructor. Private to prevent instantiation
	 */
	private ServiceReport() {

	}

	/**
	 * Prints a formatted report of the service data to the console.
	 * 
	 * @param customers
	 *            a List of the customer data
	 */
	public static void printServiceReport(List<Motorcycle> motorcycles, List<Customer> customers) {
		Map<Long, Customer> customerMap = new HashMap<>();
		for (Customer c : customers) {
			customerMap.put(c.getId(), c);
		}
		System.out.print(getReportHeader());
		for (Motorcycle motorcycle : motorcycles) {
			System.out.println(getFormattedRow(motorcycle, customerMap.get(motorcycle.getCustomerId())));
		}
	}

	/**
	 * Writes a formatted report of the motorcycle data to a text file
	 * 
	 * @param motorcycles
	 *            a List of the motorcycle data
	 * @throws IOException
	 *             if output file could not be generated
	 */
	public static void writeServiceReport(List<Motorcycle> motorcycles, List<Customer> customers) throws IOException {
		BufferedWriter outputStream = null;
		Map<Long, Customer> customerMap = new HashMap<>();
		for (Customer c : customers) {
			customerMap.put(c.getId(), c);
		}
		try {
			outputStream = new BufferedWriter(new FileWriter(DataFiles.MOTORCYCLES.getReportName()));
			outputStream.write(getReportHeader());
			for (Motorcycle motorcycle : motorcycles) {
				outputStream.write(getFormattedRow(motorcycle, customerMap.get(motorcycle.getCustomerId())));
				outputStream.newLine();
			}
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

	/*
	 * Gets the header for the formatted motorcycle report
	 * @return the report header string
	 */
	private static String getReportHeader() {
		StringBuilder header = new StringBuilder();
		header.append("Service Report").append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		header.append(String.format(HEADER_FORMAT, "First Name", "Last Name", "Make", "Model", "Year", "Mileage")).append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		return header.toString();
	}

	/*
	 * Gets the motorcycle data as a formatted string
	 * @param item the motorcycle item data to be formatted
	 * @return the formatted motorcycle data
	 */
	private static String getFormattedRow(Motorcycle motorcycle, Customer customer) {
		return String.format(SERVICE_FORMAT, customer.getFirstName(), customer.getLastName(), motorcycle.getMake(), motorcycle.getModel(),
				motorcycle.getYear(), motorcycle.getMileage());
	}

}
