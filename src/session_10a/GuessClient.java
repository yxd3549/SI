package session_10a;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * CSCI-142 Computer Science 2 Recitation Pair Exercise
 * 09-Networking
 * Guessing Game
 *
 * The client side of the guessing game.  It connects to the server
 * and sends/receives messages in order to guess the secret number.
 *
 * This is the student code.
 *
 * $ java GuessClient localhost 50000
 * Greetings from GuessServer!
 * Enter guess from 1 to 100 (-1 to exit): 50
 * Your guess was too low, try again!
 * Enter guess from 1 to 100 (-1 to exit): 75
 * Your guess was too low, try again!
 * Enter guess from 1 to 100 (-1 to exit): 97
 * Your guess was too high, try again!
 * Enter guess from 1 to 100 (-1 to exit): 95
 * You guessed the secret number was 95!
 * The secret number was 95 and you made 4 guesses!
 *
 * @author RIT CS
 */
public class GuessClient implements GuessProtocol {
    /** the client socket which expects a host and port */
    private Socket clientSocket;
    /** the incoming connection from the server */
    private Scanner networkIn;
    /** the outgoing connection to the server */
    private PrintStream networkOut;

    /**
     * Initiate the connection with the server and receive the greeting
     * message
     *
     * @param host the host name
     * @param port the port
     * @throws IOException if a network error occurs
     */
    public GuessClient(String host, int port) throws IOException {
        // establish connection with server
        this.clientSocket = new Socket(host, port);

        // get input and output connections for the server
        this.networkIn = new Scanner(this.clientSocket.getInputStream());
        this.networkOut = new PrintStream(this.clientSocket.getOutputStream(), true);

        // read the greeting from the server and display it
        System.out.println(this.networkIn.nextLine());
    }

    /**
     * The main game is played here in a loop until either the correct
     * number is guessed, or the user quits.
     */
    public void playGame() {
        // we read input from the user using a scanner
        Scanner userIn = new Scanner(System.in);

        while (true) {
            // read guess from user
            System.out.print("Enter guess from 1 to 100 (-1 to exit): ");
            int guess = userIn.nextInt();

            // user entered a guess - they don't want to abort
            if (guess != -1) {
                // send guess to server
                this.networkOut.printf(GUESS, guess);

                // check response

                // TODO: Step 2
                String response = networkIn.nextLine();
                if(response.equals(LOW)){
                    System.out.println(LOW);
                } else if(response.equals(HIGH)){
                    System.out.println(HIGH);
                } else{
                    System.out.println(CORRECT);
                    this.networkOut.println(GuessServer.ABORT);
                    break;
                }

            } else {
                this.networkOut.println(GuessServer.ABORT);
                // if the user wants to quit early we break out of the loop
                break;
            }
        }
        System.out.println(this.networkIn.nextLine());
    }

    /**
     * Called at the end of the game to close down the network connection.
     *
     * @throws IOException a network error occurred
     */
    public void shutDown() throws IOException {
        this.clientSocket.shutdownInput();
        this.clientSocket.shutdownOutput();
        this.clientSocket.close();
    }

    /**
     * The main program expects the command line to have the host and port.
     *
     * @param args command line
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java GuessClient host port");
        } else {
            try {
                // initiate the network connection and set up the I/O streams
                GuessClient client = new GuessClient(args[0], Integer.parseInt(args[1]));
                // play the game
                client.playGame();
                // shut down the network connection
                client.shutDown();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }
}
