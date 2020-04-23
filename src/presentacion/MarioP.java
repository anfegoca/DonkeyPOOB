package presentacion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Esta clase es la representacion grafica de mario
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class MarioP extends PersonajeP {
	int cont=0;

	private ListaCircular<ImageIcon> spritesL = new ListaCircular<ImageIcon>();
	private ListaCircular<ImageIcon> spritesU = new ListaCircular<ImageIcon>();
	private ListaCircular<ImageIcon> spritesM = new ListaCircular<ImageIcon>();
	private ArrayList<ImageIcon> spritesS = new ArrayList<ImageIcon>();
	private char dir;

	/**
	 * Constructor de la clase
	 */
	public MarioP() {
		super();
		prepareSpritesDerecha();
		prepareSpritesIzquierda();
		prepareSpritesSaltar();
		prepareSpritesUp();
		prepareSpritesMuerte();
		zoom();
		setIcon(sprites.get(0));
		
		dir='R';
	}
	/**
	 * Prepara los sprites de la animacion de muerte
	 */
	public void prepareSpritesMuerte() {
		//Sprites Muerte
		try {
			File archivoImagen=new File ("recursos/texturas/muerte.png");
			BufferedImage imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesM.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/muerte2.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesM.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/muerte3.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesM.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/muerte4.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesM.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/muerte5.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesM.add(new ImageIcon(imagen));
		}catch(Exception e) {
		}
	}
	/**
	 * Prepara los sprites de la animación de saltar
	 */
	public void prepareSpritesSaltar() {
		//Sprites Saltar
		try {

			File archivoImagen=new File ("recursos/texturas/marioS.png");
			BufferedImage imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesS.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/marioSL.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesS.add(new ImageIcon(imagen));


			archivoImagen=new File ("recursos/texturas/marioS2.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesS.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/marioS2L.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesS.add(new ImageIcon(imagen));
		}catch(Exception e) {
		}
	}
	/**
	 * Prepara los sprites del movimiento a la derecha
	 */
	public void prepareSpritesDerecha() {

		try {
			File archivoImagen=new File ("recursos/texturas/mario2.png");
			BufferedImage imagen;
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			sprites.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/mario3.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			sprites.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/mario.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			sprites.add(new ImageIcon(imagen));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/**
	 * Prepara los sprites del movimiento a la izquierda
	 */
	public void prepareSpritesIzquierda() {
		//Sprites Izquierda
		try {


			File archivoImagen=new File ("recursos/texturas/mario2Left.png");
			BufferedImage imagen;
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesL.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/mario3Left.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesL.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/marioLeft.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesL.add(new ImageIcon(imagen));		
		}catch(Exception e) {
		}

	}
	/**
	 * prepara los sprites del movimiento vertical
	 */
	public void prepareSpritesUp() {
		//Sprites UP
		try {
			File archivoImagen=new File ("recursos/texturas/marioUp.png");
			BufferedImage imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesU.add(new ImageIcon(imagen));

			archivoImagen=new File ("recursos/texturas/marioUp2.png");
			imagen = ImageIO.read(archivoImagen);
			imagenes.add(imagen);
			spritesU.add(new ImageIcon(imagen));
		}catch(Exception e) {
		}


	}
	/**
	 * Cambia los sprites
	 * @param direction String con la direccion del sprite que debe cambiar, "right","left","Up","Down","salto"
	 */
	public void next(String direction) {
		if (direction=="right") {
			dir='R';
			setIcon(sprites.next());
		}
		if (direction=="left") {
			dir='L';
			setIcon(spritesL.next());
		}
		if (direction == "Up" || direction == "Down") {
			setIcon(spritesU.next());
		}
		if (direction=="salto") {
			if (dir=='R') {
				setIcon(spritesS.get(0));
			}
			else {
				setIcon(spritesS.get(1));
			}
		}




	}
	/**
	 * Cambia el tamaño de las imagenes
	 */
	public void zoom() {
		for (int i=0;i<sprites.size();i++) {
			sprites.set(i, new ImageIcon(sprites.get(i).getImage().getScaledInstance(45,50,Image.SCALE_DEFAULT)));
		}
		for (int i=0;i<spritesL.size();i++) {
			spritesL.set(i, new ImageIcon(spritesL.get(i).getImage().getScaledInstance(45,50,Image.SCALE_DEFAULT)));
		}
		for (int i=0;i<spritesU.size();i++) {
			spritesU.set(i, new ImageIcon(spritesU.get(i).getImage().getScaledInstance(45,50,Image.SCALE_DEFAULT)));
		}
		for (int i=0;i<spritesS.size();i++) {
			spritesS.set(i, new ImageIcon(spritesS.get(i).getImage().getScaledInstance(45,50,Image.SCALE_DEFAULT)));
		}
		for (int i=0;i<spritesM.size();i++) {
			spritesM.set(i, new ImageIcon(spritesM.get(i).getImage().getScaledInstance(45,50,Image.SCALE_DEFAULT)));
		}


	}
	/**
	 * Hace la acción de caer
	 * @param tipo direccion de la caida
	 */
	public void aterrizar(String tipo) {
		cont=0;
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {


				if (cont==1) {
					if (dir=='R'){
						setIcon(sprites.get(0));
					}
					else {
						setIcon(spritesL.get(0));
					}
				}
				else if (cont==0) {
					if (dir=='R'){
						setIcon(spritesS.get(2));
					}
					else {
						setIcon(spritesS.get(3));
					}
				}
				else {
					timer.cancel();
				}
				cont+=1;

			}
		};
		if (tipo=="vertical"){
			timer.schedule(tarea, 700, 80);
		}
		else if (tipo=="parabolicoR") {
			timer.schedule(tarea, 800, 80);
		}
		else {
			timer.schedule(tarea, 900, 80);
		}


	}
	
	/**
	 * Animación de muerte
	 */
	public void muerte() {
		cont=0;
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {

				if (cont==12) {
					setIcon(spritesM.get(4));
					timer.cancel();

				}
				else {
					setIcon(spritesM.next(4));
				}
				cont+=1;
			}
		};
		timer.schedule(tarea, 0, 80);

	}
	/**
	 * Permite cambiar la direccion de la imagen
	 * @param dir
	 */
	public void setDir(char dir) {
		this.dir=dir;
	}

}
