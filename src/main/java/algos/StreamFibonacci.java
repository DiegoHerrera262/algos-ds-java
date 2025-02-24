import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamFibonacci {
    public static Stream<Long[]> fibonacciRawSequence() {
        return Stream.iterate(
                new Long[] { 0L, 1L },
                s -> new Long[] { s[1], s[1] + s[0] });
    }

    public static Stream<int[]> fibonacciRawModularSequence(int equiv) {
        // Fibonacci sequence is periodic when considered in modular arithmetic
        // Hence, for efficiency, we only compute it for a period.
        // Period starts with 0, 1...
        return Stream.iterate(
                new int[] { 1, 1, 1 },
                s -> new int[] { s[1] % equiv, (s[1] + s[0]) % equiv, s[2] + 1 })
                .takeWhile(s -> !((s[0] == 0) && (s[1] == 1)));
    }

    public static LongStream fibonacciSequence() {
        return fibonacciRawSequence().mapToLong(i -> i[1]);
    }

    public static Long naiveSumOfSquares(Long index) {
        if (index == 0L)
            return 0L;
        return fibonacciSequence()
                .map(fib -> fib * fib)
                .limit(index)
                .sum();
    }

    public static Long naiveSum(Long index) {
        if (index == 0L)
            return 0L;
        return fibonacciSequence()
                .limit(index)
                .sum();
    }

    public static Long getNumber(Long index) {
        if (index == 0L)
            return 0L;
        return fibonacciSequence()
                .skip(index - 1)
                .findFirst()
                .getAsLong();
    }

    public static int getModularNumber(Long index, int equiv) {
        if (index == 0L)
            return 0;

        var period = fibonacciRawModularSequence(equiv).count() + 1L;

        // orElse(0) since if the index is not found, it is because the
        // index is multiple of the period, and thus the value is 0
        return fibonacciRawModularSequence(equiv)
                .filter(s -> s[2] == (int) (index % period))
                .map(s -> s[0])
                .findFirst()
                .orElse(0);
    }

    public static Long simpleSum(Long index) {
        if (index == 0L)
            return 0L;
        return getNumber(index + 2) - 1;
    }

    public static Long squareSum(Long index) {
        if (index == 0L)
            return 0L;
        var lastPair = fibonacciRawSequence()
                .skip(index)
                .findFirst()
                .get();
        return lastPair[0] * lastPair[1];
    }

    public static int simpleSumLastDigit(Long index) {
        if (index == 0L)
            return 0;
        var lastDigit = getModularNumber(index + 2, 10) - 1;
        return lastDigit >= 0 ? lastDigit : 9;
    }

    public static int squareSumLastDigit(Long index) {
        if (index == 0L)
            return 0;

        var period = fibonacciRawModularSequence(10).count() + 1L;

        // orElse(0) since period is divisor of index and 0 is the
        // first vallue
        return fibonacciRawModularSequence(10)
                .filter(s -> s[2] == (int) (index % period))
                .map(s -> (s[0] * s[1]) % 10)
                .findFirst()
                .orElse(0);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        Long n = scanner.nextInt();
        int m = scanner.nextInt().intValue();
        System.out.println(getModularNumber(n, m));
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

        Long nextInt() {
            return Long.parseLong(next());
        }
    }
}
