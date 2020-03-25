package session_10a;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CSCI-142 Computer Science 2 Recitation Pair Exercise
 * 09-Networking
 * Guessing Game
 *
 * The server side of the guessing game.  It creates the server socket
 * that the client connects to, and sends/receives messages in order to
 * allow the client to guess the secret number.
 *
 * This is the solution code.
 *
 * $ java GuessServer 50000
 * Server waiting for client on port 50000
 * Client connected: Socket[addr=/127.0.0.1,port=61949,localport=50000]
 * Your guess of 50 is too low!
 * Your guess of 75 is too low!
 * Your guess of 97 is too high!
 * Your guess of 95 is correct!
 */
public class GuessServer implements GuessProtocol {
    /** minimum number to guess */
    public final static int MIN = 1;
    /** maximum number to guess */
    public final static int MAX = 100;

    /** the client socket */
    private Socket clientSocket;
    /** the incoming connection from the client */
    private Scanner networkIn;
    /** the outgoing connection to the client */
    private PrintStream networkOut;
    /** the secret number */
    private int secretNumber;
    /** the number of guesses the client has made */
    private int numGuesses;

    /**
     * Initialize the server by listening on the server socket,
     * accepting the client connection, and setting up the I/O
     * connections.
     *
     * @param port the server port number
     * @throws IOException if a network error occurs
     */
    public GuessServer(int port) throws IOException {
        // establish connection with the client
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for client on port " + port);
        this.clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + this.clientSocket);

        // get the input and output connections for the client
        this.networkOut = new PrintStream(this.clientSocket.getOutputStream(), true);
        this.networkIn = new Scanner(this.clientSocket.getInputStream());

        // send them a greeting
        this.networkOut.println(GREETING);

        // generate the random number between 1 and 100 inclusive
        this.secretNumber = ThreadLocalRandom.current().nextInt(MIN, MAX+1);
        this.numGuesses = 0;
    }

    /**
     * Play the game.  The server takes requests from the client and
     * sends back the results of the guess until the secret number
     * is guessed or the client wants to quit early.
     */
    public void playGame() {
        while (this.networkIn.hasNextLine()) {
            // we split for the "GUESS #" message
            String[] response = this.networkIn.nextLine().split(" ");
            if (response[0].equals(ABORT)) {
                System.out.println("Client is aborting!");
                // break out of the loop if the client is quitting
                break;
            } else { // assume it is "GUESS #"
                ++this.numGuesses;
                int guess = Integer.parseInt(response[1]);
                System.out.print("Your guess of " + guess);

                // TODO: Step 1
            }
        }
        // finally send to the client the secret number and number of guesses
        this.networkOut.printf(CLOSING, this.secretNumber, this.numGuesses);
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
     * The main program expects the command line to have the port.
     *
     * @param args command line
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GuessServer port");
        } else {
            try {
                // initial the network connection and establish the I/O streams
                GuessServer server = new GuessServer(Integer.parseInt(args[0]));
                // play the game
                server.playGame();
                // shutdown the network connection
                server.shutDown();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }
}
