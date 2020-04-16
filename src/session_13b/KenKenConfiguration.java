package session_13b;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * This class implements the necessary methods to solve a KenKen puzzle
 * using the Backtracking interface from the CS department.
 */
public class KenKenConfiguration implements Configuration{

    /* A 2d array to represent the board */
    private int[][] board;
    /* The dimensions of the board */
    private int dim;
    /* The set of rules or regions for this board */
    private ArrayList<Rule> rules;
    /* The row of the cell we must fill next */
    private int currentRow;
    /* The column of the cell we must fill next */
    private int currentCol;

    /**
     * Main constructor for a KenKenConfiguration
     * Reads the board and rules from a file and initializes the state
     */
    public KenKenConfiguration(String filename){
        this.currentRow = 0;
        this.currentCol = 0;
        try(Scanner file = new Scanner(new FileReader(filename))){
            dim = file.nextInt();
            board = new int[dim][dim];
            rules = new ArrayList<>();
            file.nextLine();
            while(file.hasNextLine()){
                String[] line = file.nextLine().split(" ");

                String operationString = line[0];
                Operation operation;
                if (operationString.equals("+")){
                    operation = Operation.SUM;
                } else if (operationString.equals("-")){
                    operation = Operation.SUBTRACTION;
                } else {
                    operation = Operation.MULTIPLICATION;
                }

                int goal = Integer.parseInt(line[1]);

                ArrayList<Coordinate> cells = new ArrayList<>();
                for (int i = 2; i < line.length; i++){
                    String[] coordinates = line[i].split(",");
                    Coordinate coordinate = new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                    cells.add(coordinate);
                }
                Coordinate[] coordinates = new Coordinate[cells.size()];
                cells.toArray(coordinates);
                Rule rule = new Rule(coordinates, goal, operation);
                rules.add(rule);
            }

        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }
    }

    /**
     * A copy constructor to copy all the state by value and not by reference
     */
    public KenKenConfiguration(KenKenConfiguration kenKenConfiguration){
        this.dim = kenKenConfiguration.dim;
        this.board = new int[this.dim][this.dim];
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                this.board[i][j] = kenKenConfiguration.board[i][j];
            }
        }
        this.rules = kenKenConfiguration.rules;
        if (kenKenConfiguration.currentCol == dim -1){
            this.currentCol = 0;
            this.currentRow = kenKenConfiguration.currentRow + 1;
        } else {
            this.currentCol = kenKenConfiguration.currentCol + 1;
            this.currentRow = kenKenConfiguration.currentRow;
        }
    }

    /**
     * For debugging purposes, the toString returns a string representing
     * the current state of the board
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                result += this.board[i][j];
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Returns a list of all the successors of this configuration
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        ArrayList<Configuration> successors = new ArrayList<>();
        if(this.currentRow == dim){
            return successors;
        }
        for(int i=1; i <= dim; i++){
            KenKenConfiguration successor = new KenKenConfiguration(this);
            successor.board[this.currentRow][this.currentCol] = i;
            successors.add(successor);
        }
        return successors;
    }

    /**
     * Returns whether this configuration is valid or not
     * Used for pruning
     * @return
     */
    @Override
    public boolean isValid() {
        // Check Rows
        for(int i=0; i < dim; i++){
            TreeSet<Integer> found = new TreeSet<>();
            for(int j=0; j < dim; j++){
                int number = this.board[i][j];
                if (number != 0 && found.contains(number)){
                    return false;
                } else{
                    found.add(number);
                }
            }
        }

        // Check Columns
        for(int i=0; i < dim; i++){
            TreeSet<Integer> found = new TreeSet<>();
            for(int j=0; j < dim; j++){
                int number = this.board[j][i];
                if (number != 0 && found.contains(number)){
                    return false;
                } else{
                    found.add(number);
                }
            }
        }
        return true;
    }

    /**
     * Return whether this configuration is a goal
     */
    @Override
    public boolean isGoal() {
        if(currentRow != dim){
            return false;
        } else {
            for (Rule rule : rules) {
                if (!rule.isSatisfied(this.board)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Starts the program and runs the backtracking algorithm
     * @param args
     */
    public static void main(String[] args) {
        KenKenConfiguration configuration = new KenKenConfiguration("5x5.txt");
        Backtracker backtracker = new Backtracker(false);
        Optional<Configuration> solution = backtracker.solve(configuration);
        if(solution.isPresent()){
            KenKenConfiguration realSolution = (KenKenConfiguration) solution.get();
            System.out.println(realSolution);
        }
    }
}
