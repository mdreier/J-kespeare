package de.martindreier.shakespeare.parser.blocks;

/**
 * Block of text representing a title.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class Title extends Block {

	/**
	 * @see de.martindreier.shakespeare.parser.blocks.Block#isMultitline()
	 */
	@Override
	protected boolean isMultitline() {
		return false;
	}

}
