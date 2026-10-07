package algos;

import java.util.Scanner;

/**
 * TwoSum
 */
public class TwoSum {
    public static Integer addTwo(Integer a, Integer b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();
        System.out.println(addTwo(a, b));
        scanner.close();
    }
}
