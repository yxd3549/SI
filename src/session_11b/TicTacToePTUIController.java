package session_11b;

import java.net.InetAddress;
import java.util.Scanner;

public class TicTacToePTUIController {

    // TODO: much like the PTUI, the Controller needs to interact with the model.
    //  So what should be our private state?


    public TicTacToePTUIController(){
        // TODO: initialize said private state
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
        }
    }

    public static void main(String[] args) {
    }

}
