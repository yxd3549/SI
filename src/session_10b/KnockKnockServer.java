package session_10b;

import java.io.IOException;

public class KnockKnockServer {

    /* TODO #1 Add the variables you think you'll need. Look at GuessServer for reference */

    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
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

    }

    /**
     * Tell a few jokes. The server keeps telling jokes until
     * the client decides to quit or until it has no more jokes.
     */
    public void tellJokes(){
        // TODO #4 Iterate through the jokes until the client quits or your out of jokes
        //  Most of the communication with the client will occur here
    }

    /**
     * Called at the end to close down the network connections
     * @throws IOException if a network error occurs
     */
    public void shutDown() throws IOException{
        // TODO #5 Don't forget to clean up after yourself!
    }

    public static void main(String[] args) {
        // TODO #3 Make an instance of a KnockKnockServer and call tellJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.
    }


}
