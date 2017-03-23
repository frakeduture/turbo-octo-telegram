/**
 * Project: A00943900Lab3
 * File: ApplicationException.java
 * Date: Jan 31, 2017
 * Time: 10:37:16 PM
 */
package a00943900.bcmc;

/**
 * @author Thomas Ngo, A00943900
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * Default Constructor.
	 */
	public ApplicationException() {
	}

	/**
	 * Construct new exception with the specified detail message.
	 * 
	 * @param message
	 *            the detail message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Construct new exception with the specified cause.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct new exception with the specified detail message cause.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct new exception with specified detail message, cause, whether or not suppression is enabled, and whether or not writable stack trace is
	 * enabled.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            whether or not suppression is enabled
	 * @param writableStackTrace
	 *            whether or not writable stack trace is enabled
	 */
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
