package observer;

public interface Subject {
	
	void addObserver(Observer observer);
	void addObserverUndoRedo(Observer observer);
	void removeObserver(Observer observer);
	void removeObserverUndoRedo(Observer observer);
	void notifyAllObservers();
	void notifyAllObserverUndoRedo();

}
