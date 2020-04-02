package session_11b;

import session_11a.Observer;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public static final char X = 'X';
    public static final char O = 'O';

    private char turn;
    private char[][] board;

    private List<Observer<TicTacToeModel>> observers;

    /**
     * Construct a model with an empty board
     * X starts playing
     */
    public TicTacToeModel(){
        this.observers = new ArrayList<>();
        this.turn = X;
        board = new char[3][3];
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.board[row][col] = '-';
            }
        }
    }

    /**
     * Returns the current player
     */
    public char getTurn(){
        return this.turn;
    }

    /**
     * Returns the character at the specified location
     */
    public char getCharacter(int row, int col){
        return this.board[row][col];
    }

    /**
     * Restarts the board to all blankc
     */
    public void restart(){
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.board[row][col] = '-';
            }
        }
        alertObservers();
    }

    /**
     * Toggles the current turn
     */
    private void changeTurn(){
        if (this.turn == X){
            this.turn = O;
        } else {
            this.turn = X;
        }
    }

    /**
     * Places the current mark at the specified location
     */
    public void placeMark(int row, int column){
        if (this.board[row][column] != X && this.board[row][column] != O){
            this.board[row][column] = turn;
            changeTurn();
        }
        alertObservers();
    }

    /**
     * The view calls this method to add themselves as an observer of the model.
     *
     * @param observer the observer
     */
    public void addObserver(Observer<TicTacToeModel> observer) {
        this.observers.add(observer);
    }

    /**
     * When the model changes, the observers are notified via their update()
     * method.
     */
    private void alertObservers() {
        for (Observer<TicTacToeModel> obs: this.observers ) {
            obs.update(this);
        }
    }

}
