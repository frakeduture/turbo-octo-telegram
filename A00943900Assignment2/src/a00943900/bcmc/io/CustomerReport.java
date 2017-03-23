/**
 * Project: A00943900Assignment1
 * File: CustomerReport.java
 * Date: Jan 20, 2017
 * Time: 8:56:34 PM
 */
package a00943900.bcmc.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import a00943900.bcmc.DataFiles;
import a00943900.bcmc.data.Customer;

/**
 * Creates formatted customer reports.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class CustomerReport {

	public static final String DASHES = "----------------------------------------" + "----------------------------------------"
			+ "----------------------------------------" + "--------------";
	public static final String HEADER_FORMAT = "%4s %-6s %-11s %-11s %-23s %-9s %-11s %-14s %-25s %11s";
	public static final String CUSTOMER_FORMAT = "%06d %-11s %-11s %-23s %-9s %-11s %-14s %-25s %-11s";
	public static final String DATE_FORMAT = "MMM dd yyyy";

	/**
	 * Default Constructor. Private to prevent instantiation
	 */
	private CustomerReport() {

	}

	/**
	 * Prints a formatted report of the customer data to the console.
	 * 
	 * @param customers
	 *            a List of the customer data
	 */
	public static void printCustomerReport(List<Customer> customers) {
		System.out.print(getReportHeader());
		int i = 0;
		for (Customer customer : customers) {
			System.out.format("%4s ", ++i);
			System.out.println(getFormattedRow(customer));
		}
	}

	/**
	 * Writes a formatted report of the customer data to a text file
	 * 
	 * @param customers
	 *            a List of the customer data
	 * @throws IOException
	 *             if output file could not be generated
	 */
	public static void writeCustomerReport(List<Customer> customers) throws IOException {
		BufferedWriter outputStream = null;

		try {
			outputStream = new BufferedWriter(new FileWriter(DataFiles.CUSTOMERS.getReportName()));
			outputStream.write(getReportHeader());
			int i = 0;
			for (Customer customer : customers) {
				outputStream.write(String.format("%4s ", ++i));
				outputStream.write(getFormattedRow(customer));
				outputStream.newLine();
			}
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

	/*
	 * Gets the header for the formatted customer report
	 * @return the report header string
	 */
	private static String getReportHeader() {
		StringBuilder header = new StringBuilder();
		header.append("Customers Report").append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		header.append(
				String.format(HEADER_FORMAT, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date"))
				.append(System.lineSeparator());
		header.append(DASHES).append(System.lineSeparator());
		return header.toString();
	}

	/*
	 * Gets the customer data as a formatted string
	 * @param customer the customer data to be formatted
	 * @return the formatted customer data
	 */
	private static String getFormattedRow(Customer customer) {
		DateTimeFormatter joinDateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return String.format(CUSTOMER_FORMAT, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreetName(),
				customer.getCity(), customer.getPostalCode(), customer.getPhoneNumber(), customer.getEmailAddress(),
				customer.getJoinDate().format(joinDateFormat));
	}

}
