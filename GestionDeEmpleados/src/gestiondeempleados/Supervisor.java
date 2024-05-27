package gestiondeempleados;

public class Supervisor implements Observer{
    private String nombre;
    private Subject empleado;
	
    public Supervisor(String nombre){
	this.nombre=nombre;
    }

    @Override
    public void update(String msg) {
        if(msg == null){
            System.out.println(nombre+":: No new message");
	}else
            System.out.println("Notificacion enviada al supervisor "+nombre+" con el mensaje:");
            System.out.println(msg);
            System.out.println("-------------------------------------------------------------");
            System.out.println("");
    }

    @Override
    public void setSubject(Subject sub) {
        this.empleado=sub;
    }
    
}
