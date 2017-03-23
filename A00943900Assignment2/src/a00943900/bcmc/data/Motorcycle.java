/**
 * Project: A00943900Assignment1
 * File: Motorcycle.java
 * Date: Feb 14, 2017
 * Time: 6:20:29 PM
 */
package a00943900.bcmc.data;

/**
 * A motorcycle in for service.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class Motorcycle {

	public static final int ATTRIBUTE_COUNT = 7;

	private long id;
	private String make;
	private String model;
	private int year;
	private String serialNumber;
	private long mileage;
	private long customerId;

	/**
	 * Builder class for Motorcycle
	 * 
	 * @author Thomas Ngo, A00943900
	 *
	 */
	public static class Builder {

		private long id;
		private String make;
		private String model;
		private int year;
		private String serialNumber;
		private long mileage;
		private long customerId;

		/**
		 * Builder constructor.
		 */
		public Builder() {

		}

		/**
		 * Set the id.
		 *
		 * @param id
		 *            the id to set
		 * @return the Motorcycle Builder
		 */
		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		/**
		 * Set the make.
		 *
		 * @param make
		 *            the make to set
		 * @return the Motorcycle Builder
		 */
		public Builder setMake(String make) {
			this.make = make;
			return this;
		}

		/**
		 * Set the model.
		 *
		 * @param model
		 *            the model to set
		 * @return the Motorcycle Builder
		 */
		public Builder setModel(String model) {
			this.model = model;
			return this;
		}

		/**
		 * Set the year.
		 *
		 * @param year
		 *            the year to set
		 * @return the Motorcycle Builder
		 */
		public Builder setYear(int year) {
			this.year = year;
			return this;
		}

		/**
		 * Set the serial number.
		 *
		 * @param serialNumber
		 *            the serialNumber to set
		 * @return the Motorcycle Builder
		 */
		public Builder setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}

		/**
		 * Set the mileage.
		 *
		 * @param mileage
		 *            the mileage to set
		 * @return the Motorcycle Builder
		 */
		public Builder setMileage(long mileage) {
			this.mileage = mileage;
			return this;
		}

		/**
		 * Set the customerId.
		 *
		 * @param customerId
		 *            the customerId to set
		 * @return the Motorcycle Builder
		 */
		public Builder setCustomerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public Motorcycle build() {
			return new Motorcycle(this);
		}

	}

	/**
	 * Default constructor. Private to prevent instantiation.
	 */
	private Motorcycle(Builder builder) {
		this.id = builder.id;
		this.make = builder.make;
		this.model = builder.model;
		this.year = builder.year;
		this.serialNumber = builder.serialNumber;
		this.mileage = builder.mileage;
		this.customerId = builder.customerId;
	}

	/**
	 * Get the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get the make.
	 *
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Set the make.
	 *
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Get the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set the model.
	 *
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year.
	 *
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get the serial number.
	 *
	 * @return the serial number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Set the serial number.
	 *
	 * @param serialNumber
	 *            the serial number to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Get the mileage.
	 *
	 * @return the mileage
	 */
	public long getMileage() {
		return mileage;
	}

	/**
	 * Set the mileage.
	 *
	 * @param mileage
	 *            the mileage to set
	 */
	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	/**
	 * Get the customer ID.
	 *
	 * @return the customer ID
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * Set the customer ID.
	 *
	 * @param customerId
	 *            the customer ID to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Motorcycle [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", serialNumber=" + serialNumber + ", mileage="
				+ mileage + ", customerId=" + customerId + "]";
	}

}
