package elegirpapeltapiz;

public class DiseñadorObserver implements Observer {
	
	private String nombre;
	private Subject diseñadorSub;
        private boolean realTimeNotis;
	
	public DiseñadorObserver(String nm, boolean realTimeNotis){
		this.nombre=nm;
                this.realTimeNotis=realTimeNotis;
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
		this.diseñadorSub=sub;
	}
        
        public boolean isRealTimeNotis(){
            return realTimeNotis;
        }
        
        public String getName(){
            return nombre;
        }

}
