/**
 *
 */
package de.martindreier.shakespeare.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.martindreier.shakespeare.parser.Token.TokenType;

/**
 * Tests for the {@link Word} class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class WordTest {

	@Test
	public void noTrailing() {
		Word word = new Word("UNITTEST");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isEmpty());
	}

	@Test
	public void whitespace() {
		Word word = new Word("\tUNITTEST  ");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isEmpty());
	}

	@Test
	public void trailingColon() {
		Word word = new Word("UNITTEST:");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isPresent());
		assertEquals(TokenType.COLON, word.getTrailingToken().get());
	}

	@Test
	public void trailingComma() {
		Word word = new Word("UNITTEST,");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isPresent());
		assertEquals(TokenType.COMMA, word.getTrailingToken().get());
	}

	@Test
	public void trailingPeriod() {
		Word word = new Word("UNITTEST.");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isPresent());
		assertEquals(TokenType.PERIOD, word.getTrailingToken().get());
	}

	@Test
	public void multipleTrailingTokens() {
		Word word = new Word("UNITTEST.:");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isPresent());
		assertEquals(TokenType.COLON, word.getTrailingToken().get());
	}

	@Test
	public void trailingColonWithWhitespace() {
		Word word = new Word("UNITTEST :");
		assertEquals("unittest", word.get());
		assertTrue(word.getTrailingToken().isPresent());
		assertEquals(TokenType.COLON, word.getTrailingToken().get());
	}
}
