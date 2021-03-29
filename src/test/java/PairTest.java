import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void shouldReturnTrueIfBothPairsAreEqual() {
        Pair pair1 = new Pair(1, 2);
        Pair pair2 = new Pair(1, 2);

        boolean equals = pair1.equals(pair2);

        assertTrue(equals);
    }

    @Test
    void shouldReturnFalseIfBothPairsAreEqual() {
        Pair pair1 = new Pair(1, 2);
        Pair pair2 = new Pair(1, 4);

        boolean equals = pair1.equals(pair2);

        assertFalse(equals);
    }
}