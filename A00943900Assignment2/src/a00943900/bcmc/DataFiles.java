/**
 * Project: A00943900Assignment1
 * File: DataFiles.java
 * Date: Feb 16, 2017
 * Time: 4:21:07 PM
 */
package a00943900.bcmc;

/**
 * @author Thomas Ngo, A00943900
 *
 */
public enum DataFiles {
	CUSTOMERS("customers.dat", "customers_report.txt", 9), INVENTORY("inventory.dat", "inventory_report.txt", 5), MOTORCYCLES("motorcycles.dat",
			"service_report.txt", 7);

	private String fileName;
	private String reportName;
	private int numberOfElements;
	private static final String ELEMENT_DELIMITER = "\\|";

	DataFiles(String fileName, String reportName, int numberOfElements) {
		this.fileName = fileName;
		this.reportName = reportName;
		this.numberOfElements = numberOfElements;
	}

	public String getFileName() {
		return fileName;
	}

	public String getReportName() {
		return reportName;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public String getElementDelimiter() {
		return ELEMENT_DELIMITER;
	}
}
