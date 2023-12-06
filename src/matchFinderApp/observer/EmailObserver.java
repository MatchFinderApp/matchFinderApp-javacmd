package src.matchFinderApp.observer;

public class EmailObserver extends Observer {

    public EmailObserver(String recipent){
        super.setRecipient(recipent);
    }

    @Override
    public void update(String message) {
       
    }
    
}
