/**
 *
 */
package de.martindreier.shakespeare.parser.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.martindreier.shakespeare.parser.errors.ErrorReporter;
import de.martindreier.shakespeare.parser.errors.SyntaxError;

/**
 * Testing the block parsing.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class BlockParserTest {

	private List<SyntaxError>	errors;
	private ErrorReporter		reporter;

	@BeforeEach
	public void clearErrors() {
		this.errors = new ArrayList<>(20);
		this.reporter = (line, error) -> this.errors.add(error);
	}

	/**
	 * Title only.
	 */
	@Test
	public void title() {
		String input = """
				A Unit Test
				""";
		List<Block> blocks = new Blocks(this.reporter).parse(input);
		assertEquals(1, blocks.size(), "Only title block expected");
		assertTrue(blocks.get(0) instanceof Title, "Not recognized as title block");
		assertEquals("A Unit Test", blocks.get(0).getContent());
		assertNoErrors();
	}

	/**
	 * Title may only have one line.
	 */
	@Test
	public void multilineTitle() {
		String input = """
				A Unit Test
				An illegal line
				""";

		List<Block> blocks = new Blocks(this.reporter).parse(input);
		assertEquals(1, blocks.size(), "Only title block expected");
		assertTrue(blocks.get(0) instanceof Title, "Not recognized as title block");
		assertEquals(1, this.errors.size(), "Second line should raise error");
		assertEquals(1, blocks.get(0).getLines().size(), "Second line should not be added to block");
	}

	@Test
	public void minimalValidProgram() {
		String input = """
				A Unit Test

				Bob, a character

				  Act I: The First Act

				Scene I: The First Scene

				[Enter Bob]

				Bob: I say something.
				  Something more.

				[Exeunt]
				""";

		List<Block> blocks = new Blocks(this.reporter).parse(input);

		assertEquals(7, blocks.size(), "Incorrect number of blocks");
		assertNoErrors();

		assertTrue(blocks.get(0) instanceof Title, "Not recognized as title block");
		assertTrue(blocks.get(1) instanceof DramatisPersonae, "Not recognized as Dramatis Personae");
		assertTrue(blocks.get(2) instanceof Section, "Not recognized as section block");
		assertTrue(blocks.get(3) instanceof Section, "Not recognized as section block");
		assertTrue(blocks.get(4) instanceof Direction, "Not recognized as direction block");
		assertTrue(blocks.get(5) instanceof Speech, "Not recognized as speech block");
		assertTrue(blocks.get(6) instanceof Direction, "Not recognized as direction block");
	}

	protected void assertNoErrors() {
		assertEquals(0, this.errors.size(), "Expected no errors");
	}
}
