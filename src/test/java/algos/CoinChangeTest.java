package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CoinChangeTest {
    @Test
    void whenTestingLargestAvailableCoin() {
        // When the change is larger than 10
        assertEquals(10, CoinChange.LargestAvailableCoin(100, CoinChange.COIN_DENOMINATIONS));
        assertEquals(10, CoinChange.LargestAvailableCoin(10, CoinChange.COIN_DENOMINATIONS));
        // When the change is smaller than 10 but larger than or equal to 5
        assertEquals(5, CoinChange.LargestAvailableCoin(9, CoinChange.COIN_DENOMINATIONS));
        assertEquals(5, CoinChange.LargestAvailableCoin(5, CoinChange.COIN_DENOMINATIONS));
        // When the change is smaller than 5 but larger than or equal to 1
        assertEquals(1, CoinChange.LargestAvailableCoin(4, CoinChange.COIN_DENOMINATIONS));
        assertEquals(1, CoinChange.LargestAvailableCoin(1, CoinChange.COIN_DENOMINATIONS));
        // When the change is smaller than 1
        assertEquals(-1, CoinChange.LargestAvailableCoin(0, CoinChange.COIN_DENOMINATIONS));
    }

    @Test
    void whenValidatingSimpleCases() {
        assertEquals(0, CoinChange.OptimalCoinChange(0, CoinChange.COIN_DENOMINATIONS));
        assertEquals(2, CoinChange.OptimalCoinChange(2, CoinChange.COIN_DENOMINATIONS));
        assertEquals(6, CoinChange.OptimalCoinChange(28, CoinChange.COIN_DENOMINATIONS));
        assertEquals(10, CoinChange.OptimalCoinChange(100, CoinChange.COIN_DENOMINATIONS));
        assertEquals(3, CoinChange.OptimalCoinChange(25, CoinChange.COIN_DENOMINATIONS));
    }
}
