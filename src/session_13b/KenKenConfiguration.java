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
                } else {
                    operation = Operation.SUBTRACTION;
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
     * TODO: Group A
     */
    public KenKenConfiguration(KenKenConfiguration kenKenConfiguration){
        // TODO: Let's move our 'cursor' forward. If the kenKenConfiguration passed in
        //  is at position (1,2), we'll want to move it -this- cursor to (1,3).
        //  however, keep in mind that if you're at an edge, say
        //  (1, 3) in a 4x4 board, you'll want to increment the row by one
        //  and reset the column to 0
        if (kenKenConfiguration.currentCol == kenKenConfiguration.dim - 1) {
            this.currentCol = 0;
            this.currentRow = kenKenConfiguration.currentRow + 1;
        }
        else {
            this.currentCol = kenKenConfiguration.currentCol + 1;
            this.currentRow = kenKenConfiguration.currentRow;
        }




        // TODO: Copy all the primitive fields from the configuration passed in
        //  to -this- configuration.
        this.board = new int[kenKenConfiguration.dim][kenKenConfiguration.dim];
        for (int row = 0; row < kenKenConfiguration.dim; row++) {
            for (int col = 0; col < kenKenConfiguration.dim; col++) {
                this.board[row][col] = kenKenConfiguration.board[row][col];
            }
        }



        // TODO: Now think about the fields that aren't primitives
        //  remember that if those fields can change, you need to deep-copy them
        //  but if they never change, you can copy them just like a primitive field
        this.rules = kenKenConfiguration.rules;
        this.dim = kenKenConfiguration.dim;

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
     * TODO: Group A
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        ArrayList<Configuration> successors = new ArrayList<>();
        // TODO: If we're at the end (i.e, if the current row is equal to the dimension of the board)
        //  return an empty list of successors
        if (this.currentRow == this.dim-1) {
            return successors;
        }


        // TODO: Otherwise, create the next generation of successors
        //  Think about how many successors we will generate; you'll need to iterate that many times
        //  You'll have to create a copy of the current configuration, how do we do that?
        //  Every copy will then only be slightly different from each other, how?
        for (int i = 1; i <= this.dim; i++) {
            KenKenConfiguration newConfig = new KenKenConfiguration(this);
            newConfig.board[this.currentRow][this.currentCol] = i;
            successors.add(newConfig);
        }





        return successors;
    }

    /**
     * Returns whether this configuration is valid or not
     * Used for pruning
     * TODO: Group A or B, whoever gets it first!
     * @return
     */
    @Override
    public boolean isValid() {
        // TODO: Check that no number is repeated in any row
        //  What kind of data structure can we use to store elements and
        //  efficiently find out if we have already seen them?
        //  Note: Remember the default number in our board is 0


        // TODO: Check that no number is repeated in any column

        return true;
    }

    /**
     * Return whether this configuration is a goal
     * TODO: Group B
     */
    @Override
    public boolean isGoal() {
        // TODO: Check if we're at the end.
        //  If we're not, we know for sure this configuration is not a goal
        if (currentRow != dim) {
            return false;
        }

        // TODO: Otherwise, check that all the rules are satisfied with this board
        //  This should be easy with your helper function!
        for (Rule rule: rules) {

            if (!(rule.isSatisfied(this.board))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Starts the program and runs the backtracking algorithm
     * @param args
     */
    public static void main(String[] args) {
        KenKenConfiguration configuration = new KenKenConfiguration("6x6.txt");
        Backtracker backtracker = new Backtracker(false);
        Optional<Configuration> solution = backtracker.solve(configuration);
        if(solution.isPresent()){
            KenKenConfiguration realSolution = (KenKenConfiguration) solution.get();
            System.out.println(realSolution);
        }
    }
}
