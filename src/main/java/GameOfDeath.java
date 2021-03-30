import java.util.HashSet;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class GameOfDeath {
    public static final int zeroPaddingInMatrix = 3;
    public static final int leaveBoundariesUntouched = 1;
    private final HashSet<Pair> coOrdinates = new HashSet<>();
    private int maxColumn;
    private int minColumn;
    private int maxRow;
    private int minRow;

    public GameOfDeath() {
        minRow = MAX_VALUE;
        maxRow = MIN_VALUE;
        minColumn = MAX_VALUE;
        maxColumn = MIN_VALUE;
    }

    String getAliveCoOrdinates(String input) {
        String[] pairString = input.split("\n");
        setAliveCoOrdinates(pairString);
        findMaxAndMinOfRowAndColumn();
        matrixSetupAndProcessingGameOfLife();
        return returnPairCoOrdinatesInString();

    }

    private String returnPairCoOrdinatesInString() {
        StringBuilder pairCoOrdinatesInString = new StringBuilder();
        for (Pair coOrdinatePair : coOrdinates) {
            pairCoOrdinatesInString.append(coOrdinatePair.row).append(", ").append(coOrdinatePair.column).append("\n");
        }
        return pairCoOrdinatesInString.toString();
    }

    private void matrixSetupAndProcessingGameOfLife() {
        int totalNoOfRows = maxRow - minRow + zeroPaddingInMatrix;
        int totalNoOfColumns = maxColumn - minColumn + zeroPaddingInMatrix;
        int rowPadding = 0;
        int columnPadding = 0;
        if (minRow <= 0 && minColumn <= 0) {
            rowPadding = (-1 * minRow) + leaveBoundariesUntouched;
            columnPadding = (-1 * minColumn) + leaveBoundariesUntouched;
        } else if (minRow <= 0) {
            rowPadding = (-1 * minRow) + leaveBoundariesUntouched;
        } else if (minColumn <= 0) {
            columnPadding = (-1 * minColumn) + leaveBoundariesUntouched;
        }
        int[][] mapMatrix = new int[totalNoOfRows][totalNoOfColumns];
        for (Pair coOrdinatePair : coOrdinates) {
            mapMatrix[coOrdinatePair.row + rowPadding][coOrdinatePair.column + columnPadding] = 1;
        }
        for (int rows = 0; rows < totalNoOfRows; rows++) {
            for (int columns = 0; columns < totalNoOfColumns; columns++) {
                int neighbours = getNoOfNeighbours(rows, columns, totalNoOfRows, totalNoOfColumns, mapMatrix);
                if (neighbours == 3) {
                    coOrdinates.add(new Pair(rows - rowPadding, columns - columnPadding));
                } else if (neighbours < 2 || neighbours > 3) {
                    coOrdinates.remove(new Pair(rows - rowPadding, columns - columnPadding));
                }
            }
        }

    }

    private int getNoOfNeighbours(int row, int column, int totalNoOfRows, int totalNoOfColumns, int[][] mapMatrix) {
        int neighbours = 0;
        int checkLowerLeftNeighbour = checkNeighbour(row + 1, column - 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkLowerLeftNeighbour;
        int checkLowerNeighbour = checkNeighbour(row + 1, column, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkLowerNeighbour;
        int checkLowerRightNeighbour = checkNeighbour(row + 1, column + 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkLowerRightNeighbour;
        int checkLeftNeighbour = checkNeighbour(row, column - 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkLeftNeighbour;
        int checkRightNeighbour = checkNeighbour(row, column + 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkRightNeighbour;
        int checkUpperLeftNeighbour = checkNeighbour(row - 1, column - 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkUpperLeftNeighbour;
        int checkUpperNeighbour = checkNeighbour(row - 1, column, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkUpperNeighbour;
        int checkUpperRightNeighbour = checkNeighbour(row - 1, column + 1, totalNoOfRows, totalNoOfColumns, mapMatrix);
        neighbours = neighbours + checkUpperRightNeighbour;
        return neighbours;
    }

    private int checkNeighbour(int row, int column, int totalNoOfRows, int totalNoOfColumns, int[][] mapMatrix) {
        if ((row >= 0 && row < totalNoOfRows) && (column >= 0 && column < totalNoOfColumns)) {
            return mapMatrix[row][column];
        }
        return 0;
    }

    private void findMaxAndMinOfRowAndColumn() {
        for (Pair currentCoOrdinate : coOrdinates) {
            if (currentCoOrdinate.row < minRow) {
                minRow = currentCoOrdinate.row;
            }
            if (currentCoOrdinate.row > maxRow) {
                maxRow = currentCoOrdinate.row;
            }
            if (currentCoOrdinate.column < minColumn) {
                minColumn = currentCoOrdinate.column;
            }
            if (currentCoOrdinate.column > maxColumn) {
                maxColumn = currentCoOrdinate.column;
            }
        }
    }

    private void setAliveCoOrdinates(String[] pairString) {
        for (String temporary : pairString) {
            String[] stringRowColumn = temporary.split(",");
            Pair pair = new Pair(Integer.parseInt(stringRowColumn[0]), Integer.parseInt(stringRowColumn[1]));
            coOrdinates.add(pair);
        }
    }
}
