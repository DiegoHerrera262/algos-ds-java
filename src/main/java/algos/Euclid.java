package algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Euclid {
    public static Long gcd(Long n, Long m) {
        return Stream.iterate(
                new Long[] { Math.max(n, m), Math.min(n, m) },
                s -> new Long[] { s[1], s[0] % s[1] })
            .dropWhile(s -> s[1] != 0L)
            .mapToLong(s -> s[0])
            .findFirst()
            .getAsLong();
    }

    public static Long naiveGdc(Long n, Long m) {
        if (n == 1L || m == 1L) return 1L;
        return LongStream.range(2, n + m)
            .filter(s -> (n % s) == 0L && (m % s) == 0L)
            .reduce((first, last) -> last)
            .orElse(1);
    }

    public static Long mcm(Long n, Long m) {
        if (n == 0L || m == 0L) return 0L;
        return (n * m) / gcd(n, m);
    }

    public static Long naiveMcm(Long n, Long m) {
        return LongStream.range(Math.min(n, m), n * m + 1)
            .filter(s -> (s % n) == 0L && (s % m) == 0L)
            .findFirst()
            .getAsLong();
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        Long n = scanner.nextInt();
        Long m = scanner.nextInt();
        System.out.println(mcm(n, m));
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
