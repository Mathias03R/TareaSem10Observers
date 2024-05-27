package gestiondeempleados;

import java.util.ArrayList;
import java.util.List;

public class Empleado implements Subject{
    private String nombre, puesto, vacacionesProgramadas;
    
    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX= new Object();

    public Empleado(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
        observers = new ArrayList<>();
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
    
    public void changePuesto(String puesto){
	this.message="Se ha cambiado el puesto de "+nombre+" de "+this.puesto+" a "+puesto;
        this.puesto=puesto;
	this.changed=true;
	notifyObservers();
    }
    
    public void setVacaciones(String fecha){
	this.message="Se han programado vacaciones para "+nombre+" en la fecha "+fecha;
        this.vacacionesProgramadas=fecha;
	this.changed=true;
	notifyObservers();
    }
    
    public void nuevoEmpleado(Empleado empleado){
        this.message="Se ha anadido un nuevo empleado: " + nombre + ", "+puesto;
        this.changed=true;
	notifyObservers();
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public String getPuesto() {
        return this.puesto;
    }

    public String getVacacionesProgramadas() {
        return vacacionesProgramadas;
    }
}
