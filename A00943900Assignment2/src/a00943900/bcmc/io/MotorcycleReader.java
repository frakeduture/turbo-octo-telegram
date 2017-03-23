/**
 * Project: A00943900Assignment1
 * File: MotorcycleReader.java
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
import a00943900.bcmc.data.Motorcycle;

/**
 * Reads inputed motorcycle file and splits it into motorcycle data
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class MotorcycleReader {

	private static final Logger LOG = LogManager.getLogger();

	public static final String FILENAME = DataFiles.MOTORCYCLES.getFileName();
	public static final String ELEMENT_DELIMITER = "\\|";

	/**
	 * Default constructor. Private to prevent instantiation
	 */
	private MotorcycleReader() {

	}

	/**
	 * Reads inputed motorcycle data file and stores the elements in a collection of motorcycle objects,
	 * 
	 * @return a list containing the split motorcycle data
	 * @throws ApplicationException
	 *             if motorcycle data is invalid or file could not be read
	 */
	public static List<Motorcycle> read() throws ApplicationException {
		File motorcycleDataFile = new File(FILENAME);
		BufferedReader reader = null;
		List<Motorcycle> motorcycles = new ArrayList<>();
		LOG.debug("Reading " + FILENAME + " at " + motorcycleDataFile.getAbsolutePath());
		try {
			reader = new BufferedReader(new FileReader(motorcycleDataFile));
			reader.readLine();
			String line = reader.readLine();
			while (line != null) {
				Motorcycle motorcycle = readMotorcycleString(line);
				motorcycles.add(motorcycle);
				LOG.debug("Added " + motorcycle.toString() + "to collection");
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
		return motorcycles;
	}

	/*
	 * Split a string of a single motorcycle's data into its elements and stores them in a motorcycle object
	 * @param row the motorcycle's data elements
	 * @return an motorcycle object
	 * @throws ApplicationException if motorcycle data is invalid
	 */
	private static Motorcycle readMotorcycleString(String row) throws ApplicationException {
		String[] motorcycleData = row.split(ELEMENT_DELIMITER);
		if (motorcycleData.length != Motorcycle.ATTRIBUTE_COUNT) {
			String message = String.format("Expected %d but got %d: %s", Motorcycle.ATTRIBUTE_COUNT, motorcycleData.length,
					Arrays.toString(motorcycleData));
			throw new ApplicationException(message);
		}
		LOG.debug(Arrays.toString(motorcycleData));
		int index = 0;
		long id = Integer.parseInt(motorcycleData[index++]);
		String make = motorcycleData[index++];
		String model = motorcycleData[index++];
		int year = Integer.parseInt(motorcycleData[index++]);
		String serialNumber = motorcycleData[index++];
		long mileage = Integer.parseInt(motorcycleData[index++]);
		long customerId = Integer.parseInt(motorcycleData[index++]);

		Motorcycle motorcycle = new Motorcycle.Builder().setId(id).setMake(make).setModel(model).setYear(year).setSerialNumber(serialNumber)
				.setMileage(mileage).setCustomerId(customerId).build();
		return motorcycle;
	}

}
