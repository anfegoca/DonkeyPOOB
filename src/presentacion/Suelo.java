package presentacion;
/**
 * Representacion grafica de una parte de la plataforma
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Suelo extends ObjetoFijo{
	
	/**
	 * Constructor de la clase
	 */
	public Suelo() {
		super();
		String url="recursos/texturas/plataforma.png";
		ponerImagen(url);
		zoom(25,20);
		setIcon(skin);
	}
}
