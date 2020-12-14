/**
 *
 */
package de.martindreier.shakespeare.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Wordlists} class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class WordlistsTest {

	@Test
	public void loading() {
		Wordlists lists = new Wordlists();
		assertNotNull(lists.characters, "Characters wordlist not loaded");
		assertEquals(152, lists.characters.size(), "Character wordlist loaded incompletely");
		assertTrue(lists.characters.contains("achilles"), "First characher missing in wordlist");
		assertTrue(lists.characters.contains("king richard"), "Multi-word character missing in wordlist");
		assertTrue(lists.characters.contains("viola"), "Last characher missing in wordlist");
	}
}
