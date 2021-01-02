/**
 *
 */
package de.martindreier.shakespeare.parser.errors;

/**
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class ParsingException extends Exception {
	/**
	 * For serialization.
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Syntax error which caused this exception.
	 */
	private SyntaxError			error;

	public ParsingException(SyntaxError error) {
		// TODO: Provide readable error messages
		super("Syntax Error: " + error.name());
		this.error = error;
	}

	/**
	 * @return
	 */
	public SyntaxError getSyntaxError() {
		return this.error;
	}
}
