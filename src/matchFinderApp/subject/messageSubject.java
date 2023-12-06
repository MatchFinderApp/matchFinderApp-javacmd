package src.matchFinderApp.subject;
import java.util.ArrayList;
import java.util.List;

import src.matchFinderApp.observer.Observer;


public class messageSubject implements subject{
//We will imlements the method at the next stage


// Add observer object to the list of observers to be notified\

    List<Observer> observers;
    public messageSubject(){
        this.observers = new ArrayList<Observer>();
    }
    @Override
    public void addSubscriber(Observer Observer) {
       this.observers.add(Observer);
    }
    // remove observer object to the list of observers
    @Override
    public void removeSubscriber(Observer Observer) {
        this.observers.remove(Observer);
    }
    // notify observer
    @Override
    public void notifySubscribers(String m) {
       for (Observer observer : observers) {
        observer.update(m);
       }
    }
    
}
