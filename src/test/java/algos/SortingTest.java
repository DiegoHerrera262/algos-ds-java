package algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Size;

interface SortingFn<T> {
    Float[] run(Float[] toSort);
}

public class SortingTest {
    public static void sortingUnitTests(SortingFn<Float> sortFn) {
        // When all elements in the array are different
        assertArrayEquals(new Float[] { 0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f },
                sortFn.run(new Float[] { 4f, 6f, 1f, 2f, 3f, 0f, 9f, 7f, 5f, 8f }));
        // When there are repeated elements in the array
        assertArrayEquals(new Float[] { -1f, -1f, 0f, 0f, 1f, 1f, 1.5f, 2f },
                sortFn.run(new Float[] { 2f, 1f, 1.5f, 0f, 1f, -1f, 0f, -1f }));
        // When array to sort is empty
        assertArrayEquals(new Float[] {},
                sortFn.run(new Float[] {}));
    }

    public static void sortingSpecTest(Float[] toSort, SortingFn<Float> sortFn) {
        Float[] sorted = sortFn.run(toSort);
        // Preserves the number of items
        assertEquals(toSort.length, sorted.length);
        // Is idempotent
        assertArrayEquals(sorted, sortFn.run(sorted));
        if (toSort.length > 0) {
            Float first = sorted[0];
            Float last = sorted[sorted.length - 1];
            // Is monotonically increasing
            assertTrue(last - first >= 0);
        }
        // Equivalent to an alternative sort
        Float[] copy = toSort.clone();
        Arrays.sort(copy);
        assertArrayEquals(sorted, copy);
    }

    @Test
    void insertionSortUnit() {
        sortingUnitTests(Sorting::insertionSort);
    }

    @Property
    void insertionSortSpec(@ForAll @Size(min = 0, max = 200) Float[] toSort) {
        sortingSpecTest(toSort, Sorting::insertionSort);
    }

    @Test
    void mergeOperationTest() {
        // When split subarrays dont have common or repeated elements
        Float[] testingNonRepeatedElements = new Float [] { 5f, 6f, 7f, 1f, 2f, 3f, 4f };
        Sorting.merge(testingNonRepeatedElements, 0, 2, 6);
        assertArrayEquals(new Float [] { 1f, 2f, 3f, 4f, 5f, 6f, 7f }, testingNonRepeatedElements);

        // When split subarrays have common or repeated elements
        Float[] testingRepeatedElements = new Float [] { 0f, 6f, 7f, 10f, -1f, 0f, 0f, 2f, 3f, 4f };
        Sorting.merge(testingRepeatedElements, 0, 3, 9);
        assertArrayEquals(new Float [] { -1f, 0f, 0f, 0f, 2f, 3f, 4f, 6f, 7f, 10f }, testingRepeatedElements);

        // When left subarray is empty
        Float[] testingLeftEmpty = new Float [] { 1f, 2f, 3f, 4f };
        Sorting.merge(testingLeftEmpty, 0, 0, 3);
        assertArrayEquals(new Float [] { 1f, 2f, 3f, 4f }, testingLeftEmpty);

        // When right subarray is empty
        Float[] testingRightEmpty = new Float [] { 1f, 2f, 3f, 4f };
        Sorting.merge(testingRightEmpty, 0, 3, 3);
        assertArrayEquals(new Float [] { 1f, 2f, 3f, 4f }, testingRightEmpty);
    }

    @Test
    void mergeSortUnit() {
        sortingUnitTests(Sorting::mergeSort);
    }

    @Property
    void mergeSortSpec(@ForAll @Size(min = 0, max = 200) Float[] toSort) {
        sortingSpecTest(toSort, Sorting::mergeSort);
    }
}
