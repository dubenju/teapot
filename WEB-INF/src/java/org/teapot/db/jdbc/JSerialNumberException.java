package org.teapot.db.jdbc;
/**
 * @author DBJ
 *
 */
public class JSerialNumberException extends Exception {

	/**
	 *
	 */
	public JSerialNumberException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JSerialNumberException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JSerialNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JSerialNumberException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JSerialNumberException(Throwable cause) {
		super(cause);
	}

}
