package presentacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Representa graficamente a un personaje del juego
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class PersonajeP extends JLabel{
	
	
	protected boolean IsVisible;
	protected ArrayList<BufferedImage> imagenes = new ArrayList<BufferedImage>();
	protected ListaCircular<ImageIcon> sprites = new ListaCircular<ImageIcon>();
	protected Color color;

	/**
	 * constructor de la clase
	 */
	public PersonajeP() {
		super();
		
	}
	/**
	 * Permite cambiar el color del personaje
	 * @param nuevo nuevo color
	 */
	public void changeColor(Color nuevo) {
		int cont = 0;
		for(BufferedImage b: imagenes) {
			changeColorBuffer(b,color,nuevo);
			sprites.set(cont,new ImageIcon(b)); 
		}
		/*
		try {
		//File archivoImagen=new File (url);
		imagen = ImageIO.read(archivoImagen);
		
		this.setIcon(new ImageIcon(imagen));
		
		}
		
		catch(Exception e){}
		*/
	}
	/**
	 * Cambia determinado color de una imagen por otro 
	 * @param imagen
	 * @param viejo
	 * @param nuevo
	 */
	private void changeColorBuffer(BufferedImage imagen ,Color viejo,Color nuevo) {
	    final int newRGB = nuevo.getRGB();
	    for (int x = 0; x < imagen.getWidth(); x++) {
	        for (int y = 0; y < imagen.getHeight(); y++) {
	        		if (imagen.getRGB(x, y) == viejo.getRGB()) {
	        			imagen.setRGB(x, y, newRGB);
	        		}
	        }
	    }
	}
	/**
	 * Cambia el tamaño de la imagen
	 */
	public void zoom(int w, int h) {
		for (int i=0;i<sprites.size();i++) {
			sprites.set(i, new ImageIcon(sprites.get(i).getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT)));
		}
		
	}
	/**
	 * mueve la imagen a determinada posicion
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public void move(int x ,int y) {
		setBounds(x, y, 400, 50);
	}
		
}
