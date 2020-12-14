/**
 *
 */
package de.martindreier.shakespeare.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import de.martindreier.shakespeare.syntax.InternalSyntaxException;

/**
 * Tests for the {@link RomanNumeral} class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class RomanNumeralTest {

	@Test
	public void parse1() throws InternalSyntaxException {
		assertEquals(1, RomanNumeral.parse("I").asInt(), "Parsing I to incorrect value");
	}

	@Test
	public void parse2() throws InternalSyntaxException {
		assertEquals(2, RomanNumeral.parse("II").asInt(), "Parsing II to incorrect value");
	}

	@Test
	public void parse3() throws InternalSyntaxException {
		assertEquals(3, RomanNumeral.parse("III").asInt(), "Parsing III to incorrect value");
	}

	@Test
	public void parse4() throws InternalSyntaxException {
		assertEquals(4, RomanNumeral.parse("IV").asInt(), "Parsing IV to incorrect value");
	}

	@Test
	public void parse5() throws InternalSyntaxException {
		assertEquals(5, RomanNumeral.parse("V").asInt(), "Parsing V to incorrect value");
	}

	@Test
	public void parse7() throws InternalSyntaxException {
		assertEquals(7, RomanNumeral.parse("VII").asInt(), "Parsing VII to incorrect value");
	}

	@Test
	public void parse9() throws InternalSyntaxException {
		assertEquals(9, RomanNumeral.parse("IX").asInt(), "Parsing IX to incorrect value");
	}

	@Test
	public void parse10() throws InternalSyntaxException {
		assertEquals(10, RomanNumeral.parse("X").asInt(), "Parsing X to incorrect value");
	}

	@Test
	public void parse24() throws InternalSyntaxException {
		assertEquals(24, RomanNumeral.parse("XXIV").asInt(), "Parsing XXIV to incorrect value");
	}

	@Test
	public void parse66() throws InternalSyntaxException {
		assertEquals(66, RomanNumeral.parse("LXVI").asInt(), "Parsing LIVI to incorrect value");
	}

	@Test
	public void parse94() throws InternalSyntaxException {
		assertEquals(94, RomanNumeral.parse("XCIV").asInt(), "Parsing LXLIV to incorrect value");
	}

	@Test
	public void parse127() throws InternalSyntaxException {
		assertEquals(127, RomanNumeral.parse("CXXVII").asInt(), "Parsing CXXVII to incorrect value");
	}

	@Test
	public void parse2421() throws InternalSyntaxException {
		assertEquals(2421, RomanNumeral.parse("MMCDXXI").asInt(), "Parsing MMCDXXI to incorrect value");
	}

	public void parseLowerCase() throws InternalSyntaxException {
		assertEquals(3, RomanNumeral.parse("iIi").asInt(), "Parsing mixed case number iIi to incorrect value");
	}

	@Test
	public void parseInvalid4() {
		try {
			RomanNumeral.parse("IIII");
			fail("Parsing IIII to incorrect value");
		} catch (InternalSyntaxException e) {
			// Expected
		}
	}

	@Test
	public void parseNonnumber() throws InternalSyntaxException {
		try {
			RomanNumeral.parse("abc");
			fail("Parsing abc to incorrect value");
		} catch (InternalSyntaxException e) {
			// Expected
		}
	}
}
