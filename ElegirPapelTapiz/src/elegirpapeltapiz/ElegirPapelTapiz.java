package elegirpapeltapiz;

public class ElegirPapelTapiz {

    public static void main(String[] args) {
        DiseñadorSubject selector = new DiseñadorSubject();
		
	DiseñadorObserver obj2 = new DiseñadorObserver("Diseñador2", true);
	DiseñadorObserver obj3 = new DiseñadorObserver("Diseñador3", false);
	
	selector.register(obj2);
	selector.register(obj3);
		
	//attach observer to subject
	obj2.setSubject(selector);
	obj3.setSubject(selector);
		
	//now send message to subject
	selector.preseleccionarPapel("Rojo");
        
        selector.preseleccionarPapel("Verde");
        
        selector.preseleccionarPapel("Amarillo");
        
        selector.seleccionarPapel("Rojo");
    }
    
}
