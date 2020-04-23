package session_11b;

import java.net.InetAddress;
import java.util.Scanner;

public class TicTacToePTUIController {

    // TODO: much like the PTUI, the Controller needs to interact with the model.
    //  So what should be our private state?
    private TicTacToeModel model;

    public TicTacToePTUIController(TicTacToeModel model){
        // TODO: initialize said private state
        this.model = model;
    }

    public void run(){
        // Here is where we interact with the user
        int placed = 0;
        Scanner userIn = new Scanner(System.in);
        while (placed < 9){
            // We ask the user for a row and a column
            System.out.print("Enter Coordinate: ");
            String[] line = userIn.nextLine().split(" ");
            int row = Integer.parseInt(line[0]);
            int col = Integer.parseInt(line[1]);

            // Now let's tell the model to do its thing
            if(this.model.getCharacter(row, col) != '-'){
                System.out.println(" Dumb dumb, no");
            } else {
                this.model.placeMark(row, col);
                placed++;
            }
        }
    }

    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        TicTacToePTUI ptui = new TicTacToePTUI(model);
        TicTacToePTUIController controller = new TicTacToePTUIController(model);
        controller.run();
    }

}
