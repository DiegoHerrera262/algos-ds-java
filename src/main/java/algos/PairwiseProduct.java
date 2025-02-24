package algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PairwiseProduct {
    /**
     * This method simply computes the max of an array
     * while ignoring the elemnt at given index. If no
     * index is given, it is equivalent to max
     */
    public static Long[] maxValExcludingIndex(Long[] numberList, int... index) {
        // if no index, set to -1 since no array index can be = to a neg number
        var excludeIndex = index.length > 0 ? index[0] : -1;
        var maxIndex = excludeIndex == 0 ? 1 : 0;

        if (numberList.length == 0 || (numberList.length == 1 && maxIndex == 1)) {
            return new Long[] {};
        }

        var maxValue = numberList[maxIndex];

        for (int i = 0; i < numberList.length; i++) {
            if (numberList[i] >= maxValue && i != excludeIndex) {
                maxIndex = i;
                maxValue = numberList[i];
            }
        }

        return new Long[] { (long) maxIndex, maxValue };
    }

    public static Long naiveMaxProduct(Long[] numberList) {
        var anotherNumberList = Arrays.copyOf(numberList, numberList.length);
        Arrays.sort(anotherNumberList);

        if (anotherNumberList.length == 0) {
            return 0L;
        }

        if (anotherNumberList.length == 1) {
            return anotherNumberList[0];
        }

        return anotherNumberList[numberList.length - 1] * anotherNumberList[numberList.length - 2];
    }

    public static Long maxProduct(Long[] numberList) {
        if (numberList.length <= 1) {
            return 0L;
        }

        var maxTuple = maxValExcludingIndex(numberList);
        var secondMaxTuple = maxValExcludingIndex(numberList, maxTuple[0].intValue());

        return maxTuple[1] * secondMaxTuple[1];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        Long n = scanner.nextLong();
        Long[] numbers = new Long[n.intValue()];
        for (int i = 0; i < n; i++) {
            numbers[i] = (long) scanner.nextLong();
        }
        System.out.println(maxProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Long nextLong() {
            return Long.parseLong(next());
        }
    }
}
