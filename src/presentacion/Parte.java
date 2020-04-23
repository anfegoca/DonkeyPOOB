package presentacion;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Representa una parte de la escalera
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Parte extends ObjetoFijo {
	/**
	 * Constructor de la clase
	 * @param tipo "inicial", final" si es la parte inicial o final de la escalera
	 */
	public Parte(String tipo) {
		super();
		if (tipo=="inicial") {
			String url="recursos/texturas/escaleraI.png";
			ponerImagen(url);
		}
		else if(tipo=="final"){
			String url="recursos/texturas/escaleraF.png";
			ponerImagen(url);
		}
		else {
			String url="recursos/texturas/escaleraM.png";
			ponerImagen(url);
		}
		zoom(30,20);
		setIcon(skin);	
	}



}
