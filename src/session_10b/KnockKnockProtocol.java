package session_10b;


/**
 * The protocol describes the messages that are passed between
 * the server and the client in order to share jokes
 */
public interface KnockKnockProtocol {

    // SERVER -> CLIENT

    /** when the client first connects it receives this greeting */
    String GREETING = "Hey! Wanna hear a joke?!";
    /** if the client answers "yes", the server sends this message */
    String KNOCKKNOCK = "Knock Knock";
    /** if the client answers "no", the server sends this message */
    String ENDING = "Oh, okay. Bye then!";
    /** if the client doesn't follow up 'Knock knock' with WHOS_THERE, the serve sends this */
    String WRONG_QUESTION = "You're supposed to say 'Who's there?'!";
    /** if the client doesn't follow up the joke hint with BLANK_WHO, send this */
    String WRONG_QUESTION2 = "Oh come on! Cooperate!";
    /** the server sends this message after telling a joke */
    String AGAIN = "Wanna hear another joke?!";

    // CLIENT -> SERVER

    /** if the user enters yes, the client sends this message to the server */
    String YES = "I'd love to!";
    /** if the user enters no, the client sends this message to the server */
    String NO = "Nah. I'm good";
    /** this is the message the server expects to hear after sending KNOCKKNOCK*/
    String WHOS_THERE = "Who's there?";
    /** this is the message the server expects to hear after sending the joke's clue*/
    String BLANKWHO = "%s who?";
}
