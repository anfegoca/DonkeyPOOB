package presentacion;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * Esta clase define la representacion grafica del barril
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class BarrilP extends PersonajeP{
	private JPanel padre;
	private int identificador;
	/**
	 * Constructor de la clase
	 */
	public BarrilP(int ide) {
		super();
		identificador=ide;
		try {
			File archivoImagen=new File ("recursos/texturas/barril.png");
			BufferedImage imagen;
			imagen = ImageIO.read(archivoImagen);
			sprites.add(new ImageIcon(imagen));
			archivoImagen = new File("recursos/texturas/barril2.png");
			imagen = ImageIO.read(archivoImagen);
			sprites.add(new ImageIcon(imagen));
			zoom(25,25);
			setIcon(sprites.get(0));
			
			
		}catch(Exception e) {
			
		}
	}
	public int getId() {
		return identificador;
	}
	public void next() {
		setIcon(sprites.next());
	}

}
