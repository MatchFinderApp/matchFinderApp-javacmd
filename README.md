

Mohammed Alqahtani	2037206

Abdullah Saad Alharthi	2035071








### Introduction



Through the Java command line, the MatchFinderApp project uses Software Design Patterns to give users the ability to view daily football game information. The App uses SMS notifications to notify users of changes and web scraping techniques to retrieve appropriate information from a static football webpage.




Creational design patterns are a category of design patterns that deal with object creation mechanisms. They provide ways to instantiate objects in a manner suitable to the situation. The primary goal of creational design patterns is to provide more flexibility in the creation of objects while promoting code reuse and system scalability.





Abstract Factory Pattern is one of the creational design patterns and provides an interface for creating families of related or dependent objects without specifying their concrete classes.
So we implemented the Abstract Factory pattern on our project to make the creation of objects more flexible while promoting code reuse and system scalability.



## Abstract Subject Interface  
The Subject interface defines the contract for subjects. and it declared the following methods:

addSubscriber (Observer observer):

This method permits observers to be added to the subscriber list.
This method will be used by concrete subjects to register observers so they can get notifications.

removeSubscriber(Observer observer):
This method allows observers to be deleted from the subscriber list.
By using this method, concrete subjects will be able to unregister observers and stop them from getting notifications in the future.

notifySubscribers(String message):
This method is in charge of informing all subscribers of any changes to the subject's state.
Whenever a state change occurs, concrete subjects will call this method, which will cause each subscribed observer to call their update method.

## The MessageSubject class implements the Subject interface.

It manages an observer list and gives the three methods provided in the Subject interface specific implementations.




