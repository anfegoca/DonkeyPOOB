package aplicacion;

public class ExcepcionEscalera extends Exception{
	public static final String escaleraError= "LA ESCALERA QUEDA FUERA DE UNA PLATAFORMA";
	public static final String salio= "Se salio de la pantalla";
	
	public ExcepcionEscalera(String nombre) {
		super(nombre);
	}
}
