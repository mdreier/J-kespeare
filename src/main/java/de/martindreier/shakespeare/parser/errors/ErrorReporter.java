/**
 *
 */
package de.martindreier.shakespeare.parser.errors;

/**
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
@FunctionalInterface
public interface ErrorReporter {

	/**
	 * Create a no-op reporter instance.
	 *
	 * @return Reporter instance.
	 */
	public static ErrorReporter nullReporter() {
		return (line, error) -> {
		};
	}

	public void report(long line, SyntaxError error);
}
