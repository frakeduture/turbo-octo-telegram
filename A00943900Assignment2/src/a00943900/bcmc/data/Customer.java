/**
 * Project: Assignment 1
 * File: Customer.java
 * Date: Jan 20, 2017
 * Time: 8:16:21 PM
 */
package a00943900.bcmc.data;

import java.time.LocalDate;

/**
 * A BCMC customer.
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class Customer {

	public static final int ATTRIBUTE_COUNT = 9;

	private long id;
	private String firstName;
	private String lastName;
	private String streetName;
	private String city;
	private String postalCode;
	private String phoneNumber;
	private String emailAddress;
	private LocalDate joinDate;

	/**
	 * A customer builder.
	 */
	public static class Builder {
		// required fields
		private final long id;
		private String phoneNumber;
		// optional fields
		private String firstName;
		private String lastName;
		private String streetName;
		private String city;
		private String postalCode;
		private String emailAddress;
		private LocalDate joinDate;

		/**
		 * Default constructor.
		 * 
		 */
		public Builder(Long id, String phoneNumber) {
			this.id = id;
			this.phoneNumber = phoneNumber;
		}

		/**
		 * Set the first name.
		 * 
		 * @param firstName
		 *            the first name to set
		 * @return the Customer Builder
		 */
		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * Set the last name.
		 * 
		 * @param lastName
		 *            the last name to set
		 * @return the Customer Builder
		 */
		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * Set the street.
		 * 
		 * @param streetName
		 *            the street name to set
		 * @return the Customer Builder
		 */
		public Builder setStreetName(String streetName) {
			this.streetName = streetName;
			return this;
		}

		/**
		 * Set the city.
		 * 
		 * @param city
		 *            the city to set
		 * @return the Customer Builder
		 */
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		/**
		 * Set the postal code.
		 * 
		 * @param postalCode
		 *            the postal code to set
		 * @return the Customer Builder
		 */
		public Builder setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		/**
		 * Set the email address.
		 * 
		 * @param emailAddress
		 *            the email address to set
		 * @return the Customer Builder
		 */
		public Builder setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		/**
		 * Set the join date.
		 * 
		 * @param joinDate
		 *            the join date to set
		 * @return the Customer Builder
		 */
		public Builder setJoinDate(LocalDate joinDate) {
			this.joinDate = joinDate;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}

	}

	/**
	 * Default Constructor.
	 */
	private Customer(Builder builder) {
		id = builder.id;
		firstName = builder.firstName;
		lastName = builder.lastName;
		streetName = builder.streetName;
		city = builder.city;
		postalCode = builder.postalCode;
		phoneNumber = builder.phoneNumber;
		emailAddress = builder.emailAddress;
		joinDate = builder.joinDate;
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
	 * Get the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name.
	 * 
	 * @param firstName
	 *            the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name.
	 * 
	 * @param lastName
	 *            the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the street name.
	 * 
	 * @return the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Set the street name.
	 * 
	 * @param streetName
	 *            the street name to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Get the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set the city.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get the postal code.
	 * 
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Set the postal code.
	 * 
	 * @param postalCode
	 *            the postal code to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * get the phone number.
	 * 
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number.
	 * 
	 * @param phoneNumber
	 *            the phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get the email address.
	 * 
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Set the email address.
	 * 
	 * @param emailAddress
	 *            the email sddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Get the join date.
	 * 
	 * @return the join date
	 */
	public LocalDate getJoinDate() {
		return joinDate;
	}

	/**
	 * Set the join date.
	 * 
	 * @param joinDate
	 *            the join date to set
	 */
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", streetName=" + streetName + ", city=" + city
				+ ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + ", joinDate=" + joinDate + "]";
	}
}
