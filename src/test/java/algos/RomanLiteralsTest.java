package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class RomanLiteralsTest {

    @Test
    void tailMatchesUnit() {
        assertTrue(RomanLiterals.tailMatches("MCMIII", "I"));
        assertTrue(RomanLiterals.tailMatches("MCMIV", "IV"));
        assertTrue(RomanLiterals.tailMatches("MCM", "CM"));
        assertFalse(RomanLiterals.tailMatches("MCM", "CD"));
    }

    @Test
    void toDecimalUnit() {
        assertEquals(3, RomanLiterals.toDecimal("III"));
        assertEquals(8, RomanLiterals.toDecimal("VIII"));
        assertEquals(58, RomanLiterals.toDecimal("LVIII"));
        assertEquals(1994, RomanLiterals.toDecimal("MCMXCIV"));
    }

    @Test
    void toRomanLiteralUnit() {
        assertEquals("III", RomanLiterals.toRomanLiteral(3));
        assertEquals("VIII", RomanLiterals.toRomanLiteral(8));
        assertEquals("LVIII", RomanLiterals.toRomanLiteral(58));
        assertEquals("DC", RomanLiterals.toRomanLiteral(600));
        assertEquals("MMMDCCXLIX", RomanLiterals.toRomanLiteral(3749));
        assertEquals("MCMXCIV", RomanLiterals.toRomanLiteral(1994));
    }

    @Property
    void inverseSpec(@ForAll @IntRange(min = 1, max = 3999) int num) {
        assertEquals(num, RomanLiterals.toDecimal(RomanLiterals.toRomanLiteral(num)));
    }
}
