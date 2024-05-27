package notificacionesmoviles;

public class AppSubscriber implements Observer {
	
	private String nombre;
	private Subject app;
	
	public AppSubscriber(String nm){
		this.nombre=nm;
	}
	@Override
	public void update(String msg) {
		if(msg == null){
			System.out.println(nombre+":: No new message");
		}else
		System.out.println("Mensaje enviado a "+nombre+": "+msg);
	}

	@Override
	public void setSubject(Subject sub) {
		this.app=sub;
	}

}
