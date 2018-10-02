package com.plateletlucoa.sudokusolver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {
    private Stage primaryStage;
    private ArrayList<TextField> nbrInputs = new ArrayList<TextField>();
    private Sudoku sudoku;
    private Solver solver;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sudoku");
        setupGUI(this.primaryStage, nbrInputs);
        sudoku = new Sudoku(nbrInputs);
        solver = new Solver(sudoku);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    // Creates the UI for the Sudoku solver and prepares input values for the solver
    private void setupGUI(Stage stage, ArrayList<TextField> inputs) {
        HBox hbox = new HBox();
        GridPane outerGrid = new GridPane();

        hbox.setPadding(new Insets(5, 0, 15, 7));
        hbox.setSpacing(15);
        outerGrid.setPadding(new Insets(5, 5, 5, 5));
        for(int oRow = 0; oRow < 3; oRow++){
            for(int oCol = 0; oCol < 3; oCol++){
                GridPane innerGrid = new GridPane();
                innerGrid.setPadding(new Insets(2, 2, 2, 2));
                for(int iRow = 0; iRow < 3; iRow++){
                    for(int iCol = 0; iCol < 3; iCol++){
                        TextField nbrInput = new OneNumberTextField();
                        if(((oRow == 0 || oRow == 2) && oCol != 1) || oRow == 1 && oCol == 1){
                            nbrInput.setStyle("-fx-control-inner-background: orange;");
                        }
                        inputs.add(nbrInput);
                        innerGrid.add(nbrInput, iCol, iRow);
                    }
                }
                outerGrid.add(innerGrid, oCol, oRow);
            }
        }

        Button solveBtn = new Button("Solve");
        Button clearBtn = new Button("Clear");
        Button randomBtn = new Button("Randomize");
        solveBtn.setOnAction(e -> solve());
        clearBtn.setOnAction(e -> clear());
        randomBtn.setOnAction(e -> randomize());
        hbox.getChildren().addAll(solveBtn, clearBtn, randomBtn);

        BorderPane borderPane = new BorderPane(outerGrid);
        borderPane.setPrefWidth(300);
        borderPane.setPrefHeight(300);
        borderPane.setBottom(hbox);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    private void solve() {
        sudoku.updateValues(nbrInputs);
        if(!solver.validGrid()){
            alert("Invalid","This Sudoku is invalid","Please fill in a valid Sudoku to solve.");
        }else if(solver.solve()){
            updateInputValues(sudoku.display());
            alert("Success", "Solution found", "This Sudoku is solvable with recursive backtracking.");
        }else{
            alert("Error", "No solution found", "This Sudoku has no solution.");
        }
    }

    // Clears the sudoku grid
    private void clear() {
        for(TextField t : nbrInputs) {
            t.clear();
        }
    }

    private void randomize() {
        updateInputValues(sudoku.randomizeGrid());
    }

    // Alert box
    private void alert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    // Updates the values on the GUI
    private void updateInputValues(int[][] matrix){
        int index = 0;
        for(int oRow = 0; oRow < 3; oRow++){
            for(int oCol = 0; oCol < 3; oCol++){
                for(int iRow = 0; iRow < 3; iRow++){
                    for(int iCol = 0; iCol < 3; iCol++){
                        if(index <= nbrInputs.size()){
                            nbrInputs.get(index).setText(Integer.toString(matrix[iRow + oRow*3][iCol + oCol*3]));
                        }
                        index++;
                    }
                }
            }
        }
    }

}
