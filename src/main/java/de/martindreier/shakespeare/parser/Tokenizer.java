/**
 *
 */
package de.martindreier.shakespeare.parser;

import java.util.LinkedList;
import java.util.List;

import de.martindreier.shakespeare.parser.Token.TokenType;

/**
 * The tokenizer takes a string and splits it into lexical tokens.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
public class Tokenizer {

	private Wordlists wordlists = new Wordlists();

	@SuppressWarnings("unlikely-arg-type")
	public List<Token> parse(String line) {
		List<Token> tokens = new LinkedList<>();

		Word[] words = splitLineAndCleanWords(line);
		int index = 0;
		while (index < words.length) {
			// Because index is already increased here, index checks below must check on expected
			// index + 1
			// For example, checking for the first character would be index == 1
			Word current = words[index++];

			// Empty token
			if (current.equals("")) {
				continue;
			}

			// Act
			if (index == 1 && current.equals("act")) {
				tokens.add(new Token(TokenType.ACT, words[index++].get()));
				// Act declaration ends the line
				break;
			} else if (index == 1 && current.equals("scene")) {
				tokens.add(new Token(TokenType.SCENE, words[index++].get()));
				// Act declaration ends the line
				break;
			}

			index++;
		}
		return tokens;
	}

	/**
	 * Split the line into words, remove whitespace and trailing colons.
	 *
	 * @param line
	 *            Line to split.
	 * @return Cleaned word list.
	 */
	private Word[] splitLineAndCleanWords(String line) {
		String[] elements = line.split("\\s+");
		Word[] words = new Word[elements.length];
		for (int index = 0; index < words.length; index++) {
			words[index] = new Word(elements[index]);
		}
		return words;
	}
}
