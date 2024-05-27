package gestiondeempleados;

public interface Observer {
	
	public void update(String msg);
	
	public void setSubject(Subject sub);
}
