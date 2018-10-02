package com.sudokusolver;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Sudoku {
    private static int[][] sudoku = new int[9][9];
    private static ArrayList<int[][]> sudokuGrids = new ArrayList<int[][]>();

    public Sudoku(ArrayList<TextField> a){
        initMatrix(a);
        generateGrids();
    }

    // Returns an integer matrix that can be used to solve the Sudoku
    public int[][] getSudoku(){
        return sudoku;
    }

    public static int[][] createSudoku(ArrayList<TextField> a){
        initMatrix(a);
        return sudoku;
    }

    // Returns a randomized Sudoku grid
    public static int[][] randomize(){
        if(sudokuGrids.isEmpty()){
            generateGrids();
        }
        return sudokuGrids.get((int)Math.floor(Math.random() * 5));
    }

    // Prints out the sudoku matrix in the console, for error checking purposes
    public void print(int[][] matrix){
        for(int i = 0; i < sudoku.length; i++){
            for(int j = 0; j < sudoku[i].length; j++){
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // Creates an integer matrix of a given array
    private static void initMatrix(ArrayList<TextField> a){
        int index = 0;
        // Outer loop
        for(int oRow = 0; oRow < 3; oRow++){
            for(int oCol = 0; oCol < 3; oCol++){
                // Inner loop
                for(int iRow = 0; iRow < 3; iRow++){
                    for(int iCol = 0; iCol < 3; iCol++){
                        if(index <= a.size()){
                            if(a.get(index).getText().equals("")){
                                sudoku[iRow + oRow*3][iCol + oCol*3] = 0;
                            }else{
                                sudoku[iRow + oRow*3][iCol + oCol*3] = Integer.parseInt(a.get(index).getText());
                            }
                        }
                        index++;
                    }
                }
            }
        }
    }

    // TODO: Implement a more efficient way to generate Sudokus, eg. read from a text file
    // Fills the grid array with solvable Sudoku grids
    private static void generateGrids(){
        int[][] GRID_TO_SOLVE_1 = {
                {9,0,0,1,0,0,0,0,5},
                {0,0,5,0,9,0,2,0,1},
                {8,0,0,0,4,0,0,0,0},
                {0,0,0,0,8,0,0,0,0},
                {0,0,0,7,0,0,0,0,0},
                {0,0,0,0,2,6,0,0,9},
                {2,0,0,3,0,0,0,0,6},
                {0,0,0,2,0,0,9,0,0},
                {0,0,1,9,0,4,5,7,0},
        };

        int[][] GRID_TO_SOLVE_2 = {
                {2,0,6,0,0,0,8,0,1},
                {0,3,0,0,6,0,0,2,0},
                {4,0,5,0,0,0,7,0,6},
                {0,0,0,8,0,3,0,0,0},
                {0,8,0,0,4,0,0,5,0},
                {0,0,0,1,0,6,0,0,0},
                {9,0,3,0,0,0,1,0,7},
                {0,6,0,0,2,0,0,8,0},
                {8,0,7,0,0,0,5,0,2},
        };

        int[][] GRID_TO_SOLVE_3 = {
                {5,0,8,0,0,4,7,0,6},
                {0,1,0,0,2,0,0,5,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,7,0,0,0,0,0,9},
                {0,4,0,0,6,0,0,3,0},
                {9,0,0,0,0,0,5,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,5,0,0,1,0,0,2,0},
                {1,0,3,6,0,0,8,0,7},
        };

        int[][] GRID_TO_SOLVE_4 = {
                {0,0,0,0,0,9,1,4,0},
                {0,4,0,5,0,0,0,3,2},
                {0,0,0,0,0,6,0,0,0},
                {0,0,0,0,2,0,0,0,3},
                {4,0,6,8,0,1,2,0,5},
                {7,0,0,0,9,0,0,0,0},
                {0,0,0,7,0,0,0,0,0},
                {2,6,0,0,0,4,0,9,0},
                {0,5,8,9,0,0,0,0,0},
        };

        int[][] GRID_TO_SOLVE_5 = {
                {0,3,8,0,0,0,0,0,0},
                {0,0,0,8,7,0,0,0,0},
                {5,0,0,1,0,0,8,0,0},
                {0,0,5,0,6,8,0,0,2},
                {0,0,1,0,5,0,7,0,0},
                {8,0,0,4,9,0,3,0,0},
                {0,0,2,0,0,5,0,0,9},
                {0,0,0,0,8,7,0,0,0},
                {0,0,0,0,0,0,6,1,0},
        };
        sudokuGrids.add(GRID_TO_SOLVE_1);
        sudokuGrids.add(GRID_TO_SOLVE_2);
        sudokuGrids.add(GRID_TO_SOLVE_3);
        sudokuGrids.add(GRID_TO_SOLVE_4);
        sudokuGrids.add(GRID_TO_SOLVE_5);
    }
}
