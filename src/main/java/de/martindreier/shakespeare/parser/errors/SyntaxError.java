/**
 *
 */
package de.martindreier.shakespeare.parser.errors;

/**
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public enum SyntaxError {
	SingleLineBlock;

	/**
	 * Throws this error as an exception.
	 *
	 * @throws ParsingException
	 */
	public void raise() throws ParsingException {
		throw new ParsingException(this);
	}

}
