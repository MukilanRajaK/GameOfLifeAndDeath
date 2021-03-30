import java.util.HashSet;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class GameOfDeath {
    private HashSet<Pair> coOrdinates =new HashSet<>();
    private int maxColumn;
    private int minColumn;
    private int maxRow;
    private int minRow;

    public GameOfDeath()
    {
        minRow= MAX_VALUE;
        maxRow=MIN_VALUE;
        minColumn=MAX_VALUE;
        maxColumn=MIN_VALUE;
    }

    String getAliveCoOrdinates(String input)
    {
        String[] pairString = input.split("\n");
        setAliveCoOrdinates(pairString);
        findMaxAndMinOfRowAndColumn();
        matrixSetupAndProcessingGameOfLife();
        for(Pair coOrdinatePair:coOrdinates)
        {
            System.out.println(coOrdinatePair.row+" "+coOrdinatePair.column);
        }
        return "";
    }

    private void matrixSetupAndProcessingGameOfLife() {
        int totalNoOfRows=maxRow-minRow+3;
        int totalNoOfColumns=maxColumn-minColumn+3;
        int rowPadding=0;
        int columnPadding=0;
        if(minRow<=0 && minColumn<=0)
        {
            rowPadding=(-1*minRow)+1;
            columnPadding=(-1*minColumn)+1;
        }
        else if(minRow<=0)
        {
            rowPadding=(-1*minRow)+1;
            columnPadding=1;
        }
        else if(minColumn<=0)
        {
            columnPadding=(-1*minColumn)+1;
            rowPadding=1;
        }
        int[][] mapMatrix =new int[totalNoOfRows][totalNoOfColumns];
        int[][] nextMapMatrix =new int[totalNoOfRows][totalNoOfColumns];
        for (Pair coOrdinatePair:coOrdinates){
            mapMatrix[coOrdinatePair.row+rowPadding][coOrdinatePair.column+columnPadding]=1;
            System.out.println(coOrdinatePair.row+" "+coOrdinatePair.column);
        }
        for(int rows=0;rows<totalNoOfRows;rows++)
        {
            for(int columns=0;columns<totalNoOfColumns;columns++)
            {
                int neighbours=getNoOfNeighbours(rows,columns,totalNoOfRows,totalNoOfColumns,mapMatrix);
                if(neighbours==3)
                {
                    nextMapMatrix[rows][columns]=1;
                    coOrdinates.add(new Pair(rows-rowPadding,columns-columnPadding));
                }
                else if(neighbours<2 || neighbours>3)
                {
                    nextMapMatrix[rows][columns]=0;
                    coOrdinates.remove(new Pair(rows-rowPadding,columns-columnPadding));
                }
                else
                {
                    if(mapMatrix[rows][columns]==1) {
                        nextMapMatrix[rows][columns] = 1;
                    }
                }
                System.out.print(neighbours+" ");
            }
            System.out.println();
        }
        for(int i=0;i<totalNoOfRows;i++)
        {
            for(int j=0;j<totalNoOfColumns;j++)
            {
                System.out.print(mapMatrix[i][j]+"->"+nextMapMatrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    private int getNoOfNeighbours(int row, int column, int totalNoOfRows, int totalNoOfColumns, int[][] mapMatrix) {
        int neighbours=0;
        neighbours=neighbours+checkNeighbour(row+1,column-1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row+1,column,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row+1,column+1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row,column-1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row,column+1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row-1,column-1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row-1,column,totalNoOfRows,totalNoOfColumns,mapMatrix);
        neighbours=neighbours+checkNeighbour(row-1,column+1,totalNoOfRows,totalNoOfColumns,mapMatrix);
        return neighbours;
    }

    private int checkNeighbour(int row, int column, int totalNoOfRows, int totalNoOfColumns, int[][] mapMatrix) {
        if((row>=0 && row<totalNoOfRows) && (column>=0 && column<totalNoOfColumns))
        {
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
        System.out.println(maxColumn+" "+minColumn+" "+maxRow+" "+minRow);
    }

    private void setAliveCoOrdinates(String[] pairString) {
        for (String temporary : pairString) {
            String[] stringRowColumn = temporary.split(",");
            Pair pair = new Pair(Integer.parseInt(stringRowColumn[0]), Integer.parseInt(stringRowColumn[1]));
            coOrdinates.add(pair);
        }
    }
}
