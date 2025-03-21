package algos;

import java.util.HashMap;
import java.util.Map;

public class RomanLiterals {

    public static Map<String, Integer> romanLiteralValues = new HashMap<String, Integer>() {
        {
            put("I", 1);
            put("IV", 4);
            put("V", 5);
            put("IX", 9);
            put("X", 10);
            put("XL", 40);
            put("L", 50);
            put("XC", 90);
            put("C", 100);
            put("CD", 400);
            put("D", 500);
            put("CM", 900);
            put("M", 1000);
        }
    };

    public static String[] romanLiteralsIncreasing = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM",
            "M" };
    public static String[] romanLiteralsDecreasing = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
            "I" };

    public static int romanTokenValue(String token) {
        return RomanLiterals.romanLiteralValues.getOrDefault(token, 0);
    }

    public static boolean tailMatches(String romanNumber, String token) {
        int charsToRemove = token.length();
        int numberLength = romanNumber.length();
        if (numberLength < charsToRemove)
            return false;
        boolean tailMatch = true;
        for (int idx = 0; idx < charsToRemove; idx++) {
            tailMatch = tailMatch && romanNumber.charAt(numberLength - 1 - idx) == token.charAt(charsToRemove - 1 - idx);
        }
        return tailMatch;
    };

    public static String removeRomanToken(String romanNumber, String token) {
        int charsToRemove = token.length();
        return romanNumber.substring(0, romanNumber.length() - charsToRemove);
    };

    public static int recursiveToDecimal(String romanNumber, int index) {
        if (romanNumber.length() == 0)
            return 0;
        if (index >= romanLiteralsIncreasing.length)
            return 0;

        String token = romanLiteralsIncreasing[index];
        if (tailMatches(romanNumber, token))
            return romanTokenValue(token) + recursiveToDecimal(removeRomanToken(romanNumber, token), index);

        return recursiveToDecimal(romanNumber, index + 1);
    }

    public static int toDecimal(String romanString) {
        return recursiveToDecimal(romanString, 0);
    }

    public static String recursiveToRomanLiteral(int num, int index) {
        if (num < 0)
            return "";
        if (index >= romanLiteralsDecreasing.length)
            return "";

        String token = romanLiteralsDecreasing[index];

        int reducedNumber = num - romanTokenValue(token);
        if (reducedNumber >= 0)
            return token + recursiveToRomanLiteral(reducedNumber, index);

        return recursiveToRomanLiteral(num, index + 1);
    }

    public static String toRomanLiteral(int num) {
        return recursiveToRomanLiteral(num, 0);
    }
}
