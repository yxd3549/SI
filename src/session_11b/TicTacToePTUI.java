package session_11b;

// TODO: First of all, let's make the PTUI an observer
public class TicTacToePTUI implements Observer<TicTacToeModel>{

    // TODO: The PTUI needs to observe the model. So what do you think we will need as our state?
    TicTacToeModel model;

    public TicTacToePTUI(TicTacToeModel model){
        // TODO: Based on the private state, what do we do here?
        this.model = model;

        // TODO: How do we let the model know we're observing it?
        this.model.addObserver(this);
        update(this.model);
    }

    @Override
    public void update(TicTacToeModel ticTacToeModel) {
        for(int i=0; i<3; i++){
            for(int j =0; j<3 ; j++){
                System.out.print(this.model.getCharacter(i, j));
            }
            System.out.println();
        }
    }
}
