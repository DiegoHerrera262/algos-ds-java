package algos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldAnswerWithFalse() {
        assertTrue(true);
    }

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll double anInteger) {
        return Math.abs(anInteger) >= 0;
    }
}
