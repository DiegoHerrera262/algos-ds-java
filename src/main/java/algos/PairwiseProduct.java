package algos;

import java.util.Scanner;

public class PairwiseProduct {

    public static long naiveMaxProduct(long[] numberList) {
        if (numberList.length < 2) {
            return -1L;
        }

        long maxProduct = -1L;

        for(int idx = 0; idx < numberList.length; idx++) {
            for (int jdx = 0; jdx < idx; jdx ++) {
                long pairProduct = numberList[idx] * numberList[jdx];
                if (pairProduct > maxProduct) maxProduct = pairProduct;
            }
        }

        return maxProduct;
    }

    public static long maxProduct(long[] numberList) {
        if (numberList.length < 2) {
            return -1L;
        }

        long maxValue = -1L;
        long secondMaxValue = -1L;

        for (long num : numberList) {
            if (num >= maxValue) {
                secondMaxValue = maxValue;
                maxValue = num;
            } else if (num >= secondMaxValue) {
                secondMaxValue = num;
            }
        }

        return secondMaxValue * maxValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextLong();
        }
        System.out.println(maxProduct(numbers));
        scanner.close();
    }
}
