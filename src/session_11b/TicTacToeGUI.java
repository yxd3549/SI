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



    private TicTacToeModel model;
    private GridPane gridPane;
    private Label text;

    @Override
    public void init() {
        // create the model and add ourselves as an observer
        this.model = new TicTacToeModel();
        this.model.addObserver(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gridPane = new GridPane();

        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                Button button = new Button();
                gridPane.add(button, row, col);
                button.setText("-");
                int finalCol = col;
                int finalRow = row;
                button.setOnAction( event -> {
                    model.placeMark(finalCol, finalRow);
                });
            }
        }

        text = new Label("X's Turn");

        Button resetButton = new Button("Restart");
        resetButton.setOnAction(event -> {
            this.model.restart();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(text, gridPane, resetButton);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(TicTacToeModel ticTacToeModel) {
        this.text.setText(this.model.getTurn() + "'s turn");

        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = getNodeFromGridPane(gridPane, col, row);
                button.setText(Character.toString(model.getCharacter(row, col)));
            }
        }
    }

    private Button getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button)node;
            }
        }
        return null;
    }

}
