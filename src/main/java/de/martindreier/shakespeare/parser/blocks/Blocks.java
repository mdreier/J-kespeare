package de.martindreier.shakespeare.parser.blocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import de.martindreier.shakespeare.parser.errors.ErrorReporter;
import de.martindreier.shakespeare.parser.errors.ParsingException;

/**
 * Parse blocks of text.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class Blocks {

	/**
	 * Error reporter instance.
	 */
	private ErrorReporter reporter;

	/**
	 * Create a new block parser.
	 *
	 * @param reporter
	 *            Error reporter instance.
	 */
	public Blocks(ErrorReporter reporter) {
		this.reporter = reporter == null ? ErrorReporter.nullReporter() : reporter;
	}

	/**
	 * Parse the input into a list of blocks.
	 *
	 * @param input
	 *            Text input.
	 * @return Parsed blocks.
	 * @throws IOException
	 *             Error reading from input stream.
	 */
	public List<Block> parse(InputStream input) throws IOException {
		List<String> lines = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			lines.add(reader.readLine());
		}
		return parse(lines);
	}

	/**
	 * Parse the input into a list of blocks.
	 *
	 * @param input
	 *            Text input.
	 * @return Parsed blocks.
	 */
	public List<Block> parse(String input) {
		// Split into single lines
		return parse(input.split("\\R"));
	}

	/**
	 * Parse the input into a list of blocks.
	 *
	 * @param input
	 *            Text input.
	 * @return Parsed blocks.
	 */
	public List<Block> parse(List<String> input) {
		return parse(input.toArray(new String[input.size()]));
	}

	/**
	 * Parse the input into a list of blocks.
	 *
	 * @param input
	 *            Text input.
	 * @return Parsed blocks.
	 */
	public List<Block> parse(String[] input) {
		List<Block> blocks = new LinkedList<>();

		Block currentBlock = null;
		for (int index = 0; index < input.length; index++) {
			String line = input[index].trim();
			if (line.isBlank() && currentBlock != null) {
				blocks.add(currentBlock);
				currentBlock = null;
				continue;
			}

			// Allocate new block if required
			if (currentBlock == null) {
				currentBlock = identifyBlockType(blocks.size(), line);
			}

			try {
				currentBlock.addLine(line);
			} catch (ParsingException e) {
				this.reporter.report(index, e.getSyntaxError());
			}
		}
		if (currentBlock != null) {
			blocks.add(currentBlock);
		}

		return blocks;
	}

	/**
	 * Identify the block type based on the number of existing blocks
	 * and the line content.
	 *
	 * @param blockCount
	 *            Number of existing blocks.
	 * @param line
	 *            Line content.
	 * @return New empty block of the correct type.
	 */
	private Block identifyBlockType(long blockCount, String line) {

		if (blockCount == 0) {
			// First block is always the title
			return new Title();
		} else if (blockCount == 1) {
			// Second block is always the dramatis personae
			return new DramatisPersonae();
		} else if (line.startsWith("[")) {
			return new Direction();
		} else if (line.toLowerCase().startsWith("act") || line.toLowerCase().startsWith("scene")) {
			return new Section();
		} else {
			return new Speech();
		}
	}

}
