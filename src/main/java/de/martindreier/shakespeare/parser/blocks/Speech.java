package de.martindreier.shakespeare.parser.blocks;

/**
 * Block representing the speech by a character.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class Speech extends Block {

	/**
	 * @see de.martindreier.shakespeare.parser.blocks.Block#isMultitline()
	 */
	@Override
	protected boolean isMultitline() {
		return true;
	}

}
