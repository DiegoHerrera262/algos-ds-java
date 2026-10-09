package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

/**
 * Unit test for simple App.
 */
public class PairwiseProductTest {

    /**
     * Given:
     * - An array of longs will all elements different
     * Then:
     * - Computes correctly max pairwise product
     */
    @Test
    void whenAllNumbersDifferentInList() {
        assertEquals(400L, PairwiseProduct.maxProduct(new long[] { 100L, 1L, 2L, 4L }));
    }

    /**
     * Given:
     * - An array with duplicated max value
     * Then:
     * - Computes correctly max pairwise product
     */
    @Test
    void whenMaxValueIsDuplicated() {
        assertEquals(100L, PairwiseProduct.maxProduct(new long[] { 10L, 10L, 1L, 1L }));
    }

    /**
     * Given:
     * - An array with duplicated second max value
     * Then:
     * - Computes correctly max pairwise product
     */
    @Test
    void whenSecondMaxValueIsDuplicated() {
        assertEquals(70L, PairwiseProduct.maxProduct(new long[] { 10L, 7L, 2L, 7L }));
    }

    /**
     * Given:
     * - An alternative potentially slower correct implementation
     * Then:
     * - Matches with the implementation in all sample cases
     */
    @Provide
    Arbitrary<long[]> positiveArrays() {
       return Arbitraries.longs()
           .greaterOrEqual(0L)
           .lessOrEqual(200000L)
           .array(long[].class)
           .ofMinSize(2)
           .ofMaxSize(200);
    }

    @Property
    void equivalentToNaive(@ForAll("positiveArrays") long[] numberList) {
        assertEquals(PairwiseProduct.naiveMaxProduct(numberList),
                     PairwiseProduct.maxProduct(numberList));
    }
}
