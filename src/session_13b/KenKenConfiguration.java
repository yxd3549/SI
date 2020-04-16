package session_13b;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class KenKenConfiguration implements Configuration{

    private int[][] board;
    private int dim;
    private ArrayList<Rule> rules;
    private int currentRow;
    private int currentCol;

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

    @Override
    public boolean isGoal() {
//        int[][] goal = {
//                {1,2,5,3,4},
//                {2,4,3,5,1},
//                {4,5,1,2,3},
//                {5,3,4,1,2},
//                {3,1,2,4,5}
//        };
        if(currentRow != dim){
            return false;
        }
//        boolean found = true;
//        for(int i=0; i < dim; i++){
//            for(int j=0; j < dim; j++){
//                if(this.board[i][j] != goal[i][j]){
//                    found = false;
//                }
//            }
//        }
//        if(found){
//            System.out.println(this);
//        }
        for(Rule rule: rules){
            if(!rule.isSatisfied(this.board)){
                return false;
            }
        }
        return true;
    }

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
