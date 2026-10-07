package algos;

import java.util.Arrays;

public class Knapsack {

    /**
     * Naive implementation that is O(N^2) due tto the fact that it is searching
     * for the most profitable available item each time the loot is calculated
     * for a taken item
     *  */

    public static Long[] unitCosts(Long[] costs, Long[] netQuantities) {
        if (costs.length != netQuantities.length)
            return new Long[] {};
        Long[] unitCostsArray = new Long[costs.length];
        for (int idx = 0; idx < costs.length; idx++) {
            unitCostsArray[idx] = costs[idx] / netQuantities[idx];
        }
        return unitCostsArray;
    }

    public static int mostProfitableAvailableItem(Long[] netQuantities, Long[] unitCosts) {
        if (netQuantities.length != unitCosts.length)
            return -1;
        int itemIdx = -1;
        Long bestUnitCost = 0L;
        for (int idx = 0; idx < netQuantities.length; idx++) {
            if (unitCosts[idx] >= bestUnitCost && netQuantities[idx] > 0L) {
                itemIdx = idx;
                bestUnitCost = unitCosts[idx];
            }
        }
        return itemIdx;
    }

    public static Long internalGreedyMaximumLoot(Long capacity, Long[] netQuantities, Long[] unitCosts) {
        if (capacity <= 0L)
            return 0L;

        if (netQuantities.length == 0 && unitCosts.length == 0)
            return 0L;

        int greedyItemIdx = mostProfitableAvailableItem(netQuantities, unitCosts);
        if (greedyItemIdx == -1)
            return -1L;

        Long takenAmount = Math.min(capacity, netQuantities[greedyItemIdx]);
        Long newCapacity = capacity - takenAmount;

        netQuantities[greedyItemIdx] = netQuantities[greedyItemIdx] - takenAmount;

        return takenAmount * unitCosts[greedyItemIdx] + internalGreedyMaximumLoot(newCapacity, netQuantities, unitCosts);
    }

    public static Long greedyMaximumLoot(Long capacity, Long[] netQuantities, Long[] netCosts) {
        Long[] unitCosts = unitCosts(netCosts, netQuantities);
        if (netQuantities.length != netCosts.length)
            return -1L;
        return internalGreedyMaximumLoot(capacity, netQuantities, unitCosts);
    }

    /**
     * More efficient implementation that uses the fact that the best approach is by
     * using the most unit-costly available item first completely for building the loot */

    static class KnapsackItem {
        Long totalAmount;
        Long unitCost;

        public KnapsackItem(Long total_amount, Long total_cost) {
            totalAmount = total_amount;
            unitCost = total_amount > 0 ? total_cost / total_amount : 0;
        }
    }

    public static Long maximumLootWithSortedItems(Long capacity, int currentItem, KnapsackItem[] itemsToTake) {
        if (capacity <= 0 || currentItem >= itemsToTake.length) return 0L;
        Long quantityTaken = Math.min(capacity, itemsToTake[currentItem].totalAmount);
        return (quantityTaken * itemsToTake[currentItem].unitCost) + maximumLootWithSortedItems(capacity - quantityTaken, currentItem + 1, itemsToTake);
    }

    public static Long fastMaximumLoot(Long capacity, KnapsackItem[] itemsToTake) {
        Arrays.sort(itemsToTake, (i1, i2) -> -Long.compare(i1.unitCost, i2.unitCost));
        return maximumLootWithSortedItems(capacity, 0, itemsToTake);
    }

    public static Long maximumLoot(Long capacity, Long[] netQuantities, Long[] netCosts) {
        if (netQuantities.length != netCosts.length) return -1L;
        KnapsackItem[] itemsToTake = new KnapsackItem[netQuantities.length];
        for (int idx = 0; idx < netQuantities.length; idx++) {
            itemsToTake[idx] = new KnapsackItem(netQuantities[idx], netCosts[idx]);
        }
        return fastMaximumLoot(capacity, itemsToTake);
    }
}
