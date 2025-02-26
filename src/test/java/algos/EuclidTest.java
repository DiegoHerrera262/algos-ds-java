package algos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.*;

public class EuclidTest {

    @Test
    void whenValidatingGdcForSomeValues() {
        assertEquals(21L, Euclid.gcd(1071L, 462L));
        assertEquals(4L, Euclid.gcd(12L, 4L));
        assertEquals(17657L, Euclid.gcd(28851538L, 1183019L));
    }

    @Test
    void whenValidatingMcmForSomeValues() {
        assertEquals(12L, Euclid.mcm(3L, 4L));
        assertEquals(6L, Euclid.mcm(6L, 3L));
        assertEquals(467970912861L, Euclid.mcm(761457L, 614573L));
    }

    @Property
    void gdcStressTest(@ForAll @LongRange(min = 1, max = 1000) Long n,
            @ForAll @LongRange(min = 1, max = 1000L) Long m) {
        assertEquals(Euclid.naiveGdc(n, m), Euclid.gcd(n, m));
    }

    @Property
    void mcmStressTest(@ForAll @LongRange(min = 1, max = 1000) Long n,
            @ForAll @LongRange(min = 1, max = 1000) Long m) {
        assertEquals(Euclid.naiveMcm(n, m), Euclid.mcm(n, m));
    }
}
