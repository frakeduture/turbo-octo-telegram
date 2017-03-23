/**
 * Project: Lab5
 * File: Validator.java
 * Date: Jan 20, 2017
 * Time: 9:12:36 PM
 */
package a00943900.bcmc.util;

/**
 * Checks customer data for invalid entries
 * 
 * @author Thomas Ngo, A00943900
 *
 */
public class Validator {

	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Validator() {

	}

	/**
	 * Checks whether or not an email address is valid.
	 * 
	 * @param email
	 *            the email to be validated
	 * @return whether or not the email is valid
	 */
	public static boolean validateEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
}
