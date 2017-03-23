/**
 * Project: A00943900Assignment1
 * File: Inventory.java
 * Date: Feb 14, 2017
 * Time: 12:04:02 PM
 */
package a00943900.bcmc.data;

/**
 * A part in the BCMC inventory.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class Inventory {

	public static final int ATTRIBUTE_COUNT = 5;

	private String motorcycleId;
	private String description;
	private String partNumber;
	private long price;
	private long quantity;

	/**
	 * Builder class for Inventory
	 * 
	 * @author Thomas Ngo, A00943900
	 *
	 */
	public static class Builder {
		// required fields
		private final String motorcycleId;
		private final String partNumber;
		// optional fields
		private String description;
		private long price;
		private long quantity;

		/**
		 * Builder constructor
		 */
		public Builder(String motorcycleId, String partNumber) {
			this.motorcycleId = motorcycleId;
			this.partNumber = partNumber;
		}

		/**
		 * Set the description.
		 * 
		 * @param description
		 *            the description to set
		 * @return the Inventory Builder;
		 */
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Set the price
		 * 
		 * @param price
		 *            the price to set
		 * @return the Inventory Builder
		 */
		public Builder setPrice(long price) {
			this.price = price;
			return this;
		}

		/**
		 * Set the quantity
		 * 
		 * @param quantity
		 *            the quantity to set
		 * @return the Inventory Builder
		 */
		public Builder setQuantity(long quantity) {
			this.quantity = quantity;
			return this;
		}

		public Inventory build() {
			return new Inventory(this);
		}

	}

	/**
	 * Default constructor. Private to prevent instantiation.
	 * 
	 * @param builder
	 *            the Inventory Builder used to set fields.
	 */
	private Inventory(Builder builder) {
		motorcycleId = builder.motorcycleId;
		description = builder.description;
		partNumber = builder.partNumber;
		price = builder.price;
		quantity = builder.quantity;
	}

	/**
	 * Get the motorcycle ID.
	 * 
	 * @return the motorcycle ID
	 */
	public String getMotorcycleId() {
		return motorcycleId;
	}

	/**
	 * Set the motorcycle ID
	 * 
	 * @param motorcycleID
	 *            the motorcycle ID to set
	 */
	public void setMotorcycleId(String motorcycleId) {
		this.motorcycleId = motorcycleId;
	}

	/**
	 * Get the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the part number
	 * 
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * Set the part number
	 * 
	 * @param partNumber
	 *            the part number to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * Get the price
	 * 
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Set the price.
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * Get the quantity.
	 * 
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity.
	 * 
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [motorcycleId=" + motorcycleId + ", description=" + description + ", partNumber=" + partNumber + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
