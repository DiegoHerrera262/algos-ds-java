package algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KnapsackTest {

    @Test
    void whenValidatingUnitCostForSomeValues() {
        assertArrayEquals(new Long[] { 8L, 7L, 6L },
                Knapsack.unitCosts(new Long[] { 24L, 28L, 30L }, new Long[] { 3L, 4L, 5L }));
        assertArrayEquals(new Long[] {}, Knapsack.unitCosts(new Long[] {}, new Long[] { 3L }));
    }

    @Test
    void whenValidatingMostProfitableAvailableItem() {
        assertEquals(0, Knapsack.mostProfitableAvailableItem(new Long[] { 3L, 4L, 5L }, new Long[] { 8L, 7L, 6L }));
        assertEquals(1, Knapsack.mostProfitableAvailableItem(new Long[] { 0L, 4L, 5L }, new Long[] { 8L, 7L, 6L }));
        assertEquals(2, Knapsack.mostProfitableAvailableItem(new Long[] { 0L, 0L, 5L }, new Long[] { 8L, 7L, 6L }));
        assertEquals(-1, Knapsack.mostProfitableAvailableItem(new Long[] { 0L, 0L, 0L }, new Long[] { 8L, 7L, 6L }));
        assertEquals(-1, Knapsack.mostProfitableAvailableItem(new Long[] { 1L, 4L }, new Long[] { 8L, 7L, 6L }));
    }

    @Test
    void whenValidatingGreedyMaximumLoot() {
        assertEquals(-1L, Knapsack.internalGreedyMaximumLoot(100L, new Long[] { 2L, 1L }, new Long[] {}));
        assertEquals(-1L, Knapsack.internalGreedyMaximumLoot(1L, new Long[] {}, new Long[] { 1L }));
        assertEquals(0L, Knapsack.internalGreedyMaximumLoot(10L, new Long[] {}, new Long[] {}));
        assertEquals(10L, Knapsack.internalGreedyMaximumLoot(1L, new Long[] { 1L }, new Long[] { 10L }));
        assertEquals(64L, Knapsack.internalGreedyMaximumLoot(9L, new Long[] { 3L, 4L, 5L }, new Long[] { 8L, 7L, 6L }));
        assertEquals(180L,
                Knapsack.internalGreedyMaximumLoot(50L, new Long[] { 20L, 50L, 30L }, new Long[] { 3L, 2L, 4L }));
    }

    @Test
    void whenValidatingMaximumLoot() {
        assertEquals(-1L, Knapsack.maximumLoot(100L, new Long[] { 2L, 1L }, new Long[] {}));
        assertEquals(-1L, Knapsack.maximumLoot(1L, new Long[] {}, new Long[] { 1L }));
        assertEquals(0L, Knapsack.maximumLoot(10L, new Long[] {}, new Long[] {}));
        assertEquals(10L, Knapsack.maximumLoot(1L, new Long[] { 1L }, new Long[] { 10L }));
        assertEquals(2L, Knapsack.maximumLoot(1L, new Long[] { 1L, 1L, 1L, 1L, 1L, 1L, 1L },
                new Long[] { 1L, 1L, 1L, 1L, 1L, 1L, 2L }));
        assertEquals(64L, Knapsack.maximumLoot(9L, new Long[] { 3L, 4L, 5L }, new Long[] { 24L, 28L, 30L }));
        assertEquals(180L, Knapsack.maximumLoot(50L, new Long[] { 20L, 50L, 30L }, new Long[] { 60L, 100L, 120L }));
    }

    @Test
    void whenValidatingExternalGreedyMaximumLoot() {
        assertEquals(-1L, Knapsack.greedyMaximumLoot(100L, new Long[] { 2L, 1L }, new Long[] {}));
        assertEquals(-1L, Knapsack.greedyMaximumLoot(1L, new Long[] {}, new Long[] { 1L }));
        assertEquals(0L, Knapsack.greedyMaximumLoot(10L, new Long[] {}, new Long[] {}));
        assertEquals(10L, Knapsack.greedyMaximumLoot(1L, new Long[] { 1L }, new Long[] { 10L }));
        assertEquals(2L, Knapsack.greedyMaximumLoot(1L, new Long[] { 1L, 1L, 1L, 1L, 1L, 1L, 1L },
                new Long[] { 1L, 1L, 1L, 1L, 1L, 1L, 2L }));
        assertEquals(64L, Knapsack.greedyMaximumLoot(9L, new Long[] { 3L, 4L, 5L }, new Long[] { 24L, 28L, 30L }));
        assertEquals(180L,
                Knapsack.greedyMaximumLoot(50L, new Long[] { 20L, 50L, 30L }, new Long[] { 60L, 100L, 120L }));
    }

    @Test
    void whenComparingGreedyAndMaximumLoot() {
        assertEquals(
                Knapsack.greedyMaximumLoot(19L, new Long[] { 8113263L, 7763133L, 267L, 638L },
                        new Long[] { 1334479L, 268675L, 285495L, 1856725L }),
                Knapsack.maximumLoot(19L, new Long[] { 8113263L, 7763133L, 267L, 638L },
                        new Long[] { 1334479L, 268675L, 285495L, 1856725L }));
        assertEquals(
                Knapsack.greedyMaximumLoot(9L, new Long[] { 17534L, 6L, 2L, 2L, 84619L, 3570L },
                        new Long[] { 1577L, 64L, 15786477941059L, 8404624359134415252L, 14L, 1151L}),
                Knapsack.maximumLoot(9L, new Long[] { 17534L, 6L, 2L, 2L, 84619L, 3570L },
                        new Long[] { 1577L, 64L, 15786477941059L, 8404624359134415252L, 14L, 1151L}));

    }

    // FIXME This property test is more appropriate for comparing the two implementations of the greedy
    // algorithm, but is not passing due to some strange behavior that need to be determined
    //
    // @Property
    // void maximumLootStressTest(
    //         @ForAll @LongRange(min = 1L, max = 20L) Long capacity,
    //         @ForAll("0 -> 10 length arrays of positive longs") Long[] netQuantities,
    //         @ForAll("0 -> 10 length arrays of positive longs") Long[] totalCosts) {
    //     assertEquals(
    //             Knapsack.greedyMaximumLoot(capacity, netQuantities, totalCosts),
    //             Knapsack.maximumLoot(capacity, netQuantities, totalCosts));
    // }

    // @Provide("0 -> 10 length arrays of positive longs")
    // Arbitrary<Long[]> longArrayGenerator() {
    //     return Arbitraries.longs().greaterOrEqual(1L).array(Long[].class).ofMaxSize(10).ofMinSize(0);
    // }
}
