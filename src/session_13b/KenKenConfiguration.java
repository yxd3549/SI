package session_13b;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class KenKenConfiguration {

    private int[][] board;
    private int dim;
    private ArrayList<Rule> rules;

    public KenKenConfiguration(String filename){
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

    public KenKenConfiguration(KenKenConfiguration kenKenConfiguration){
        this.dim = kenKenConfiguration.dim;
        this.board = new int[this.dim][this.dim];
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                this.board[i][j] = kenKenConfiguration.board[i][j];
            }
        }
    }

    public static void main(String[] args) {
        KenKenConfiguration configuration = new KenKenConfiguration("3x3.txt");
        System.out.println(configuration);
    }
}
