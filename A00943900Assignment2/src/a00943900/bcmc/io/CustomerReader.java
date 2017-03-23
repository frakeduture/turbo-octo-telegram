/**
 * Project: Lab5
 * File: CustomerReader.java
 * Date: Jan 20, 2017
 * Time: 8:23:57 PM
 */
package a00943900.bcmc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00943900.bcmc.ApplicationException;
import a00943900.bcmc.DataFiles;
import a00943900.bcmc.data.Customer;
import a00943900.bcmc.util.Validator;

/**
 * Reads customer data file and splits it into customer data.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class CustomerReader {

	private static final Logger LOG = LogManager.getLogger();

	public static final String JOIN_DATE_PATTERN = "yyyyMMdd";
	public static final String FILENAME = DataFiles.CUSTOMERS.getFileName();
	public static final String ELEMENT_DELIMITER = "\\|";

	/**
	 * Default constructor. Private to prevent instantiation.
	 */
	private CustomerReader() {

	}

	/**
	 * Reads inputed customer data file and stores the elements in a collection of customer objects.
	 * 
	 * @return a list containing the split customer data
	 * @throws ApplicationException
	 *             if customer data is invalid or could not be found
	 */
	public static List<Customer> read() throws ApplicationException {
		File customerDataFile = new File(FILENAME);
		BufferedReader reader = null;
		List<Customer> customers = new ArrayList<>();
		LOG.debug("Reading " + FILENAME + " at " + customerDataFile.getAbsolutePath());
		try {
			reader = new BufferedReader(new FileReader(customerDataFile));
			reader.readLine(); // skip first line (column header)
			String line = reader.readLine();
			while (line != null) {
				Customer customer = readCustomerString(line);
				customers.add(customer);
				LOG.debug("Added " + customer.toString() + "to collection");
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
		return customers;
	}

	/*
	 * Split a string of a single customer's data into its elements and stores them in a customer object
	 * @param row the customer's data elements
	 * @return a customer object
	 * @throws ApplicationException if customer data is invalid
	 */
	private static Customer readCustomerString(String row) throws ApplicationException {
		String[] customerData = row.split(ELEMENT_DELIMITER);
		if (customerData.length != Customer.ATTRIBUTE_COUNT) {
			String message = String.format("Expected %d but got %d: %s", Customer.ATTRIBUTE_COUNT, customerData.length,
					Arrays.toString(customerData));
			throw new ApplicationException(message);
		}
		LOG.debug(Arrays.toString(customerData));

		int index = 0;
		long id = Integer.parseInt(customerData[index++]);
		String firstName = customerData[index++];
		String lastName = customerData[index++];
		String streetName = customerData[index++];
		String city = customerData[index++];
		String postalCode = customerData[index++];
		String phoneNumber = customerData[index++];
		String emailAddress = customerData[index++];

		if (Validator.validateEmail(emailAddress) == false) {
			throw new ApplicationException("Invalid email address: " + emailAddress);
		}

		DateTimeFormatter joinDateFormat = DateTimeFormatter.ofPattern(JOIN_DATE_PATTERN);
		LocalDate joinDate;
		try {
			joinDate = LocalDate.parse(customerData[index++], joinDateFormat);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}

		Customer customer = new Customer.Builder(id, phoneNumber).setFirstName(firstName).setLastName(lastName).setStreetName(streetName)
				.setCity(city).setPostalCode(postalCode).setEmailAddress(emailAddress).setJoinDate(joinDate).build();
		return customer;
	}
}
