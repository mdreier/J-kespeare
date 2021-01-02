package de.martindreier.shakespeare.parser.blocks;

/**
 * Block representing the dramatis personae, the list of characters.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class DramatisPersonae extends Block {

	public DramatisPersonae(long startingLineNumber) {
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
