package session_13b;

import java.util.ArrayList;
import java.util.Collections;

public class Rule {

    private Coordinate[] cells;
    private int goal;
    private Operation operation;

    public Rule(Coordinate[] cells, int goal, Operation operation) {
        this.cells = cells;
        this.goal = goal;
        this.operation = operation;
    }

    public boolean isSatisfied(int[][] board){
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < cells.length; i++){
            Coordinate cell = cells[i];
            numbers.add(board[cell.getRow()][cell.getCol()]);
        }
        Collections.sort(numbers, Collections.reverseOrder());

        if (operation == Operation.SUM){
            int total = 0;
            for(int number: numbers){
                total += number;
            }
            return total == goal;

        } else if (operation == Operation.SUBTRACTION){
            int total = 0;
            for(int number: numbers){
                if (total == 0){
                    total = number;
                } else {
                    total -= number;
                }
            }
            return total == goal;

        } else if (operation == Operation.MULTIPLICATION){
            int total = 1;
            for(int number: numbers){
                total *= number;
            }
            return total == goal;
        }
        return false;
    }
}
