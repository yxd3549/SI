package session_10b;

import java.io.IOException;

public class KnockKnockClient {

    /* TODO #1 Add the variables you think you'll need. Look at GuessServer for reference */

    /**
     * Initiate the connection with the server and receive the greeting
     * @param host the host name
     * @param port the port
     * @throws IOException if a network error occurs
     */
    public KnockKnockClient(String host, int port) throws IOException {
        // TODO #2 Initialize the client and receive the greeting message. Print it out
    }

    /**
     * Receive jokes from the server until the user quits
     * or the server ends communication
     */
    public void hearJokes(){
        // TODO #3 while you keep getting messages, print them out and prompt the user when necessary
        //  Most of the interaction with the server occurs here
    }


    public static void main(String[] args) {
        // TODO #3 Make an instance of a KnockKnockClient and call hearJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.
    }
}
