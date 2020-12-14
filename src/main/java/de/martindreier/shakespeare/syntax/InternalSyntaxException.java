/**
 *
 */
package de.martindreier.shakespeare.syntax;

/**
 * This exception is raised in case of invalid syntax.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class InternalSyntaxException extends Exception {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -2594316765259766471L;

	/**
	 * @param message
	 * @param cause
	 */
	public InternalSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public InternalSyntaxException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InternalSyntaxException(Throwable cause) {
		super(cause);
	}
}
