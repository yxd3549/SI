package session_10b;

import session_10a.GuessServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static session_10b.KnockKnockProtocol.GREETING;
import static session_10b.KnockKnockProtocol.KNOCKKNOCK;


public class KnockKnockServer {

    /* TODO #1 Add the variables you think you'll need. Look at GuessServer for reference */
    /** the client socket */
    private Socket clientSocket;
    /** the incoming connection from the client */
    private Scanner networkIn;
    /** the outgoing connection to the client */
    private PrintStream networkOut;


    private String[] clues = { "Turnip", "LittleOldLady", "Atch", "Who", "Who" };
    private String[] jokes = { "Turnip the heat, it's cold in here!",
            "I didn't know you could yodel!",
            "Bless you!",
            "Is there an owl in here?",
            "Is there an echo in here?" };


    /**
     * Initialize the server by listening on the server socket,
     * accepting the client connection, and setting up the I/O
     * connections.
     * @param port the server port number
     * @throws IOException if a network error occurs
     */
    public KnockKnockServer(int port) throws IOException {
        // TODO #2 Initialize the server and send greeting message to client
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
    }

    /**
     * Tell a few jokes. The server keeps telling jokes until
     * the client decides to quit or until it has no more jokes.
     */
    public void tellJokes(){
        // TODO #3 Complete this method
        for(int i = 0; i < clues.length; i++){
            String clue = this.clues[i];
            String joke = this.jokes[i];

            // Get a response from the client
            String answer = networkIn.nextLine();
            if (answer.equals(KnockKnockProtocol.YES)){ // If the client wants a joke:
                // Send the KnockKnock message to the client
                networkOut.println(KNOCKKNOCK);

                // Get an answer from the Client
                answer = networkIn.nextLine();
                while(!answer.equals(KnockKnockProtocol.WHOS_THERE)){ // While the answer is not exactly "Who's there?":
                    // Send the Wrong Question message to the client
                    networkOut.println(KnockKnockProtocol.WRONG_QUESTION);

                    // Get a new answer from the client
                    answer = networkIn.nextLine();
                }

                // Once you finally get the right response, send the client the clue to the joke
                networkOut.println(clue);

                // Get a response from the client
                answer = networkIn.nextLine();

                String[] pieces = answer.split(" ");
                while(pieces.length != 2 || !(pieces[0].equals(clue) && pieces[1].equals("who?"))){ // While the answer isn't right:
                    // Send the Wrong Question 2 message to the client
                    networkOut.println(KnockKnockProtocol.WRONG_QUESTION2);
                    // get a new answer from the client
                    answer=networkIn.nextLine();
                    pieces = answer.split(" ");
                }

                // Finally, send the client the punchline
                networkOut.println(joke);
                // Send the client the Again message
                networkOut.println(KnockKnockProtocol.AGAIN);
            } else { // if the client does not want a joke:
                //Send the client the Ending message
                networkOut.println(KnockKnockProtocol.ENDING);
                break;
            }
        }
    }

    /**
     * Called at the end to close down the network connections
     * @throws IOException if a network error occurs
     */
    public void shutDown() throws IOException{
        this.clientSocket.shutdownInput();
        this.clientSocket.shutdownOutput();
        this.clientSocket.close();
    }

    public static void main(String[] args) throws IOException{
        // TODO #4 Make an instance of a KnockKnockServer and call tellJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.


        // initial the network connection and establish the I/O streams
        KnockKnockServer server = new KnockKnockServer(1000);
        // play the game
        server.tellJokes();
        // shutdown the network connection
        server.shutDown();

    }


}
