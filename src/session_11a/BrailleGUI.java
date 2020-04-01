package session_11a;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * CSCI-142 Recitation Pair Exercise
 * 10-GUI
 * Braille GUI
 *
 * This is the Braille GUI which represents the View and Controller parts of
 * MVC.  It is a JavaFX application that creates the model, BrailleModel.
 * The graphical dots of the braille cell communicate with the model
 * to determine whether they are on or off.  When the translate button
 * is pressed, the letter for the current dot configuration is retrieved,
 * the GUI is notified via update, and then the GUI gets the letter
 * from the model to display.
 *
 * This is the student starter code.
 *
 * @author RIT CS
 */
public class BrailleGUI extends Application implements Observer<BrailleModel> {
    /** there are six dots in a braille cell */
    public final static int NUM_DOTS = 6;
    /** the braille cell has 3 rows */
    public final static int NUM_ROWS = 3;
    /** the braille cell has 2 columns */
    public final static int NUM_COLS = 2;

    /** the model */
    private BrailleModel model;

    /** the unfilled dot image */
    private Image unfilledDot = new Image(getClass().getResourceAsStream("unfilled.png"));
    /** the filled dot image */
    private Image filledDot = new Image(getClass().getResourceAsStream("filled.png"));
    /** a definition of white for the button background to match the dot image backgrounds */
    private static final Background WHITE =
            new Background( new BackgroundFill(Color.WHITE, null, null));

    /** we store the dot buttons for easy access when generating the bit string */
    private DotButton[] dots;

    /** the letter to be displayed when translating */
    private Text letter;
    /** the size of the font for the letter to display */
    private final static int LETTER_FONT_SIZE = 150;

    /**
     * In the init the GUI creates the model and adds itself as an observer
     * of it.
     */
    @Override
    public void init() {
        // create the model and add ourselves as an observer
        this.model = new BrailleModel();
        this.model.addObserver(this);
    }

    /**
     * A helper function that creates the grid of braille dots.
     *
     * @return the grid of dots
     */
    private GridPane createDots() {
        GridPane gridPane = new GridPane();

        this.dots = new DotButton[NUM_DOTS];

        // in braille, the first dot is referred to as position 1, not 0
        int position=1;
        // loop and create all the dots
        for (int col=0; col<NUM_COLS; ++col) {
            for (int row=0; row<NUM_ROWS; ++row) {
                DotButton button = new DotButton(position++);

                // here we register a callback for the dot button that asks the
                // model what the state of the dot is and to display the
                // corresponding image
                button.setOnAction((event) -> {
                    this.model.dotPressed(button.getPosition());
                    if (this.model.getDot(button.getPosition())) {
                        this.dots[button.getPosition()-1].setGraphic(new ImageView(this.filledDot));
                    } else {
                        this.dots[button.getPosition()-1].setGraphic(new ImageView(this.unfilledDot));
                    }
                });

                // add the button into the grid - notice it is column major
                gridPane.add(button, col, row);

                this.dots[button.getPosition()-1] = button;
            }
        }

        return gridPane;
    }

    /**
     * The start method is where the entire GUI is built.  After this method
     * returns control is passed to the JavaFX event handler.
     *
     * @param stage the window in which to set up our GUI components in
     * @throws Exception if there is a problem creating something
     */
    @Override
    public void start(Stage stage) throws Exception {
        // our main pane is a border pane
        BorderPane borderPane = new BorderPane();

        // the braille dots and the letter are in a vertical box
        VBox braillePane = new VBox();

        // create the brailled dot grid and add that first to the vbox
        GridPane dotsPane = createDots();
        braillePane.getChildren().add(dotsPane);
        borderPane.setCenter(braillePane);

        // create the letter text, initially empty, and add it to the vbox
        this.letter = new Text("");
        this.letter.setFill(Color.BLACK);
        this.letter.setStyle("-fx-font: " + LETTER_FONT_SIZE + " arial;");
        braillePane.getChildren().add(this.letter);
        braillePane.setAlignment(Pos.CENTER);

        // the translate button is in a horizontal box so it can be centered
        HBox buttonBox = new HBox();

        // create the translate button
        Button translateButton = new Button("Translate");

        // register a handler so that when the translate button is pressed,
        // the GUI generates a bit string of the dot states from
        // position 6 down to position 1
        translateButton.setOnAction((event) -> {
            String bitString = "";
            // ask the model for the state of each dot and create a
            // bitstring of 0's (off) and 1's (on).

            int i = 6;
            while(i != 0){
                if(model.getDot(i)){
                    bitString = bitString.concat("1");
                } else {
                    bitString = bitString.concat("0");
                }
                i--;
            }

            // inform the model of the bit string
            model.translate(bitString);

        });
        buttonBox.getChildren().add(translateButton);
        buttonBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(buttonBox);

        // add the main pane to the scene and set some properties before showing it
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Braille GUI");
        stage.sizeToScene();
        stage.setResizable(false);

        stage.show();
    }

    /**
     * A private inner class that extends the behavior of a JavaFX
     * button to have a position, image and background.
     */
    private class DotButton extends Button {
        // the position is 1-6
        private int position;

        /**
         * Create the button.
         *
         * @param position the dot position
         */
        public DotButton(int position) {
            this.position = position;
            this.setGraphic(new ImageView(unfilledDot));
            this.setBackground(WHITE);
        }

        /**
         * Get the position number of this dot
         *
         * @return position
         */
        public int getPosition() {
            return this.position;
        }
    }

    /**
     * This method is called by the model when the translate button is
     * pressed.  Here you should get the letter for the corresponding
     * bit string from the model, and then set the text field to display it.
     * @param brailleModel
     */
    @Override
    public void update(BrailleModel brailleModel) {
        // get the letter from the last bit string translated

        char letter = model.getLetter();

        // set the text for the letter

        this.letter.setText(Character.toString(letter));

        // if the letter is known, make it green, otherwise red

        if(letter == BrailleModel.UNKNOWN){
            this.letter.setFill(Color.RED);
        } else {
            this.letter.setFill(Color.GREEN);
        }
    }
}
