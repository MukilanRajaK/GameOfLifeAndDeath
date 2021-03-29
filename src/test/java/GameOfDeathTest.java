import org.junit.jupiter.api.Test;

public class GameOfDeathTest {
    @Test
    void shouldReturnexpectedOutputForGivenSetOfInputCoordinates() {
        String input="1,1\n" + "1,2\n" + "2,1\n" + "2,2";
        GameOfDeath gameOfDeath = new GameOfDeath();
        gameOfDeath.getAliveCoOrdinates(input);
    }
}
