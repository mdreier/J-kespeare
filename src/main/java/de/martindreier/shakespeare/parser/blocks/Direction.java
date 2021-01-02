package de.martindreier.shakespeare.parser.blocks;

/**
 * Block representing an enter, exit, or exeunt direction.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class Direction extends Block {

	public Direction(long startingLineNumber) {
		super(startingLineNumber);
	}

	/**
	 * @see de.martindreier.shakespeare.parser.blocks.Block#isMultitline()
	 */
	@Override
	protected boolean isMultitline() {
		return false;
	}

}
