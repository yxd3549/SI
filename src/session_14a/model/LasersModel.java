package session_14a.model;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * The model of the lasers safe.  You are free to change this class however
 * you wish, but it should still follow the MVC architecture.
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class LasersModel {
    /** the observers who are registered with this model */
    private List<Observer<LasersModel>> observers;

    public LasersModel(String filename) throws FileNotFoundException {
        this.observers = new LinkedList<>();
        // TODO
    }

    /**
     * Add a new observer.
     *
     * @param observer the new observer
     */
    public void addObserver(Observer<LasersModel> observer) {
        this.observers.add(observer);
    }

    /**
     * Notify observers the model has changed.
     *
     * @param data optional data the model can send to the view
     */
    private void notifyObservers(){
        for (Observer<LasersModel> observer: observers) {
            observer.update(this);
        }
    }
}
