package algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoinChange {
    public static int[] COIN_DENOMINATIONS = { 10, 5, 1 };


    public static int LargestAvailableCoin(int n, int[] coinDenominations) {
        int largestDenomination = -1;
        for (int idx = 0; idx < coinDenominations.length; idx++) {
            if (coinDenominations[idx] <= n) {
                largestDenomination = coinDenominations[idx];
                break;
            }
        }
        return largestDenomination;
    }

    public static int OptimalCoinChange(int n, int[] coinDenominations) {
        if (n == 0) return 0;
        int bestDenomination = LargestAvailableCoin(n, coinDenominations);
        if (bestDenomination <= 0) return bestDenomination;
        return 1 + OptimalCoinChange(n - bestDenomination, coinDenominations);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        System.out.println(OptimalCoinChange(n, COIN_DENOMINATIONS));
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
