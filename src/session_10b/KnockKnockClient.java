package session_10b;

import java.io.IOException;
import java.util.Scanner;

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
        // TODO #3 Complete this method
        Scanner userIn = new Scanner(System.in); // Use this to get input from the user
        while(true){
            String userAnswer = "";
            // Get an answer to the server's greeting from the user

            if(userAnswer.contains("y")){ // If the user's answer contains the letter y:
                // Send the YES message to the server

                // Get the knock knock question from the server. Print it out

                // Get a response from the user. The user should ask "Who's there?"
                // But they may not, the server will handle that.
                // Send the user's response to the server

                // Get an answer from the server
                String serverAnswer = "";
                while(serverAnswer.equals(KnockKnockProtocol.WRONG_QUESTION)){ // While the server isn't satisfied:
                    // Print out the server's answer

                    // Get a new answer from the user

                    // Send the answer to the server

                    // Get a new response from the server
                }

                // Print the server's new answer. This will be the clue to the joke


                // Get the user's response. They user ask "___ who?"
                // But they may not, the server will handle that.
                // Send the user's response to the server

                // Get an answer from the server

                while(serverAnswer.equals(KnockKnockProtocol.WRONG_QUESTION2)){ // while the server isn't satisfied:
                    // Print out the server's answer

                    // Get a new answer from the user

                    // Send the answer to the server

                    // Get a new response from the server
                }

                // Print the server's new answer. This is the punchline. Finally!

                // Get a new response form the server. This will be the server's prompt for another joke. Print it
            } else {
                // Send the NO message to the server

                // Get a response form the server

                // exit the loop
            }
        }
    }

    /**
     * Called at the end to close down the network connection.
     */
    public void shutDown() throws IOException {
        // TODO #5 Don't forget to clean up after yourself!
    }


    public static void main(String[] args) throws IOException{
        // TODO #4 Make an instance of a KnockKnockClient and call hearJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.
    }
}
