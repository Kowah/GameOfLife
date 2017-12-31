package game;

import java.io.PrintStream;
import java.io.Serializable;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class Game implements Serializable {

    private int rows;
    private int columns;
    private int survive;

    private boolean[][] grid;
    private boolean[][] previousGrid;

    public Game(int rows, int columns, int survive){
        this.rows = rows;
        this.columns = columns;
        this.survive = survive;
        grid = new boolean[rows][columns];
    }

    public void generateRandomly(int numbers){
        int numbersCommited = 0;
        if(numbers>rows*columns)
            numbers=rows*columns;
        while(numbersCommited != numbers){
            int x = (int) (Math.random()* rows);
            int y = (int) (Math.random()* columns);
            if (!grid[x][y]){
                grid[x][y] = true;
                numbersCommited++;
            }
        }
        System.out.println(numbersCommited);
    }
    public void generateFigure(){
        /* Grenouille */
        /*
        grid[2][2] = true;
        grid[2][3] = true;
        grid[2][4] = true;
        grid[1][1] = true;
        grid[1][2] = true;
        grid[1][3] = true;
        */
        grid[1][2] = true;
        grid[2][3] = true;
        grid[3][1] = true;
        grid[3][2] = true;
        grid[3][3] = true;
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public boolean[][] getGrid() {
        return grid;
    }

    public void evolve(){
        boolean[][] nextGrid = new boolean[rows][columns];
        for(int row=0; row<rows;row++){
            for(int col=0; col<columns; col++){
                int neighbour = countNeighbourhood(row,col);
                if(neighbour==3){
                    nextGrid[row][col] = true;
                }
                else if(neighbour==2){
                    nextGrid[row][col] = grid[row][col];
                }
                else {
                    nextGrid[row][col] = false;
                }
            }
        }
        previousGrid = grid;
        grid = nextGrid;
    }

    public int countNeighbourhood(int row, int column){
        int count = 0;
        if(row>0){
            count += evaluateCell(grid[row-1][column]);
        }
        if(row<rows-1){
            count += evaluateCell(grid[row+1][column]);
        }
        if(column>0){
            count += evaluateCell(grid[row][column-1]);
        }
        if(column<columns-1){
            count += evaluateCell(grid[row][column+1]);
        }
        if(row>0 && column>0){
            count += evaluateCell(grid[row-1][column-1]);
        }
        if(row>0 && column<columns-1){
            count += evaluateCell(grid[row-1][column+1]);
        }
        if(row<rows-1 && column>0){
            count += evaluateCell(grid[row+1][column-1]);
        }
        if(row<rows-1 && column<columns-1){
            count += evaluateCell(grid[row+1][column+1]);
        }
        return count;
    }

    public int evaluateCell(boolean cell){
        return cell?1:0;
    }

    public boolean gameIsBlocked(){
        if(previousGrid == null)
            return false;
        for(int row=0; row<rows;row++) {
            for (int col = 0; col < columns; col++) {
                if(grid[row][col] != previousGrid[row][col])
                    return false;
            }
        }
        return true;
    }

    public boolean gameIsEmpty(){
        for(int row=0; row<rows;row++) {
            for (int col = 0; col < columns; col++) {
                if(grid[row][col])
                    return false;
            }
        }
        return true;
    }

    public boolean thereIsLife(){
        return !gameIsEmpty() && !gameIsBlocked();
    }

    public int getSurvive() {
        return survive;
    }
}
