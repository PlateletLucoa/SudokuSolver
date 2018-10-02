package com.plateletlucoa.sudokusolver;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Sudoku {
    private int[][] grid;

    public Sudoku(ArrayList<TextField> a) {
        initGrid(a, a.size());
    }

    public int getRowSize() {
        return grid.length;
    }

    public int getColSize() {
        return grid[0].length;
    }

    // Retrieves a value from the sudoku grid
    public int getValue(int row, int col) {
        return grid[row][col];
    }

    // Inserts a value to the sudoku grid
    public void insertValue(int row, int col, int val) {
        if(val < 0 || val > 9) {
            throw new IllegalArgumentException();
        } else {
            grid[row][col] = val;
        }
    }

    // Updates the sudoku grid with new values from the GUI
    public void updateValues(ArrayList<TextField> a){
        initGrid(a, a.size());
    }

    // Getter for the grid matrix
    public int[][] display() {
        return grid;
    }

    // TODO: Fills the grid with values from a predefined sudoku puzzle
    public int[][] randomizeGrid() {
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

        ArrayList<int[][]> grids = new ArrayList<int[][]>();
        grids.add(GRID_TO_SOLVE_1);
        grids.add(GRID_TO_SOLVE_2);
        grids.add(GRID_TO_SOLVE_3);
        grids.add(GRID_TO_SOLVE_4);
        grids.add(GRID_TO_SOLVE_5);

        return grids.get((int)Math.floor(Math.random() * 5));
    }

    private void initGrid(ArrayList<TextField> a, int gridSize) {
        // 9x9 grid
        if(gridSize == 81) {
            grid = new int[9][9];
            int index = 0;
            // Outer loop
            for(int oRow = 0; oRow < 3; oRow++) {
                for(int oCol = 0; oCol < 3; oCol++) {
                    // Inner loop
                    for(int iRow = 0; iRow < 3; iRow++) {
                        for(int iCol = 0; iCol < 3; iCol++) {
                            if(index <= gridSize) {
                                if(a.get(index).getText().equals("")) {
                                    grid[iRow + oRow*3][iCol + oCol*3] = 0;
                                } else {
                                    grid[iRow + oRow*3][iCol + oCol*3] = Integer.parseInt(a.get(index).getText());
                                }
                            }
                            index++;
                        }
                    }
                }
            }
        } else if(gridSize == 9) {
            grid = new int[3][3];
            int index = 0;
            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    if(index <= gridSize) {
                        if(a.get(index).getText().equals("")) {
                            grid[row][col] = 0;
                        } else {
                            grid[row][col] = Integer.parseInt(a.get(index).getText());
                        }
                    }
                    index++;
                }
            }
        } else {
            // If reached then an illegal grid size was entered
            throw new IllegalArgumentException();
        }
    }

}


