package utils;

/**
 * Created by Kovvah on 28/12/2017.
 */
public enum LifePatterns {
    Random(0,0),
    Turtle(2,3);

    int rows;
    int columns;

    LifePatterns(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
