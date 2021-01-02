/**
 *
 */
package de.martindreier.shakespeare.parser.blocks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.martindreier.shakespeare.parser.errors.ParsingException;

/**
 * Test the {@link Block} class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class BlockTest {

	@Test
	public void addingLinesToSingleLineBlock() throws ParsingException {
		// Title only allows single line blocks
		Block block = new Title();
		assertFalse(block.isMultitline()); // Verify assumption

		// This should be successful
		block.addLine("A");

		try {
			block.addLine("B");
			fail("Adding second line should throw exception");
		} catch (ParsingException e) {
			// Pass
		}

		try {
			block.addLines(Arrays.asList("B", "C"));
			fail("Adding multiple line should throw exception");
		} catch (ParsingException e) {
			// Pass
		}
	}

	@Test
	public void addingLinesToEmptySingleLineBlock() throws ParsingException {
		// Title only allows single line blocks
		Block block = new Title();
		assertFalse(block.isMultitline()); // Verify assumption

		try {
			block.addLines(Arrays.asList("B", "C"));
			fail("Adding multiple line should throw exception");
		} catch (ParsingException e) {
			// Pass
		}
	}

	@Test
	public void addingSingleLineToEmptySingleLineBlock() throws ParsingException {
		// Title only allows single line blocks
		Block block = new Title();
		assertFalse(block.isMultitline()); // Verify assumption

		block.addLines(Arrays.asList("B"));
	}

}
