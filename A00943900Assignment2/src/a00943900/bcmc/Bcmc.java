/**
 * Project: A00943900Assignment1
 * File: Bcmc.java
 * Date: Jan 20, 2017
 * Time: 8:07:41 PM
 */
package a00943900.bcmc;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00943900.bcmc.data.Customer;
import a00943900.bcmc.data.Inventory;
import a00943900.bcmc.data.Motorcycle;
import a00943900.bcmc.io.CustomerReader;
import a00943900.bcmc.io.CustomerReport;
import a00943900.bcmc.io.InventoryReader;
import a00943900.bcmc.io.InventoryReport;
import a00943900.bcmc.io.MotorcycleReader;
import a00943900.bcmc.io.ServiceReport;
import a00943900.bcmc.util.CompareByDescription;
import a00943900.bcmc.util.CompareByJoinDate;
import a00943900.bcmc.util.CompareByQuantity;

/**
 * This program is a command line program which reads customer data, creates Customer objects, adds them to an array, and generates a simple customer
 * report.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class Bcmc {

	public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger();

	private List<Customer> customers;
	private List<Inventory> inventory;
	private List<Motorcycle> motorcycles;

	/**
	 * Main driver for Bcmc.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		LOG.info("Start time: " + startTime);

		try {
			BcmcOptions.process(args);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			LOG.error(e.getStackTrace());
		}

		LOG.info("Data files located");
		CommandLine commandLine = BcmcOptions.getCommandLine();
		Bcmc bcmc = new Bcmc();
		LOG.info("Created Bcmc");
		bcmc.run(commandLine);
		Instant endTime = Instant.now();
		LOG.info("End time: " + endTime);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
		System.exit(0);

	}

	/**
	 * Bcmc Constructor.
	 * 
	 * @param customerFile
	 *            the file containing the customer data
	 */
	public Bcmc() {

	}

	/*
	 * populates customer data and generates a report
	 */
	private void run(CommandLine commandLine) {
		try {
			readDataFiles();
			if (commandLine.hasOption(BcmcOptions.Value.MAKE.getLongOption())) {
				Iterator<Inventory> it = inventory.iterator();
				while (it.hasNext()) {
					if (!it.next().getMotorcycleId().startsWith(commandLine.getOptionValue(BcmcOptions.Value.MAKE.getOption()))) {
						it.remove();
					}
				}
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_JOIN_DATE.getOption())) {
				Collections.sort(customers, new CompareByJoinDate());
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_JOIN_DATE.getOption())
					&& commandLine.hasOption(BcmcOptions.Value.DESCENDING.getOption())) {
				Collections.sort(customers, Collections.reverseOrder(new CompareByJoinDate()));
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_DESCRIPTION.getOption())) {
				Collections.sort(inventory, new CompareByDescription());
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_DESCRIPTION.getOption())
					&& commandLine.hasOption(BcmcOptions.Value.DESCENDING.getOption())) {
				Collections.sort(inventory, Collections.reverseOrder(new CompareByDescription()));
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_COUNT.getOption())) {
				Collections.sort(inventory, new CompareByQuantity());
			}
			if (commandLine.hasOption(BcmcOptions.Value.BY_COUNT.getOption()) && commandLine.hasOption(BcmcOptions.Value.DESCENDING.getOption())) {
				Collections.sort(inventory, Collections.reverseOrder(new CompareByQuantity()));
			}
			if (commandLine.hasOption(BcmcOptions.Value.SERVICE.getOption())) {
				ServiceReport.writeServiceReport(motorcycles, customers);
				ServiceReport.printServiceReport(motorcycles, customers);
			}
			if (commandLine.hasOption(BcmcOptions.Value.INVENTORY.getOption())) {
				InventoryReport.writeInventoryReport(inventory);
				InventoryReport.printInventoryReport(inventory);
			}
			if (commandLine.hasOption(BcmcOptions.Value.CUSTOMERS.getOption())) {
				CustomerReport.writeCustomerReport(customers);
				CustomerReport.printCustomerReport(customers);
			}

			if (commandLine.getOptions().length == 0 && commandLine.getArgs().length == 0) {
				CustomerReport.writeCustomerReport(customers);
				InventoryReport.writeInventoryReport(inventory);
				ServiceReport.writeServiceReport(motorcycles, customers);
				CustomerReport.printCustomerReport(customers);
				InventoryReport.printInventoryReport(inventory);
				ServiceReport.printServiceReport(motorcycles, customers);
			}

		} catch (ApplicationException | IOException e) {
			LOG.error(e.getMessage());
			LOG.debug(e.getMessage(), e);
		}
	}

	private void readDataFiles() throws ApplicationException {
		customers = CustomerReader.read();
		inventory = InventoryReader.read();
		motorcycles = MotorcycleReader.read();
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.err.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

}
