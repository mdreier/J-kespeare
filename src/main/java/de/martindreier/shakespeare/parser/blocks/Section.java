/**
 *
 */
package de.martindreier.shakespeare.parser.blocks;

/**
 * Block representing an act or scene declaration.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public final class Section extends Block {

	public Section(long startingLineNumber) {
		super(startingLineNumber);
	}

	/**
	 * @see de.martindreier.shakespeare.parser.blocks.Block#isMultitline()
	 */
	@Override
	protected boolean isMultitline() {
		// TODO Auto-generated method stub
		return false;
	}

}
