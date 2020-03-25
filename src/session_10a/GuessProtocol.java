package session_10a;

/**
 * CSCI-142 Computer Science 2 Recitation Pair Exercise
 * 09-Networking
 * Guessing Game
 *
 * The protocol describes the messages that are passed between the
 * server and client in order to facilitate playing the game.
 *
 * This is the student code.
 *
 * @author RIT CS
 */
public interface GuessProtocol {
    // SERVER -> CLIENT
    /** when client first connects it receives this greeting */
    String GREETING = "Greetings from GuessServer!";
    /** when the client's guess is low, this is sent */
    String LOW = "LOW";
    /** when the client's guess is high, this is sent */
    String HIGH = "HIGH";
    /** when the client's guess is correct, this is sent */
    String CORRECT = "CORRECT";
    /** At the end the client receives the secret number and number of attempts */
    String CLOSING = "The secret number was %d and you made %d guesses!\n";

    // CLIENT -> SERVER
    /** when the client wants to make a guess it uses this message */
    String GUESS = "GUESS %d\n";
    /** when the client wants to quit early they send this message */
    String ABORT = "ABORT";
}
