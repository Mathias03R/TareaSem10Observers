package elegirpapeltapiz;

import java.util.ArrayList;
import java.util.List;

public class DiseñadorSubject implements Subject {

	private List<DiseñadorObserver> diseñadores;
        private boolean realTimeMsg = true;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();
	
	public DiseñadorSubject(){
		this.diseñadores=new ArrayList<>();
	}
	@Override
	public void register(DiseñadorObserver obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
		if(!diseñadores.contains(obj)) diseñadores.add(obj);
		}
	}

	@Override
	public void unregister(DiseñadorObserver obj) {
		synchronized (MUTEX) {
		diseñadores.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<DiseñadorObserver> observersLocal = null;
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed)
				return;
			observersLocal = new ArrayList<>(this.diseñadores);
			this.changed=false;
		}
		for (DiseñadorObserver obj : observersLocal) {
                    if (realTimeMsg){
                        if (obj.isRealTimeNotis())
                            obj.update(message);
                    } else{
                        obj.update(message);
                    }
                    
		}

	}
	
	public void preseleccionarPapel(String color){
		this.message="Papel preseleccionado: "+color;
                this.realTimeMsg = true;
                this.changed=true;
		notifyObservers();
	}
        
        public void seleccionarPapel(String color){
		this.message="Papel seleccionado: "+color;
                this.realTimeMsg = false;
		this.changed=true;
		notifyObservers();
	}

}
