package com.sudokusolver;

public class Solver {
    private int[][] matrix;

    public Solver(int[][] matrix){
        this.matrix = matrix;
    }

    // Check if a possible number is already in a row
    private boolean isInRow(int row, int number){
        for(int i = 0; i < 9; i++){
            if(matrix[row][i] == number){
                return true;
            }
        }
        return false;
    }

    // Check if a possible number is already in a col
    private boolean isInCol(int col, int number){
        for(int i = 0; i < 9; i++){
            if(matrix[i][col] == number){
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
                if(matrix[i][j] == number){
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

    // Check if the Sudoku is valid before solving
    public boolean isValid(){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                int number = matrix[row][col];
                if(number != 0){
                    matrix[row][col] = 0;
                    if(!isOk(row, col, number)){
                        return false;
                    }
                    matrix[row][col] = number;
                }
            }
        }
        return true;
    }

    // Recursive method for solving
    public boolean solve(){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                // Search an empty cell
                if(matrix[row][col] == 0){
                    // Try possible numbers
                    for(int number = 1; number <= 9; number++){
                        if(isOk(row, col, number)){
                            // Number is ok. Add it to the grid
                            matrix[row][col] = number;

                            // Start backtracking
                            if(solve()){
                                return true;
                            }else{
                                matrix[row][col] = 0;
                            }
                        }
                    }
                    return false;   // sudoku unsolvable if we reach this point
                }
            }
        }
//        Sudoku.printSudoku(matrix);
        return true;    // sudoku solved if we reach this point
    }

    public int[][] display(){
        return matrix;
    }
}
