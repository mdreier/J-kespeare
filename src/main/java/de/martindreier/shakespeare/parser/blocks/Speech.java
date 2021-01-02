package de.martindreier.shakespeare.parser.blocks;

/**
 * Block representing the speech by a character.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class Speech extends Block {

	public Speech(long startingLineNumber) {
		super(startingLineNumber);
	}

	/**
	 * @see de.martindreier.shakespeare.parser.blocks.Block#isMultitline()
	 */
	@Override
	protected boolean isMultitline() {
		return true;
	}

}
