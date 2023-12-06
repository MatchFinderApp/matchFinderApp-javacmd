package src.matchFinderApp.subject;

import src.matchFinderApp.observer.Observer;

public interface subject {
    void addSubscriber(Observer Observer);
    void removeSubscriber(Observer Observer);
    void notifySubscribers(String m);    
}
