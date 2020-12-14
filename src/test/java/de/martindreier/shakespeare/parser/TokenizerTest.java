/**
 *
 */
package de.martindreier.shakespeare.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.martindreier.shakespeare.parser.Token.TokenType;

/**
 * Tests for the {@link Tokenizer} class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class TokenizerTest {

	@Test
	public void actDeclaration() {
		List<Token> tokens = new Tokenizer().parse("Act I: A Unit Test");

		assertEquals(1, tokens.size(), "Incorrect number of tokens");
		assertEquals(TokenType.ACT, tokens.get(0).getType(), "Incorrect token type");
		assertEquals("i", tokens.get(0).getValue(), "Incorrect token value");
	}

	@Test
	public void sceneDeclaration() {
		List<Token> tokens = new Tokenizer().parse("Scene IV: A Unit Test");

		assertEquals(1, tokens.size(), "Incorrect number of tokens");
		assertEquals(TokenType.SCENE, tokens.get(0).getType(), "Incorrect token type");
		assertEquals("iv", tokens.get(0).getValue(), "Incorrect token value");
	}

}
