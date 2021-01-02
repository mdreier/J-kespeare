/**
 *
 */
package de.martindreier.shakespeare.parser.blocks;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import de.martindreier.shakespeare.parser.errors.ParsingException;
import de.martindreier.shakespeare.parser.errors.SyntaxError;

/**
 * Block of text.
 *
 * A block may be of the following types:
 * <ul>
 * <li>Title
 * <li>Dramatis Personae
 * <li>Act or Scene
 * <li>Speech
 * <li>Entering and Exiting
 * </ul>
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public sealed abstract class Block permits Title,DramatisPersonae,Section,Direction,Speech {

	/**
	 * Lines of text in the block.
	 */
	private List<String> lines = new LinkedList<>();

	/**
	 * Add a line to the block.
	 *
	 * @throws ParsingException
	 *             Lines are not allowed to be added.
	 */
	public void addLine(String line) throws ParsingException {
		checkNewLinesAllowed(1);
		this.lines.add(line);
	}

	/**
	 * Add lines to the block.
	 *
	 * @throws ParsingException
	 *             Lines are not allowed to be added.
	 */
	public void addLines(List<String> lines) throws ParsingException {
		checkNewLinesAllowed(lines.size());
		this.lines.addAll(lines);
	}

	/**
	 * Add all lines from another block to the block.
	 *
	 * @throws ParsingException
	 *             Lines are not allowed to be added.
	 */
	public void addLines(Block other) throws ParsingException {
		addLines(other.getLines());
	}

	/**
	 * Get the lines of this block.
	 *
	 * @return Unmodifiable list of lines in this block.
	 */
	public List<String> getLines() {
		return Collections.unmodifiableList(this.lines);
	}

	/**
	 * Get the content of the block as a single line.
	 *
	 * @return Complete content as single line string.
	 */
	public String getContent() {
		return this.lines.stream().collect(Collectors.joining(" "));
	}

	/**
	 * Merge this block with another block. The other block will be appended
	 * to this block. A new block will be returned, this block and the other
	 * block will not be modified.
	 *
	 * @param other
	 *            The other block to merge.
	 * @return New block containing the contents of this and the other block.
	 * @throws ParsingException
	 *             Block type does not allow merging.
	 * @see #addLines(Block)
	 */
	public Block merge(Block other) throws ParsingException {
		if (this.getClass() != other.getClass()) {
			throw new IllegalArgumentException("Only blocks with the same type can be merged");
		}
		Block merged;
		try {
			merged = this.getClass().getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new IllegalArgumentException("Cannot construct new instance of block type", e);
		}
		merged.addLines(getLines());
		merged.addLines(other.getLines());
		return merged;
	}

	/**
	 * Check if new lines can be added to the block.
	 *
	 * @param count
	 *            List of lines to be added.
	 * @throws ParsingException
	 *             Requested number of lines cannot be added.
	 */
	protected void checkNewLinesAllowed(long count) throws ParsingException {
		if (isMultitline()) {
			// Adding more lines is always allowed
			return;
		}
		if (count > 1) {
			SyntaxError.SingleLineBlock.raise();
		} else if (count == 1 && this.lines.size() > 0) {
			SyntaxError.SingleLineBlock.raise();
		}
	}

	/**
	 * Check if the block type allows multiple lines.
	 *
	 * @return <code>true</code> if multiple lines are allowed, <code>false</code>
	 *         if only one line is allowed.
	 */
	protected abstract boolean isMultitline();
}
