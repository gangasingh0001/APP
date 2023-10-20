package org.app.Exceptions;

/**
 * Defined Map org.app.Exceptions are thrown by this class.
 */
public class InvalidMap extends Exception {

	/**
	 * InvalidMap constructor is used to print message when exception is caught in
	 * case map is invalid.
	 *
	 * @param p_message message to print when map is invalid.
	 */
	public InvalidMap(String p_message) {
		super(p_message);
	}
}