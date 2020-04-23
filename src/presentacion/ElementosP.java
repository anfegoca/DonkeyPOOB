package presentacion;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Clase que representa graficamente los elementos del juego que se pueden mover
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class ElementosP extends JLabel {
	
	protected ListaCircular<ImageIcon> sprites = new ListaCircular<ImageIcon>();
	
	/**
	 * Constructor de la clase
	 */
	public ElementosP() {
		super();
	}
	/**
	 * Permite mover la imagen a la coordenada x, y
	 * @param x punto en x
	 * @param y punto en y
	 */
	public void move(int x ,int y) {
		setBounds(x, y, 400, 50);
	}
	
}
