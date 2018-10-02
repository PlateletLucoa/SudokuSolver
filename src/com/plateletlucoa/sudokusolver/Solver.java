package com.plateletlucoa.sudokusolver;

public class Solver {
    private Sudoku sudoku;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    // Check if a possible number is already in a row
    private boolean isInRow(int row, int number) {
        for(int i = 0; i < sudoku.getRowSize(); i++) {
            if(sudoku.getValue(row, i) == number) {
                return true;
            }
        }
        return false;
    }

    // Check if a possible number is already in a col
    private boolean isInCol(int col, int number) {
        for(int i = 0; i < sudoku.getColSize(); i++) {
            if(sudoku.getValue(i, col) == number) {
                return true;
            }
        }
        return false;
    }

    // Check if a possible number is in it's 3x3 box
    private boolean isInBox(int row, int col, int number){
        int r = row - row % 3;
        int c = col - col % 3;

        for(int i = r; i < r + 3; i++){
            for(int j = c; j < c + 3; j++){
                if(sudoku.getValue(i, j) == number){
                    return true;
                }
            }
        }
        return false;
    }

    // Combined method to check if a number possible to a [row][col] position is ok
    private boolean isOk(int row, int col, int number){
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    // Check if the sudoku grid has been correctly set up before solving
    public boolean validGrid(){
        for(int row = 0; row < sudoku.getRowSize(); row++){
            for(int col = 0; col < sudoku.getColSize(); col++){
                int number = sudoku.getValue(row, col);
                if(number != 0){
                    sudoku.insertValue(row, col, 0);
                    if(!isOk(row, col, number)){
                        return false;
                    }
                    sudoku.insertValue(row, col, number);
                }
            }
        }
        return true;
    }

    // Recursive method for solving
    public boolean solve(){
        for(int row = 0; row < sudoku.getRowSize(); row++){
            for(int col = 0; col < sudoku.getColSize(); col++){
                // Search an empty cell
                if(sudoku.getValue(row, col) == 0){
                    // Try possible numbers
                    for(int number = 1; number <= 9; number++){
                        if(isOk(row, col, number)){
                            // Number is ok. Add it to the grid
                            sudoku.insertValue(row, col, number);

                            // Start backtracking
                            if(solve()){
                                return true;
                            }else{
                                sudoku.insertValue(row, col, 0);
                            }
                        }
                    }
                    return false;   // sudoku unsolvable if we reach this point
                }
            }
        }
        return true;    // sudoku solved if we reach this point
    }
}
