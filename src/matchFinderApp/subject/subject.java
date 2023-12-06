package src.matchFinderApp.subject;

import src.matchFinderApp.observer.Observer;

//Here is the Abstract Factory Design Pattern

public abstract interface subject {

    void addSubscriber(Observer Observer);
    void removeSubscriber(Observer Observer);
    void notifySubscribers(String m);    
}
