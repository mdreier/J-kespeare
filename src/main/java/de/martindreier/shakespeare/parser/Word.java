/**
 *
 */
package de.martindreier.shakespeare.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import de.martindreier.shakespeare.parser.Token.TokenType;

/**
 * A single word in the program source.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class Word {

	private static final Pattern	TRAILING_TOKENS	= Pattern.compile(".+[:,\\.]$");

	private String					value;

	private List<TokenType>			trailingTokens	= new ArrayList<>(1);

	public Word(String original) {
		original = original.strip().toLowerCase();
		assert original != null && original.length() != 0;

		// parse trailing tokens
		while (TRAILING_TOKENS.matcher(original).matches()) {
			this.trailingTokens.add(0, getTokenType(original.charAt(original.length() - 1)));
			original = original.substring(0, original.length() - 1).stripTrailing();
		}

		this.value = original;
	}

	/**
	 * Convert a character to a token. Only the following tokens are allowed:
	 * <ul>
	 * <li>Colon (:)
	 * <li>Comma (,)
	 * <li>Period (.)
	 * </ul>
	 *
	 * @param value
	 *            Character to convert.
	 * @return Token type.
	 */
	private TokenType getTokenType(char value) {
		switch (value) {
			case ':':
				return TokenType.COLON;
			case ',':
				return TokenType.COMMA;
			case '.':
				return TokenType.PERIOD;
		}
		throw new IllegalArgumentException(String.format("%s may not occur at the end of a word", value));
	}

	/**
	 * Get the word value.
	 *
	 * @return Word value.
	 */
	public String get() {
		return this.value;
	}

	/**
	 * Get the trailing token, if any.
	 *
	 * @return The last trailing token. Empty when no trailing token.
	 */
	public Optional<TokenType> getTrailingToken() {
		if (this.trailingTokens.size() == 0) {
			return Optional.empty();
		} else {
			return Optional.of(this.trailingTokens.get(this.trailingTokens.size() - 1));
		}
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Word otherWord) {
			return this.value.equals(otherWord.value);
		} else if (other instanceof String otherString) {
			return this.value.equals(otherString);
		} else {
			return super.equals(other);
		}
	}
}
