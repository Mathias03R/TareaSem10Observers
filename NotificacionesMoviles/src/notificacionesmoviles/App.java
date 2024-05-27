package notificacionesmoviles;

import java.util.ArrayList;
import java.util.List;

public class App implements Subject {

	private List<Observer> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();
	
	public App(){
		this.observers=new ArrayList<>();
	}
	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
		if(!observers.contains(obj)) observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		synchronized (MUTEX) {
		observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer> observersLocal = null;
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed=false;
		}
		for (Observer obj : observersLocal) {
			obj.update(message);
		}

	}
	
	public void newMensaje(String mensaje){
		this.message="Nuevo mensaje: "+mensaje;
                this.changed=true;
		notifyObservers();
	}
        
        public void newEstado(String estado){
		this.message="Nuevo estado: "+estado;
		this.changed=true;
		notifyObservers();
	}
        
        public void recordatorio(String recordatorio){
		this.message="Recordatorio: "+recordatorio;
		this.changed=true;
		notifyObservers();
	}

}
