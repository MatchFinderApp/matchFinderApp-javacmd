package src.matchFinderApp.subject;
import java.util.ArrayList;
import java.util.List;

import src.matchFinderApp.observer.Observer;


public class messageSubject implements subject{

    List<Observer> observers;
    public messageSubject(){
        this.observers = new ArrayList<Observer>();
    }
    @Override
    public void addSubscriber(Observer Observer) {
       this.observers.add(Observer);
    }

    @Override
    public void removeSubscriber(Observer Observer) {
        this.observers.remove(Observer);
    }

    @Override
    public void notifySubscribers(String m) {
       for (Observer observer : observers) {
        observer.update(m);
       }
    }
    
}
