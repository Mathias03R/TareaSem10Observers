package notificacionesmoviles;

public class NotificacionesMoviles {

    public static void main(String[] args) {
	App app = new App();
		
	Observer obj1 = new AppSubscriber("Obj1");
	Observer obj2 = new AppSubscriber("Obj2");
	Observer obj3 = new AppSubscriber("Obj3");
		
	app.register(obj1);
	app.register(obj2);
	app.register(obj3);
		
	//attach observer to subject
	obj1.setSubject(app);
	obj2.setSubject(app);
	obj3.setSubject(app);
		
	//now send message to subject
	app.newMensaje("Nuevo mensaje");
        
        app.newEstado("Nuevo estado");
        
        app.recordatorio("recordatorio");
    }
    
}
