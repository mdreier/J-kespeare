/**
 *
 */
package de.martindreier.shakespeare.parser;

import java.text.ParseException;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;

import de.martindreier.shakespeare.syntax.InternalSyntaxException;

/**
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class RomanNumeral implements Comparable<RomanNumeral> {

	/**
	 * Decimal representation of the Roman numeral.
	 */
	private int							value;

	private static RomanNumeralFormat	format	= new RomanNumeralFormat();

	/**
	 * @param intValue
	 */
	public RomanNumeral(int intValue) {
		this.value = intValue;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RomanNumeral o) {
		return Integer.compare(this.value, o.value);
	}

	/**
	 * Parse a string into a roman numeral.
	 *
	 * @param value
	 *            Value to parse.
	 * @return Roman numeral.
	 * @throws InternalSyntaxException
	 *             Input is not a valid roman numeral.
	 */
	public static RomanNumeral parse(String value) throws InternalSyntaxException {
		try {
			return new RomanNumeral(format.parse(value).intValue());
		} catch (ParseException e) {
			throw new InternalSyntaxException(String.format("%s is not a valid roman numeral", value));
		}
	}

	/**
	 * @return
	 */
	public int asInt() {
		return this.value;
	}
}
