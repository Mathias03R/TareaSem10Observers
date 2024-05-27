package elegirpapeltapiz;

public interface Subject {

	//methods to register and unregister observers
	public void register(DiseñadorObserver obj);
	public void unregister(DiseñadorObserver obj);
	
	//method to notify observers of change
	public void notifyObservers();
	
}
