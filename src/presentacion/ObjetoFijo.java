package presentacion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Representa los objetos los cuales no tienen movimiento durante el juego
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class ObjetoFijo extends JLabel{
	protected ImageIcon skin;
	protected BufferedImage imagen;
	
	/**
	 * Constructor de la clase
	 */
	public ObjetoFijo() {
		super();
	}
	/**
	 * Permite colocarle una imagen al objeto
	 * @param url ruta de la imagen
	 */
	protected void ponerImagen(String url) {
		try {
			File archivoImagen=new File (url);
			imagen = ImageIO.read(archivoImagen);
			skin=new ImageIcon(imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Permite mover el objeto a determinada posicion
	 * @param x posicion en x a mover
	 * @param y posisicon en y a mover
	 */
	public void move(int x ,int y) {
		setBounds(x, y, 400, 50);
	}
	/**
	 * Cambia el tamaño del objeto
	 * @param w ancho
	 * @param h alto
	 */
	public void zoom(int w, int h) {
		skin=new ImageIcon(skin.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
	}
}
