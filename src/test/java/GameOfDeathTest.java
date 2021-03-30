import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfDeathTest {
    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet1() {
        String input = "-1,0\n-1,1\n-1,2";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);

        assertEquals("0, 1\n-1, 1\n-2, 1\n", output);
    }

    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet2() {
        String input = "1,1\n1,2\n2,1\n2,2";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);

        assertEquals("2, 1\n1, 1\n2, 2\n1, 2\n", output);
    }

    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet3() {
        String input = "1,1\n1,2\n1,0";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);

        assertEquals("2, 1\n1, 1\n0, 1\n", output);
    }

    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet4() {
        String input = "0,1\n1,2\n1,0\n2,1\n0,2";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);

        assertEquals("1, 0\n2, 1\n0, 1\n1, 2\n0, 2\n", output);
    }

    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet5() {
        String input = "1,1\n1,2\n1,3\n2,2\n2,3\n2,4\n";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);
        assertEquals("2, 1\n1, 1\n3, 3\n2, 4\n0, 2\n1, 4\n", output);
    }

    @Test
    void shouldReturnExpectedOutputForGivenSetOfInputCoordinatesSet6() {
        String input = "-1,-1\n-1,-2\n-1,-3\n-1,-4\n-1,0\n";
        GameOfDeath gameOfDeath = new GameOfDeath();

        String output = gameOfDeath.getAliveCoOrdinates(input);
        assertEquals("-1, -2\n-2, -3\n0, -1\n-1, -1\n-2, -2\n-2, -1\n0, -3\n-1, -3\n0, -2\n", output);
    }

}
