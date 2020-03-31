package session_11a;

import java.util.*;

/**
 * CSCI-142 Recitation Pair Exercise
 * 10-GUI
 * Braille GUI
 *
 * This is the model in the MVC design.  It keeps track of the state of
 * each dot, and it knows the mapping between a bit string and its
 * corresponding letter.
 *
 * This is the student starter code.
 *
 * @author RIT CS
 */
public class BrailleModel {
    /** if the bitstring doesn't map to anything */
    public static final char UNKNOWN = '?';

    /** map of bit strings to characters */
    private static final Map<String, Character> letters;

    // initialize the mappings here
    static {
        Map<String, Character> map = new HashMap<>();
        map.put("000001", 'A');
        map.put("000011", 'B');
        map.put("001001", 'C');
        map.put("011001", 'D');
        map.put("010001", 'E');
        map.put("001011", 'F');
        map.put("011011", 'G');
        map.put("010011", 'H');
        map.put("001010", 'I');
        map.put("011010", 'J');
        map.put("000101", 'K');
        map.put("000111", 'L');
        map.put("001101", 'M');
        map.put("011101", 'N');
        map.put("010101", 'O');
        map.put("001111", 'P');
        map.put("011111", 'Q');
        map.put("010111", 'R');
        map.put("001110", 'S');
        map.put("011110", 'T');
        map.put("100101", 'U');
        map.put("100111", 'V');
        map.put("111010", 'W');
        map.put("101101", 'X');
        map.put("111101", 'Y');
        map.put("110101", 'Z');

        // do not let the map be modified after this
        letters = Collections.unmodifiableMap(map);
    }
    
    /** the observers of this model */
    private List<Observer<BrailleModel>> observers;

    /** the dots (0 = off, 1 = on) */
    private boolean[] dots;

    /** the letter the current dot configuration translates to */
    private char letter;

    /**
     * Create the model.
     */
    public BrailleModel() {
        // create our observer list
        this.observers = new LinkedList<>();
        // initialize the dot boolean values to all false (the default behavior)
        this.dots = new boolean[BrailleGUI.NUM_DOTS];
        // our letter is not known yet
        this.letter = UNKNOWN;
    }

    /**
     * The view calls this method to add themselves as an observer of the model.
     *
     * @param observer the observer
     */
    public void addObserver(Observer<BrailleModel> observer) {
        this.observers.add(observer);
    }

    /**
     * When the model changes, the observers are notified via their update()
     * method.
     */
    private void alertObservers() {
        for (Observer<BrailleModel> obs: this.observers ) {
            obs.update(this);
        }
    }

    /**
     * Called by the controller whenever a dot is pressed on.
     *
     * @param position
     */
    public void dotPressed(int position) {
        this.dots[position-1] = !this.dots[position-1];
    }

    /**
     * Get the state of the dot, either off (false) or on (true).
     *
     * @param position the position of the dot (1 through 6)
     * @return whether the dot is currently off or on
     */
    public boolean getDot(int position) {
        return this.dots[position-1];
    }

    /**
     * Called by the GUI when the translate button is pressed.  Here we look
     * up the bit string in the map and if there is a value we return the
     * letter, otherwise we return a question mark.
     *
     * @param bitString the bit string of the dots by position in the braille cell
     */
    public void translate(String bitString) {

        // get the corresponding letter from the map for this bit string and
        // set the the letter data member to it

        // TODO

        // call alertObservers() so the GUI sees the update

        // TODO
    }

    /**
     * Get the letter that is associated with the last bitstring translated.  This
     * is called by the GUI's update() when it wants to display the letter.
     *
     * @return
     */
    public char getLetter() {
        return this.letter;
    }
}
