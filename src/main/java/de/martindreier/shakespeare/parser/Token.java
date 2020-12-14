/**
 *
 */
package de.martindreier.shakespeare.parser;

/**
 * Token of the Shakespeare Programming Language.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class Token {
	/**
	 * Type of token.
	 *
	 * @author Martin Dreier <martin@martindreier.de>
	 *
	 */
	public static enum TokenType {
		ACT, SCENE, PERIOD, COMMA, COLON;
	}

	/**
	 * Token value.
	 */
	private String		value;
	/**
	 * Token type.
	 */
	private TokenType	type;

	/**
	 *
	 * @param type
	 * @param value
	 */
	public Token(TokenType type, String value) {
		assert type != null && value != null;
		this.type = type;
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public TokenType getType() {
		return this.type;
	}

	public boolean is(TokenType type) {
		return this.type == type;
	}
}
