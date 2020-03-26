package session_10b;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class KnockKnockClient {

    // TODO #1

    private Socket clientSocket;
    private Scanner networkIn;
    private PrintStream networkOut;

    /**
     * Initiate the connection with the server and receive the greeting
     * @param host the host name
     * @param port the port
     * @throws IOException if a network error occurs
     */
    public KnockKnockClient(String host, int port) throws IOException {
        // TODO #2 Initialize the client and receive the greeting message. Print it out

        this.clientSocket = new Socket(host, port);
        this.networkIn = new Scanner(this.clientSocket.getInputStream());
        this.networkOut = new PrintStream(this.clientSocket.getOutputStream(), true);

        System.out.println(this.networkIn.nextLine());

    }

    /**
     * Receive jokes from the server until the user quits
     * or the server ends communication
     */
    public void hearJokes(){
        // TODO #3 Complete this method
        Scanner userIn = new Scanner(System.in); // Use this to get input from the user
        while(true){
            String userAnswer = userIn.nextLine();
            // Get an answer to the server's greeting from the u

            if(userAnswer.contains("y")){ // If the user's answer contains the letter y:
                // Send the YES message to the server
                networkOut.println(KnockKnockProtocol.YES);
                // Get the knock knock question from the server. Print it out
                System.out.println(networkIn.nextLine());
                // Get a response from the user. The user should ask "Who's there?"
                String response = userIn.nextLine();
                // But they may not, the server will handle that.
                // Send the user's response to the server
                networkOut.println(response);

                // Get an answer from the server
                String serverAnswer = networkIn.nextLine();
                while(serverAnswer.equals(KnockKnockProtocol.WRONG_QUESTION)){ // While the server isn't satisfied:
                    // Print out the server's answer
                    System.out.println(serverAnswer);
                    // Get a new answer from the user
                    response = userIn.nextLine();
                    // Send the answer to the server
                    networkOut.println(response);
                    // Get a new response from the server
                    serverAnswer = networkIn.nextLine();
                }

                // Print the server's new answer. This will be the clue to the joke
                System.out.println(serverAnswer);


                // Get the user's response. They should ask "___ who?"
                // But they may not, the server will handle that.
                response = userIn.nextLine();
                // Send the user's response to the server
                networkOut.println(response);

                // Get an answer from the server
                serverAnswer = networkIn.nextLine();

                while(serverAnswer.equals(KnockKnockProtocol.WRONG_QUESTION2)){ // while the server isn't satisfied:
                    // Print out the server's answer
                    System.out.println(serverAnswer);
                    // Get a new answer from the user
                    response = userIn.nextLine();
                    // Send the answer to the server
                    networkOut.println(response);
                    // Get a new response from the server
                    serverAnswer = networkIn.nextLine();
                }

                // Print the server's new answer. This is the punchline. Finally!
                System.out.println(serverAnswer);
                // Get a new response form the server. This will be the server's prompt for another joke. Print it
                serverAnswer = networkIn.nextLine();
            } else {
                // Send the NO message to the server
                networkOut.println(KnockKnockProtocol.NO);
                // Get a response form the server
                String answer = networkIn.nextLine();
                System.out.println(answer);
                // exit the loop
                break;
            }
        }
    }

    /**
     * Called at the end to close down the network connection.
     */
    public void shutDown() throws IOException {
        // TODO #5 Don't forget to clean up after yourself!    <3
        this.clientSocket.shutdownInput();
        this.clientSocket.shutdownOutput();
        this.clientSocket.close();
    }


    public static void main(String[] args) throws IOException{
        // TODO #4 Make an instance of a KnockKnockClient and call hearJokes()
        //  Use port 1000
        //  Call shutDown() to close all resources.
        KnockKnockClient yan = new KnockKnockClient("localhost", 1000);
        yan.hearJokes();
        yan.shutDown();
    }
}
