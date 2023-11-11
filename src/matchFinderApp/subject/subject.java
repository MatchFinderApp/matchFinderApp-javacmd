package src.matchFinderApp.subject;

public interface subject {
    void addSubscriber(Observer Observer);
    void removeSubscriber(Observer Observer);
    void notifySubscribers(String m);    
}
