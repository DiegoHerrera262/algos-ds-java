package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.*;

public class StreamFibonacciTest {

    @Test
    void whenValidatingSimpleNumbers() {
        assertEquals(0L, StreamFibonacci.getNumber(0L));
        assertEquals(1L, StreamFibonacci.getNumber(1L));
        assertEquals(2L, StreamFibonacci.getNumber(3L));
        assertEquals(55L, StreamFibonacci.getNumber(10L));
        assertEquals(1134903170L, StreamFibonacci.getNumber(45L));
        assertEquals(1548008755920L, StreamFibonacci.getNumber(60L));
    }

    @Test
    void whenValidatingModularNumbers() {
        assertEquals(4, StreamFibonacci.getModularNumber(9L, 10));
        assertEquals(5, StreamFibonacci.getModularNumber(10L, 10));
        assertEquals(7, StreamFibonacci.getModularNumber(14L, 10));
        assertEquals(885, StreamFibonacci.getModularNumber(115L, 1000));
        assertEquals(151, StreamFibonacci.getModularNumber(2816213588L, 239));
    }

    @Property
    void equivalentToStreamFibonacciLastDigit(@ForAll @LongRange(min = 0L, max = 65L) Long anIndex) {
        assertEquals(StreamFibonacci.getNumber(anIndex) % 10L,
                StreamFibonacci.getModularNumber(anIndex, 10));
    }

    @Property
    void equivalentToNaiveSum(@ForAll @LongRange(min = 0L, max = 65L) Long anIndex) {
        assertEquals(StreamFibonacci.naiveSum(anIndex), StreamFibonacci.simpleSum(anIndex));
    }

    @Property
    void equivalentToNaiveSquareSum(@ForAll @LongRange(min = 0L, max = 65L) Long anIndex) {
        assertEquals(StreamFibonacci.naiveSumOfSquares(anIndex), StreamFibonacci.squareSum(anIndex));
    }

    @Property
    void lastDigitEquivalentSum(@ForAll @LongRange(min = 0L, max = 65L) Long anIndex) {
        assertEquals(StreamFibonacci.simpleSum(anIndex) % 10L,
                StreamFibonacci.simpleSumLastDigit(anIndex));
    }

    @Property
    void lastDigitEquivalentSquareSum(@ForAll @LongRange(min = 0L, max = 45L) Long anIndex) {
        assertEquals(StreamFibonacci.squareSum(anIndex) % 10L,
                StreamFibonacci.squareSumLastDigit(anIndex));
    }
}
