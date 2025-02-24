package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Size;

/**
 * Unit test for simple App.
 */
public class PairwiseProductTest {

    @Test
    void whenExcludingRandomIndexInList() {
        Long[] sample = { 100L, 200L, 300L, 50L };
        var maxResult = PairwiseProduct.maxValExcludingIndex(sample, 2);
        assertEquals(200L, maxResult[1]);
        assertEquals(1L, maxResult[0]);
    }

    @Test
    void whenExcludingFirstElement() {
        Long[] sample = { 1000L, 200L, 300L, 50L };
        var maxResult = PairwiseProduct.maxValExcludingIndex(sample, 0);
        assertEquals(300L, maxResult[1]);
        assertEquals(2L, maxResult[0]);
    }

    @Test
    void whenEmptyList() {
        Long[] sample = {};
        var maxResult = PairwiseProduct.maxValExcludingIndex(sample, 2);
        assertEquals(0, maxResult.length);
    }

    @Test
    void whenExcludingOnlyElement() {
        Long[] sample = { 100L };
        var maxResult = PairwiseProduct.maxValExcludingIndex(sample, 0);
        assertEquals(0, maxResult.length);
    }

    @Property
    void equivalentToMax(@ForAll @Size(min=2, max=200) Long[] numberList) {
        var maxResponse = PairwiseProduct.maxValExcludingIndex(numberList);
        var anotherNumberList = Arrays.copyOf(numberList, numberList.length);
        Arrays.sort(anotherNumberList);
        assertEquals(anotherNumberList[numberList.length - 1], maxResponse[1]);
    }

    /**
     * Given:
     * - An array of longs will all elements different
     * Then:
     * - Computes correctly max pairwise product
     */
    @Test
    void whenAllNumbersDifferentInList() {
        Long[] sample = { 100L, 4L, 2L, 1L };
        assertEquals(400L, PairwiseProduct.maxProduct(sample));
    }

    /**
     * Given:
     * - An array with duplicated max value
     * Then:
     * - Computes correctly max pairwise product
     */
    @Test
    void whenMaxValueIsDuplicated() {
        Long[] sample = { 10L, 10L, 1L, 1L };
        assertEquals(100L, PairwiseProduct.maxProduct(sample));
    }

    /**
     * Given:
     * - An alternative potentially slower correct implementation
     * Then:
     * - Matches with the implementation in all sample cases
     */
    @Property
    void equivalentToNaive(@ForAll @Size(min=2, max=200) Long[] numberList) {
        assertEquals(PairwiseProduct.maxProduct(numberList),
                     PairwiseProduct.maxProduct(numberList));
    }
}
