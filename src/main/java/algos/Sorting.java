package algos;

import java.util.Arrays;

public class Sorting {

    public static void insertInSorted (Float[] partiallySorted, int untilIdx) {
        if (untilIdx == 0) return;
        Float sortingValue = partiallySorted[untilIdx];
        for (int idx = untilIdx - 1; idx >= 0; idx--) {
            if (partiallySorted[idx] > partiallySorted[idx + 1]) {
                partiallySorted[idx + 1] = partiallySorted[idx];
                partiallySorted[idx] = sortingValue;
            }
        }
    }

    public static Float[] insertionSort (Float[] toSort) {
        Float[] toSortCopy = toSort.clone();
        for (int idx = 0; idx < toSort.length; idx++) {
            insertInSorted(toSortCopy, idx);
        }
        return toSortCopy;
    }

    public static void merge(Float[] splitSorted, int p, int q, int r) {
        if (p == q && q == r) return;
        Float[] leftArray = Arrays.copyOfRange(splitSorted, p, q + 1);
        Float[] rightArray = Arrays.copyOfRange(splitSorted, q + 1, r + 1);
        int idxToInsertLeft = 0;
        int idxToInsertRight = 0;
        for (int idx = p; idx <= r; idx++) {
            Boolean rightInBound = idxToInsertRight < rightArray.length;
            Boolean leftInBound = idxToInsertLeft < leftArray.length;
            if (!leftInBound) {
                splitSorted[idx] = rightArray[idxToInsertRight];
                idxToInsertRight++;
            }
            if (!rightInBound) {
                splitSorted[idx] = leftArray[idxToInsertLeft];
                idxToInsertLeft++;
            }
            if (leftInBound && rightInBound) {
                Float minLeft = leftArray[idxToInsertLeft];
                Float minRight = rightArray[idxToInsertRight];
                if (minLeft < minRight) {
                    splitSorted[idx] = minLeft;
                    idxToInsertLeft++;
                } else {
                    splitSorted[idx] = minRight;
                    idxToInsertRight++;
                }
            }
        }
    }

    public static void mergeSortInPlace(Float[] toSort, int p, int r) {
        if (p >= r) return;
        int q = (int) (p + r) / 2;
        mergeSortInPlace(toSort, p, q);
        mergeSortInPlace(toSort, q + 1, r);
        merge(toSort, p, q, r);
    }

    public static Float[] mergeSort(Float[] toSort) {
        Float[] toSortCopy = toSort.clone();
        mergeSortInPlace(toSortCopy, 0, toSortCopy.length - 1);
        return toSortCopy;
    }
}
