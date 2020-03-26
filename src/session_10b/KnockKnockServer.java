package session_10b;

import java.io.IOException;


public class KnockKnockServer {

    /* TODO #1 Add the variables you think you'll need. Look at GuessServer for reference */

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
            String answer = "";
            if (answer.equals(KnockKnockProtocol.YES)){ // If the client wants a joke:
                // Send the KnockKnock message to the client

                // Get an answer from the Client
                while(!answer.equals(KnockKnockProtocol.WHOS_THERE)){ // While the answer is not exactly "Who's there?":
                    // Send the Wrong Question message to the client

                    // Get a new answer from the client
                }

                // Once you finally get the right response, send the client the clue to the joke

                // Get a response from the client

                String[] pieces = answer.split(" ");
                while(pieces.length != 2 || !(pieces[0].equals(clue) && pieces[1].equals("who?"))){ // While the answer isn't right:
                    // Send the Wrong Question 2 message to the client

                    // get a new answer from the client

                    pieces = answer.split(" ");
                }

                // Finally, send the client the punchline

                // Send the client the Again message

            } else { // if the client does not want a joke:
                //Send the client the Ending message

                break;
            }
        }
    }

    /**
     * Called at the end to close down the network connections
     * @throws IOException if a network error occurs
     */
    public void shutDown() throws IOException{
        // TODO #5 Don't forget to clean up after yourself!

    }

    public static void main(String[] args) throws IOException{
        // TODO #4 Make an instance of a KnockKnockServer and call tellJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.

    }


}
