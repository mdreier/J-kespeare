/**
 *
 */
package de.martindreier.shakespeare.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class loads the wordlists into memory.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class Wordlists {

	public final Set<String> characters = load("characters");

	/**
	 * Load a wordlist.
	 *
	 * @param wordlist
	 *            Name of the wordlist to load.
	 * @return Content of the wordlist.
	 */
	private Set<String> load(String wordlist) {
		try {
			return Collections.unmodifiableSet(new HashSet<>(Files.readAllLines(Path.of(this.getClass().getResource("/wordlists/" + wordlist).toURI()))));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(String.format("Could not load wordlist %s", wordlist), e);
		}
	}

}
