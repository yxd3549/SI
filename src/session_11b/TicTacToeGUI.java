package session_11b;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import session_11a.Observer;


public class TicTacToeGUI extends Application implements Observer<TicTacToeModel> {


    // TODO: Add a variable to store the model
    private TicTacToeModel model;

    // TODO: Add a variable for the gridPane to store the cells/buttons
    private GridPane gridPane;

    // TODO: Add a variable for the Label that will store text at the top
    private Label text;

    @Override
    public void init() {
        // TODO: create the model and add ourselves as an observer
    }

    @Override
    public void start(Stage stage) throws Exception {

        // TODO: Initialize the Label.
        //  It should display "_'s turn", where _ is the current mark in the model


        // TODO: Initialize the gridPane

        // Create the buttons
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                // TODO: Create a Button
                //  - Set the button's text to "-".


                // For reasons that won't make sense until you learn more about programming languages and lambdas,
                // you need these "final" variables to use inside the button's setOnAction
                int finalCol = col;
                int finalRow = row;

                // TODO: Make it so that, when we click a button, we place a mark in the model at the correct location.
                //  Remember the board is stored in a Row then Column fashion


                // TODO: Add the button to the GridPane
                //  remeber the grid stores values in a x then y fashion
            }
        }

        // TODO: Create a button to reset the board
        //  Make it so that when you click on it, the model restarts the board


        // TODO: Create a container to put all your nodes in
        //  Add all the nodes to this container


        // TODO: Create a new Scene
        //  Set the stage's scene to the new scene
        //  Show the stage
        Scene scene = new Scene(new VBox());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(TicTacToeModel ticTacToeModel) {

        // TODO: Update the label to reflect the new turn
        this.text.setText(this.model.getTurn() + "'s turn");

        // TODO: Update the gridPane to reflect the changes to the board
    }

    /**
     * You'll need this function in your update method.
     * You're welcome :)
     */
    private Button getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button)node;
            }
        }
        return null;
    }

}
